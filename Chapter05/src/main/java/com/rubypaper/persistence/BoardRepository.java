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
	
	//위치기반
	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String serchKeyword);
	
	//특정변수만 조회하기
	@Query("SELECT b FROM Board b "
			+ "WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Object[]> queryAnnotationTest2(String serchKeyword);
	
	@Query(value="select seq, title, writer "
			+ "from board where title like '%'||?1||'%' "
			+ "order by seq desc", nativeQuery=true)
	List<Object[]>queryAnnotationTest3(String searchKeyword);
}
//jpql에서는 쿼리안의 보드는 클래스 보드랑 이름이 같아야한다. 뒤에 있는 애들도 필드속성이다.
//쿼리 메서드 명 준수