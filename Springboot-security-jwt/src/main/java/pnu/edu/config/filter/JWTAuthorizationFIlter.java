package pnu.edu.config.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import pnu.edu.domain.Member;
import pnu.edu.persistence.MemberRepository;

@RequiredArgsConstructor
public class JWTAuthorizationFIlter extends OncePerRequestFilter {
	//인가 설정을 위해 사용자의 Role 정보를 읽어 들이기 위한 객체 설정
	private final MemberRepository memberRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String srcToken = request.getHeader("Authorization"); // 요청 헤더에서 autorization을 얻어온다
		if(srcToken == null || !srcToken.startsWith("Bearer ")) { //없거나 bearer로 시작하지 않는다면
			filterChain.doFilter(request, response);// 필터를 그냥 통과
			return;
		}
		String jwtToken = srcToken.replace("Bearer ",""); // 토큰에서 bear를 제거
		
		System.out.println("jwtToken:" + jwtToken);
		//토큰에서 username 추출
		String username = JWT.require(Algorithm.HMAC256("pnu.edu.jwt")).build()
				.verify(jwtToken).getClaim("username").asString();
		
		System.out.println("username :" + username);
		
		Optional<Member> opt = memberRepository.findById(username);
		if(!opt.isPresent()) {
			filterChain.doFilter(request, response);
			return;
		}
		Member findmember = opt.get();
		
		//db에서 읽은 사용자 정보를 이용해서 userdetails 타입의 객체를 생성
		User user = new User(findmember.getUsername(), findmember.getPassward(), AuthorityUtils.createAuthorityList(findmember.getRole().toString()));
		// authetication 객체를 생성 : 사용자명과 권한 관리를 위한 정보를 입력(암호필요 없다)
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		//시큐리 세션에 등록한다.
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
		
		
	
	}
}

// onceperrequestfilter를 상속받게 되면 하나의 요청에 대해서단 한번만 필터를 거치게 된다
// 예를 들어 forwarding 되어 다른 페이지로 이동하게 되더라도 다시 이 필터를 거치지 않게 한다.