package pnu.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pnu.edu.domain.Member;
import pnu.edu.persistence.MemberRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepo;
	
	
	//AuthenticationManger의 authenticate 메소드가 호출되면 실행
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Member member = memberRepo.findById(username)
								.orElseThrow(()->new UsernameNotFoundException("Not FOUND!"));
		
		return new User(member.getUsername(),member.getPassward(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}
}
