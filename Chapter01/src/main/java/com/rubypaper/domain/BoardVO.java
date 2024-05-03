package com.rubypaper.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@AllArgsConstructor // 모든 것이 포함된 생성자
@NoArgsConstructor // 아무것도 없는 생성자
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date creatDate = new Date();
	private int cnt = 0;
}
