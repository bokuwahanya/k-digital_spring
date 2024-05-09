package com.rubypaper.controller;

import java.sql.SQLException;

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
	public ResponseEntity<?> getMemberById(Integer id){
		MemberVO m = memberService.getMemberById(id);
		if(m == null)
			return ResponseEntity.badRequest().body("멤버가 존재하지 않습니다.");
		return ResponseEntity.ok(m);
	}

	@PostMapping("/memberJSON")
	public ResponseEntity<?> add(@RequestBody MemberVO memberVO){
		MemberVO memberId = memberService.add(memberVO);
		if(memberId == null)
			return ResponseEntity.badRequest().body("존재하지  id임.");
		return ResponseEntity.ok(memberId);

	}
	@PutMapping("/member")
	public ResponseEntity<?> update(MemberVO memberVO) throws SQLException{
		int m = memberService.update(memberVO);
		if(m == 0)
			return ResponseEntity.badRequest().body("존재하지 않는 멤버임 ");
		return ResponseEntity.ok(m);
	}
	@DeleteMapping("/member")
	public ResponseEntity<?>remove(Integer id){
		System.out.println(id);
		int m = memberService.remove(id);
		if(m == 0) {
			return ResponseEntity.badRequest().body("존재하지 않는 id");
		}
		return ResponseEntity.ok(m);

	}

}