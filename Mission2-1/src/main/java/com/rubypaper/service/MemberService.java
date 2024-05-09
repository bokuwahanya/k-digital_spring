package com.rubypaper.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rubypaper.domain.MemberVO;

@Service
public class MemberService {
	private Map<Integer,MemberVO> map = new HashMap<>();
	
	public MemberService(){
		for(int i = 0; i <=10; i++) {
			MemberVO member = MemberVO.builder()
					.id(i)
					.name("name" + i)
					.pass("pass" + i)
					.regidate(new Date())
					.build();
			
			map.put(i, member);
		}
	}
	
	
	public Map<Integer,MemberVO> getAllMember(){
	return map;
	}
	

	public MemberVO getMemberById(Integer memberId) {
		return map.get(memberId);
	}
	
	
	public MemberVO add(@RequestBody MemberVO memberVO) {
		int memberId = memberVO.getId();
		if(getMemberById(memberId)!= null) {
		return null;
	}
		map.put(memberId, memberVO);
		return memberVO;
}

	public int update(MemberVO memberVO) {
		int memberId = memberVO.getId();
		MemberVO m = getMemberById(memberId);
		if(m== null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		m.setRegidate(memberVO.getRegidate());
		return 1;
	}
	
	public int remove(Integer memberId) {
		try {
			map.remove(memberId);
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}
}
