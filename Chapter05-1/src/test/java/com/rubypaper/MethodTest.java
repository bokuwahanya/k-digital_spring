package com.rubypaper;

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
public class MethodTest {

	Random rd = new Random();
	@Autowired
	private BoardRepository boardRepo;

	@BeforeEach
	public void datePrepare() {
		for (int i = 1; i < 100; i++) {
			boardRepo.save(Board.builder()
					.title("title[test] " + i )
					.writer("member[test] ")
					.content("content[test] " + i)
					.createDate(new Date())
					.cnt(Math.abs(rd.nextLong()%100L))
					.build()
					);
		}
	}
	
	public void print(List <Board> boardList) {
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
	@Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("title[test] 10");
		print(boardList);
	}
	
	
}
