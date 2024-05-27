package pnu.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pnu.edu.domain.Member;
import pnu.edu.persistance.MemberRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService  {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberRepo.findById(username).orElseThrow(()->new UsernameNotFoundException("NOt FOUND"));
		
		return new User(member.getUsername(),member.getPassward(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}
	

}
//개발자 패스워드 제거!