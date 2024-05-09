package com.rubypaper.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.rubypaper.domain.MemberVO;

@Service
public class MemberService {
	private Map<Integer,MemberVO> map = new HashMap<>();
	
	public MemberService() {
		System.out.println("meberController");
		for(int i = 1; i<= 10; i++) {
			MemberVO member = MemberVO.builder()
					.id(i)
					.pass("pass"+i)
					.name("name"+i)
					.build();
			
			//맵에 데이터 추가
			map.put(i, member);
		}
	}
	
	
	@GetMapping("/members")
	public Map<Integer,MemberVO> getAllMember(){
		return map;
	}
	
	//검색
	@GetMapping("/member")
	public MemberVO getMemberById(Integer memberId) {
		return map.get(memberId);
	}
	//
	
	//입력
	@PostMapping("/memberJSON")	
	public MemberVO add(@RequestBody MemberVO memberVO) {
		
		int memberId = memberVO.getId();
		if(getMemberById(memberId)!= null) {
			return null;
		}
		map.put(memberId, memberVO);
		return memberVO;
//		int hashCode = memberVO.hashCode();
//		
//		MemberVO exist = null;
//		for(MemberVO member : map.values()) {
//			if(member.hashCode() == hashCode && member.equals(memberVO)) {
//				exist = member;
//				break;
//			}
//		}
//		if(exist != null) {
//			return null;
//		}
//		map.put(memberVO.getId(), memberVO);
//		
//		return memberVO;
	}
	//수정
	@PutMapping("/member")
	public int update(MemberVO memberVO) {
		int memberId = memberVO.getId();
		MemberVO m = getMemberById(memberId);
		if( m == null)	
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	@DeleteMapping("/member")
	public int remove(Integer id) {
		try {
			map.remove(id);
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}
}

