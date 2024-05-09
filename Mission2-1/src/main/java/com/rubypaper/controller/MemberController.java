package com.rubypaper.controller;

import org.springframework.http.ResponseEntity;
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
	MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getAllMember(){
		return ResponseEntity.ok(memberService.getAllMember());
	}
	@GetMapping("/member")
	public ResponseEntity<?> getMemberById(Integer memberId){
		MemberVO memberVO = memberService.getMemberById(memberId);
		if(memberVO == null)
			return ResponseEntity.badRequest().body("존재하지 않는 멤버임");
		return ResponseEntity.ok(memberVO);
	}
	@PostMapping("/memberJSON")
	public ResponseEntity<?> add(@RequestBody MemberVO memberVO){
		MemberVO memberId = memberService.add(memberVO);
		if(memberId == null)
			return ResponseEntity.badRequest().body("존재하지  id임.");
		return ResponseEntity.ok(memberId);
		
	}
	@PutMapping("/member")
	public ResponseEntity<?> update(@RequestBody MemberVO memberVO){
		int m = memberService.update(memberVO);
		if(m == 0)
			return ResponseEntity.badRequest().body("존재하지 않는 멤버임 ");
		return ResponseEntity.ok(m);
	}
	@DeleteMapping("/member")
	public ResponseEntity<?>remove(Integer memberId){
		int m = memberService.remove(memberId);
		if(m == 0)
			return ResponseEntity.badRequest().body("존재하지 않는 id");
		return ResponseEntity.ok(m);
			
	}
}
