package pnu.edu;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pnu.edu.domain.Member;
import pnu.edu.domain.Role;
import pnu.edu.persistence.MemberRepository;

@Component
@RequiredArgsConstructor
public class MemberInitialize implements ApplicationRunner {

	private final MemberRepository memberRepo;
	private final PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		memberRepo.save(Member.builder()
							.username("KAMADO")
							.password(encoder.encode("1234"))
							.role(Role.ROLE_MEMBER)
							.enabled(true)
							.build());
		
		memberRepo.save(Member.builder()
							.username("TAKESI")
							.password(encoder.encode("1234"))
							.role(Role.ROLE_MANAGER)
							.enabled(true)
							.build());
		
		memberRepo.save(Member.builder()
							.username("HARUKI")
							.password(encoder.encode("1234"))
							.role(Role.ROLE_ADMIN)
							.enabled(true)
							.build());
		
		
	}
}
