package com.rubypaper;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private final BoardRepository boardRepo;
	private final MemberRepository memberRepo;
	
	public void run(ApplicationArguments args) throws Exception {
		
		Member member1 = Member.builder()
						.id("member[1]")
						.password("mp[1]")
						.name("TAKESI")
						.role("ADMIN")
						.build();
		memberRepo.save(member1);
		
		Member member2 = Member.builder()
				.id("member[2]")
				.password("mp[2]")
				.name("HARUKI")
				.role("ADMIN")
				.build();
memberRepo.save(member2);
		
		
		Random rd = new Random();
		for (int i = 1; i < 5; i++) {
			boardRepo.save(Board.builder()
					.title("TAKESI'S " + i )
					.content("content " + i)
					.createDate(new Date())
					.cnt(Math.abs(rd.nextLong()%100L))
					.member(member1)
					.build()
					);
		}
		for (int i = 1; i < 5; i++) {
			boardRepo.save(Board.builder()
					.title("HARUKI'S " +i )
					.content("content " + i)
					.createDate(new Date())
					.cnt(Math.abs(rd.nextLong()%100L))
					.member(member2)
					.build()
					);
		}
	}

}
