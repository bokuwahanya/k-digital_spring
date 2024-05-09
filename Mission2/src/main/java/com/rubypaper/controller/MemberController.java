package com.rubypaper.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.MemberVO;
import com.rubypaper.service.MemberService;

@RestController
public class MemberController {
	MemberService memberService; //멤버변수
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/members")
	public Map<Integer,MemberVO> getAllMembers(){
		return memberService.getAllMember();
	}
	
	@GetMapping("/member")
	public MemberVO getMemberById(Integer memberId) {
		return memberService.getMemberById(memberId);
	}
	
	@PostMapping("/memberJSON")
	public MemberVO add(@RequestBody MemberVO memberVO) {
		return memberService.add(memberVO);
	}
	@PutMapping("/memberJSON")
	public int update(@RequestBody MemberVO memberVO) {
		return memberService.update(memberVO);
	}
	@DeleteMapping("/member")
	public int remove(Integer memberId) {
		return memberService.remove(memberId);
	}
}
