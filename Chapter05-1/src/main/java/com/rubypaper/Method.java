package com.rubypaper;

import java.util.Date;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@Component
public class Method {

	Random rd = new Random();
	@Autowired
	private BoardRepository boardRepo;

	public void run(ApplicationArguments args) throws Exception {
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
	
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("title[test] 10");
		print(boardList);
	}
	
	
}
