package pnu.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pnu.edu.domain.Member;
import pnu.edu.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public void login() {
		System.out.println("login 요청");
		System.out.println("로그인 진행");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess(Model model, Member member) {
		model.addAttribute("username",member.getUsername());
		System.out.println(member);
		System.out.println("loginSuccess 요청");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("accessDenied");
	}
	
	@GetMapping("/auth")
	public @ResponseBody ResponseEntity<?> auth(@AuthenticationPrincipal User user) {
		
		if(user == null) {
			return ResponseEntity.ok("로그인상태 아님");
		}
		return ResponseEntity.ok(user);
	}
	@GetMapping("/join")
	public void join() {}
	
	@PostMapping("/join")
	public String joinProc(Model model , Member member) {
		model.addAttribute("username",member.getUsername());
		System.out.println(member);
		System.out.println("joinProc");
		memberService.save(member);
		return"welcome";
	}
//	@GetMapping("/welcome")
//	public void welcome(@AuthenticationPrincipal User user, Model model) {
//		model.addAttribute("username",user.getUsername());
//	}
}
