package pnu.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import pnu.edu.domain.Member;
import pnu.edu.persistence.MemberRepository;


@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void save(Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		memberRepo.save(member);
	}
	
	 
}
