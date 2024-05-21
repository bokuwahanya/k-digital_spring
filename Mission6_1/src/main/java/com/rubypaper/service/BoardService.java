package com.rubypaper.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rubypaper.domain.Board;

import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;

import com.rubypaper.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRepo;
	private final MemberRepository memberRepo;
	
	
	
	public List<Board> getBoards(){
		return boardRepo.findAll();
	}
	
	public String FindByMemberId(Board board) {
		return board.getMember().getId();
	}
	
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}
	public Board postBoard(Board board) {
		return boardRepo.save(board);
	}
	public Board putBoard(Board board) {
		Board b = boardRepo.findById(board.getSeq()).get();
		b.update(board);
		return boardRepo.save(b);
	}
	public Board deleteBoard(Long seq) {
		Board board = boardRepo.findById(seq).get();
		boardRepo.deleteById(seq);
		return board;
	}
}
