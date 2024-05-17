package com.rubypaper;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;
import com.rubypaper.domain.Board;
import com.rubypaper.domain.QBoard;
import com.rubypaper.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {
	
	@Autowired
	private DynamicBoardRepository boardRepo;
	
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
	
	@Test
	public void testDynamicQuery() {
		String searchCondition = "TITLE";
		String searchKeyword = "title[test] ";
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}
		
		Pageable paging = PageRequest.of(0, 5);
		
		Page<Board> boardList = boardRepo.findAll(builder,paging);
		
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("--->" + board.toString());
		}
	}
}
