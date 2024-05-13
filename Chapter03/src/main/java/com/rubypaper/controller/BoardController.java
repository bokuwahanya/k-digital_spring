package com.rubypaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController
public class BoardController {

	@GetMapping("/hello")
	public String BoarController(String name){
		return("Hello : "+name);
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		return BoardVO.builder()
					.writer("테스터")
					.build();
	}
	
}
