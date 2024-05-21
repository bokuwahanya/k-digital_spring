package com.rubypaper;

import java.util.Date;
import java.util.Random;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner {

	private final MemberRepository memberRepo;
	private final BoardRepository boardRepo;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		Member member = Member.builder()
							.id("KYOJURO")
							.name("KYOJURO")
							.pass("pass1")
							.role("FIRE")
							.build();
		
		memberRepo.save(member);
		
		
		for(int i = 0; i < 5; i++) {
			Board board = Board.builder()
						.title(member.getName() +"'S" + i)
						.content("CONTENT "+ i)
						.createDate(new Date())
						.member(member)
						.build();
			boardRepo.save(board);
		}
	}

}
