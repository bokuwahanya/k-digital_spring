package com.rubypaper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;




@SpringBootTest
public class RelationMappingTest {
	
	@Autowired
	private  BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(5L).get();
		System.out.println("[" + board.getSeq()+ "]번 게시글 정보");
		System.out.println("제목 :" + board.getTitle());
		System.out.println("내용 : " + board.getContent());
		System.out.println("작성자 : " + board.getMember().getName());
		System.out.println("작성자 권한 : " + board.getMember().getRole());
	}
	
	@Test
	public void testTwoWayMapping() {
		Member member = memberRepo.findById("member[1]").get();
		
		System.out.println("=".repeat(20));
		System.out.println(member.getName() + "가 저장한 게시글 목록");
		System.out.println("=".repeat(20));
		List<Board>list = member.getBoardList();
		for(Board board : list) {
			System.out.println(board.toString());
		}
	}
}
