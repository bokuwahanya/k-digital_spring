package pnu.edu.controller;

import java.util.Collection;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pnu.edu.domain.Member;

@RequiredArgsConstructor
@Controller
@Slf4j
public class LoginController {
	
	private final AuthenticationConfiguration authConfig;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginProc(@RequestBody Member member){
		
		try {
			Authentication authToken = 	
					new UsernamePasswordAuthenticationToken(member.getUsername(),
															member.getPassward());
			Authentication auth = authConfig.getAuthenticationManager().authenticate(authToken);
			
			User user = (User)auth.getPrincipal();
			Collection<GrantedAuthority> cols = user.getAuthorities();
			StringBuffer role = new StringBuffer();
			for(GrantedAuthority ga : cols) {
				role.append(ga.getAuthority());
			}
			String jwtToken = JWT.create()
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 100))
                    .withClaim("username", user.getUsername())
                    .sign(Algorithm.HMAC256("pnu.edu.jwt"));
			
			log.info(user.toString());
			log.debug(jwtToken);
			
			return ResponseEntity.ok().header("Authorization","Bearer " + jwtToken).build();
		}catch(Exception e) {
			log.info(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
	}
	


}

