package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.MemberVO;

@RestController
public class MemberController {
	private List<MemberVO> list = new ArrayList<>();
	
	public MemberController() {
		System.out.println("MemberController");
		for (int i =1; i <= 10 ; i++) {
			list.add(MemberVO.builder()
					.id(i)
					.name("name" + i)
					.pass("pass" + i)
					.regidate(new Date())
					.build());
		}
	}

	@GetMapping("/members")
	public ResponseEntity<?> getAllMember(){
		return ResponseEntity.ok(list);
		//호출할
	}
	//검색
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id)
				return m;
		}
		return null;
	}
	
	//입력
	@PostMapping("/memberJSON")
	public MemberVO addMember(@RequestBody MemberVO memberVO) {
		if(getMemberById(memberVO.getId()) != null) {
			System.out.println(memberVO.getId()+"는 이미 있씁니다");
			return null;
		}
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}
	//수정
	@PutMapping("/member")
	public int updateMembers(MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if( m == null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	//삭제
	@DeleteMapping("/member")
	public int removeMember (Integer id){
		try {
			list.remove(getMemberById(id));
		}catch(Exception e) {
			return 0;
		}
		return 1;
	}
}
