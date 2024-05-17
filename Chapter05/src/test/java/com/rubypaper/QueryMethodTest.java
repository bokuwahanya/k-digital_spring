package com.rubypaper;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest {

	Random rd = new Random();
	@Autowired
	private BoardRepository boardRepo;

	@BeforeEach
	public void datePrepare() {
		for (int i = 1; i < 100; i++) {
			boardRepo.save(Board.builder().title("title[test] " + i).writer("member[test] ")
					.content("content[test] " + i).createDate(new Date()).cnt(Math.abs(rd.nextLong() % 100L)).build());
		}
	}

	public void printBoard(List<Board> boardList) {
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	public void printPage(Page<Board> pageInfo) {
	
		System.out.println("PAGE SIZE: "+ pageInfo.getSize());
		System.out.println("TOTAL PAGE: "+ pageInfo.getTotalPages());
		System.out.println("TOTAL COUNT: "+ pageInfo.getTotalElements());
		System.out.println("NEXT: "+ pageInfo.nextPageable());
		
		List<Board> boardList = pageInfo.getContent();
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}

//	@Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("title[test] 10");
		printBoard(boardList);
	}

	@Test
	public void findByContentContaining() {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
		List<Board> boardList = boardRepo.findByContentContaining("1", paging);
		printBoard(boardList);
	}

//	@Test
	public void findByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("1", "1");
		printBoard(boardList);
	}

//	@Test
	public void testFindByTitleContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrderBySeqDescContentDesc("1");
		printBoard(boardList);
	}

//	@Test
	public void findByTitleContainingOrContentContainingOrderByseqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2");
		printBoard(boardList);
	}

	@Test
	public void findByTitleContaining() {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
		Page<Board> pageInfo = boardRepo.findByTitleContaining("제목", paging);
		printPage(pageInfo);
	}
}
