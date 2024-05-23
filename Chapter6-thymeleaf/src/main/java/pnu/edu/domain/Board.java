package pnu.edu.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity
public class Board {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	@Column(updatable = false)
	private String writer;
	private String content;
	@Column(insertable = false, updatable = false, columnDefinition = "date default (curdate())")
	private Date createDate;
	
	@Column(insertable = false, updatable = false, columnDefinition = "bigint default 0")
	private Long cnt;
}
