package pnu.edu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Member {
	@Id
	private String username;
	private String passward;
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private Role role = Role.ROLE_MEMBER;
	@Builder.Default
	private boolean enabled = true;
}