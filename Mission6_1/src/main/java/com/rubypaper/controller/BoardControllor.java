package com.rubypaper.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Board;
import com.rubypaper.service.BoardService;


@RestController
public class BoardControllor {

	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/boards")
	public ResponseEntity<?> getboards(){
		return ResponseEntity.ok(boardService.getBoards());
	}
	
	@GetMapping("/board")
	public ResponseEntity<?> getboard(Long id){
		Board seq = boardService.getBoard(id);
		if(seq == null)
			return ResponseEntity.badRequest().body( "["+ id +"]번의 보드가 존재하지 않습니다.");
		return ResponseEntity.ok(seq);
	}
	
	@PostMapping("/board")
	public ResponseEntity<?> postBoard(Board board){
		return ResponseEntity.ok(boardService.postBoard(board));
	}
	@PutMapping("/board")
	public ResponseEntity<?> putBoard(Board board){
//		String seq = boardService.FindByMemberId(board);
//		if(seq == null)
//			return ResponseEntity.badRequest().body("해당 멤버가 존재하지 않습니다");
//		return ResponseEntity.ok(boardService.putBoard(board));
		return ResponseEntity.ok(boardService.putBoard(board));
			
	}
	@DeleteMapping("/board")
	public ResponseEntity<?> deleteBoard(Long id){
		Board seq = boardService.getBoard(id);
		if(seq == null)
			return ResponseEntity.badRequest().body( "["+ id +"]번의 보드가 존재하지 않습니다.");
		return ResponseEntity.ok(boardService.deleteBoard(id));
	}
}
