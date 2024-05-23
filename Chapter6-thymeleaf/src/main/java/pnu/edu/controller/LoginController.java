package pnu.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pnu.edu.domain.Member;
import pnu.edu.service.MemberService;

@SessionAttributes("member")
@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public void loginView() {
	}
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		//뷰에 다가 넘겨줄 클래스 정보를 담는 객체 가 model
		Member findMember =memberService.getMember(member);
		
		if(findMember != null
				&& findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member",findMember);
			return"redirect:getBoardList";
		}
		else {
			return "redirect:login";
		}
	}
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		//sessionAttributes 속성 제거
		return "logout";
	}
}
