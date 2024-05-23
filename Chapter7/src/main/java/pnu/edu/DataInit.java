package pnu.edu;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import pnu.edu.domain.Board;
import pnu.edu.domain.Member;
import pnu.edu.persistence.BoardRepository;
import pnu.edu.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner {
	
	private final BoardRepository boardRepo;
	private final MemberRepository memberRepo;
	
	public void run(ApplicationArguments args) throws Exception {
		
		Member member1 = Member.builder()
						.id("TAKESI")
						.password("user")
						.name("TAKESI")
						.role("ROLE_USER")
						.build();
		memberRepo.save(member1);
		
		Member member2 = Member.builder()
				.id("HARUKI")
				.password("admin")
				.name("HARUKI")
				.role("ROLE_ADMIN")
				.build();
memberRepo.save(member2);
		
		
		Random rd = new Random();
		for (int i = 1; i < 5; i++) {
			boardRepo.save(Board.builder()
					.title("TAKESI'S " + i )
					.content("content " + i)
					.writer("TAKESI")
					.createDate(new Date())
					.cnt(Math.abs(rd.nextLong()%100L))
					.build()
					);
		}
		for (int i = 1; i < 5; i++) {
			boardRepo.save(Board.builder()
					.title("HARUKI'S " +i )
					.content("content " + i)
					.writer("HARUKI")
					.createDate(new Date())
					.cnt(Math.abs(rd.nextLong()%100L))
					.build()
					);
		}
	}

}
