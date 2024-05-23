package pnu.edu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import pnu.edu.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
