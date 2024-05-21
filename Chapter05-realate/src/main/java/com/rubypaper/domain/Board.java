package com.rubypaper.domain;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 클래스 안에 있는 어노테이션은 제일 뒤에 추가해야 오류가 안남
@Getter
@Setter
@ToString
@Builder
//entity는 jpa에게 알려주는 어노테 jpa가 밑의 클래스 참조해서 테이블 만들어줌
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//식별값 생성
	private Long seq;
	//@Column varchar 길이지정가능
	private String title;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
	
	//자기가 여러게 멤버는 하나 // 일대다 환경
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	//조인 할때 필드명 필요.
	
	public void update(Board board) {
		if(board.getTitle() != null) {
			title = board.getTitle();
		}
		if(board.getMember().getId() != null) {
			member = board.getMember();
		}
		if(board.getContent() != null) {
			content = board.getContent();
		}
		
	}
}
