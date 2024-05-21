package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rubypaper.domain.Board;

//어노테이션이 없고 내용도 없고 인터페이스 상속받아서 그걸 사용해서 한다면 하이버네이트라는 놈이 자동실행
public interface BoardRepository extends JpaRepository<Board, Long> {
	
	List<Board> findByTitleContainingOrderBySeqDescContentDesc(String searchkeyword);

	List<Board> findByTitle(String searchKeyword);
	
	List<Board> findByContentContaining(String content, Pageable paging);

	List<Board> findByTitleContainingOrContentContaining(String title, String content);

	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String string, String string2);
	
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
	
}
