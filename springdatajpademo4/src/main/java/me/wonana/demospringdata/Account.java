package me.wonana.demospringdata;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Account {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	private String password;
	
	@OneToMany
	private Set<Study> studies;
	
	@Builder
	public Account(Long id, String username, String password, Set<Study> studies) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.studies = studies;
	}
	
}
