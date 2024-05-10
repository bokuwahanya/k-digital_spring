package com.rubypaper.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.MemberVO;
import com.rubypaper.service.MemberService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor 
//필드에서 파이널이 붙는 생성자를 자동생성
//순환참조가 걸릴때는 이걸 해제하자
//최신 자주 사용
@RestController
public class MemberController {
	
//	@Autowired // 부트의 꽃
	private final MemberService memberService;

//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//		//생성자를 이용한 오토와이어드는 순환참조가 될 가능성이 있다.
//		//빈객체는 모든 객체가 프라이빗
////		memberService = new MemberService(new MemberDAO());
////		memberService = new MemberService();
////		memberService.setMemberDao(new MemberDAO());
//		System.out.println("멤버컨트롤러 생성");
//		//의존성 주입 외부에서 생성자를 받아서 넣는다.
//	}

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