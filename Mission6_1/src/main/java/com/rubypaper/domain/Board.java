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

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;


	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;

	public void update(Board board) {
		if(board.getTitle() != null) {
			title = board.getTitle();
		}
		if(board.getMember() != null) {
			member = board.getMember();
		}
		if(board.getContent() != null) {
			content = board.getContent();
		}
	}
}
