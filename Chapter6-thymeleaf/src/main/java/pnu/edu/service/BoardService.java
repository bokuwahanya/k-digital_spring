package pnu.edu.service;

import java.util.List;

import pnu.edu.domain.Board;

public interface BoardService {

	List<Board> getBoardList(Board board);

	Board getBoard(Board board);

	void deleteBoard(Board board);

	void insertBoard(Board board);

	void updateBoard(Board board);

	

}