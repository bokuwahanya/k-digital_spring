package pnu.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class Securitycontroller {
	@GetMapping({"/","index"})
	public String index() {
		System.out.println("index 요청");
		return "index";
	}
	
	@GetMapping("/member")
	public void member() {
		System.out.println("member 요청");
	}
	@GetMapping("/manager")
	public void manager() {
		System.out.println("manager 요청");
	}
	
	@GetMapping("/admin")
	public void admin() {
		System.out.println("admin 요청");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess 요청");
	}
	
	

}
