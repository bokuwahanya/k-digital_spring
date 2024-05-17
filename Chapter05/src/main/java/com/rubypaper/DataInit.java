package com.rubypaper;

import java.util.Date;
import java.util.Random;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner {
	
	private final BoardRepository boardRepo;
	
	public void run(ApplicationArguments args) throws Exception {
		
		Random rd = new Random();
		for (int i = 1; i < 5; i++) {
			boardRepo.save(Board.builder()
					.title("title[1] " + i )
					.writer("member[1]")
					.content("content[1] " + i)
					.createDate(new Date())
					.cnt(Math.abs(rd.nextLong()%100L))
					.build()
					);
		}
		for (int i = 1; i < 5; i++) {
			boardRepo.save(Board.builder()
					.title("title[2] " +i )
					.writer("member[2]")
					.content("content[2] " + i)
					.createDate(new Date())
					.cnt(Math.abs(rd.nextLong()%100L))
					.build()
					);
		}
	}

}
