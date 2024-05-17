package com.rubypaper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class QueryAnnotationTest {

	@Autowired
	private BoardRepository boardRepo;

	Random rnd = new Random(100);

	@BeforeEach
	public void datePrepare() {
		for (int i = 1; i < 100; i++) {
			boardRepo.save(Board.builder()
					.title("title[test] " + i)
					.writer("member[test] ")
					.content("content[test] " + i)
					.createDate(new Date())
					.cnt(Math.abs(rnd.nextLong() % 100L)).build());
		}
	}

	public void printBoard(List<?> boardList) {
		System.out.println("검색결과");
		for(Object board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	@Test
	public void testQueryAnnotationTest1() {
		List<Board> boardList = boardRepo.queryAnnotationTest1("title[test] 10");
		printBoard(boardList);
	}
	@Test
	public void testQueryAnnotationTest2() {
		List<Object[]> boardList = boardRepo.queryAnnotationTest2("title[test] 10");
		System.out.println("검색결과");
		for(Object[] row : boardList) {
			System.out.println("--->" + Arrays.toString(row));
		}
	}
//	@Test
//	public void testQueryAnnotationTest3() {
//		List<Object[]> boardList = boardRepo.queryAnnotationTest3("title[test] 11");
//		System.out.println("검색결과");
//		for(Object[] row : boardList) {
//			System.out.println("--->" + Arrays.toString(row));
//		}
//	}
}
