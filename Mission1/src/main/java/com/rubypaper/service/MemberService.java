package com.rubypaper.service;

import java.util.Date;

import com.rubypaper.domain.MemberVO;

public class MemberService {
	public static void main(String[] args) {
		
		//빌더로 변수 선언
		MemberVO m = MemberVO.builder()
				.id(2)
				.pass("p")
				.name("nma")
				.regidate(new Date())
				.build();
	}
}
