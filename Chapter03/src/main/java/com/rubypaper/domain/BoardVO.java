package com.rubypaper.domain;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {
	private Integer seq;
	private String title;
	private String writer;
	private String content;
	
	@Builder.Default
	private Date createDate = new Date();
	private Integer cnt;
}
