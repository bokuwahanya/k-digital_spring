package com.rubypaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

//인터페이스 밖에 형성 안되어있는데 부트 시작시 형성해서 보낸다.

@RestController
public class Testcontroller {

	
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/boards")
	public List<Board> getBoards(){
		return boardRepo.findAll();
	}
	
	@GetMapping("/board")
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}
	
	@PostMapping("/board")
	public Board postBoard(Board board) {
		return boardRepo.save(board);
	}
	@PutMapping("/board")
	public Board putBoard(Board board) {
		Board b = boardRepo.findById(board.getSeq()).get();
		b.update(board);
		return boardRepo.save(b);
	}
	@DeleteMapping("/board")
	public Board deleteBoard(Long seq) {
		Board board = boardRepo.findById(seq).get();
		boardRepo.deleteById(seq);
		return board;
	}
}
