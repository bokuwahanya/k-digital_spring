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
public class BoardUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepo;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberRepo.findById(username)
								.orElseThrow(()->new UsernameNotFoundException("Not Found"));
		
		System.out.println(member);
		
		//userdetails 타입의 객체를 생성해서 리턴(o.s.s.core.userdetails.user
		return new User(member.getUsername(),member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}
	//사용자마다 디비정보가 다르다 유저정보를 디비에서 스프링부트로 들고와서 부트 시큐리티에서 제공하는 USER객체를 리턴.
	//자주등장 핵심
}
