package pnu.edu.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import pnu.edu.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
