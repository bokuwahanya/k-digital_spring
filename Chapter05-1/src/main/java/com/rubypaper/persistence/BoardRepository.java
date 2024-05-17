package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubypaper.domain.Board;

//어노테이션이 없고 내용도 없고 인터페이스 상속받아서 그걸 사용해서 한다면 하이버네이트라는 놈이 자동실행
public interface BoardRepository extends JpaRepository<Board, Long> {
	
	List<Board> findByTitleContainingOrderBySeqDescContentAsc(String searchkeyword);

	List<Board> findByTitle(String searchKeyword);
	
	List<Board> findByContentContaining(String content);

	List<Board> findByTitleContainingOrContentContaining(String title, String content);
}

//쿼리 메서드 명 준수