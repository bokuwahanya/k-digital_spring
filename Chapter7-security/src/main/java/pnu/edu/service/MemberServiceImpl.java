package pnu.edu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pnu.edu.domain.Member;
import pnu.edu.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getId());
		//선택적으로 값을 포함할 수 있는 객체 optional
		if(findMember.isPresent())
			return findMember.get();
		else return null;
	}
}
