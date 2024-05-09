package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController
public class BoardController {
	public BoardController() {
		System.out.println("==>Boardcontroller 생성");
	}
	
	@GetMapping("/getBoard")
	//메서드가 get일때 온다. 포스트는 폼.
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("위대한 코뿔소의 이야기");
		board.setWriter("뤼메리뇽");
		board.setContent("히멀건모틴마스 퀸뚜에르 마티에스");
		board.setCreatDate(new Date());
		board.setCnt(0);
		return board;
		//controller를 한다면 위에 리턴값 전체에 대한 view를 던진다. 쓸 일이 별로 없다.
	}
	
	@GetMapping("/getBoardList")
	   public List<BoardVO> getBoardList() {
	      List<BoardVO> boardList = new ArrayList<BoardVO>();
	      for (int i = 1; i <= 10; i++) {
	         BoardVO board = new BoardVO();
	         board.setSeq(i);
	         board.setTitle("제목" + i);
	         board.setWriter("테스터");
	         board.setContent(i + "번 내용입니다.");
	         board.setCreatDate(new Date());
	         board.setCnt(0);
	         boardList.add(board);
	      }
	      return boardList;
	   }
}

//제이슨팡이로 간다잇