package pnu.edu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pnu.edu.domain.Member;
import pnu.edu.domain.Role;
import pnu.edu.persistence.MemberRepository;

@SpringBootTest
public class MemberInitialize {

	@Autowired
	MemberRepository memberRepo;
	
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	public void doWork() {
		memberRepo.save(Member.builder()
								.username("member")
								.passward(encoder.encode("abcd"))
								.role(Role.ROLE_MEMBER)
								.enabled(true)
								.build());
		memberRepo.save(Member.builder()
								.username("manager")
								.passward(encoder.encode("abcd"))
								.role(Role.ROLE_MANAGER)
								.enabled(true)
								.build());
		memberRepo.save(Member.builder()
								.username("admin")
								.passward(encoder.encode("abcd"))
								.role(Role.ROLE_ADMIN)
								.enabled(true)
								.build());
	}
}
