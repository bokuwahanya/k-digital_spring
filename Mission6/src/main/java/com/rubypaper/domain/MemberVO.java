package com.rubypaper.domain;





import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberVO {
	private String name;
	private String pass;
	private int id;
	private Date regidate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String birth;
}
