package pnu.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pnu.edu.domain.Board;
import pnu.edu.persistence.BoardRepository;

@Service
public class ServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	
	@Override
	public List<Board> getBoardList(Board board){
		return (List<Board>) boardRepo.findAll();
	}
	
	@Override
	public Board getBoard(Board board) {
		Board b = boardRepo.findById(board.getSeq()).get();
		b.setCnt(b.getCnt()+1);
		return b;
	}
	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}
	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		findBoard.setCnt(findBoard.getCnt()+1);
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}
	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}
	
}
