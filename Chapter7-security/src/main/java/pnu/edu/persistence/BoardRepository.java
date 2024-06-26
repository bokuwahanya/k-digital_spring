package pnu.edu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import pnu.edu.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
