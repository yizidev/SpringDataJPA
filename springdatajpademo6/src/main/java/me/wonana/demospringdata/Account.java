package me.wonana.demospringdata;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	private String password;
	
	@OneToMany(mappedBy = "owner")
	private Set<Study> studies = new HashSet<>();
	
	@Builder
	public Account(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	@Builder
	public Account(Long id, String username, String password, Set<Study> studies) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.studies = studies;
	}
	
	/*
	 * 이런 관계 설정을 하는 메서드를 convenient method라 부른다.
	 * 이런식으로 convenient method를 만들어 사용하는 것이 일반적이다.
	 */
	public void addStudy(Study study) {
		this.getStudies().add(study);
		study.setOwner(this);
	}
	//이런 관계 설정을 하는 메서드를 convenient method라 부른다.
	public void addStudies(Set<Study> studies) {
		this.getStudies().addAll(studies);
		for(Study study : studies) {
			study.setOwner(this);
		}
	}
	
	// save와 마찬가지로 remove 할때도 관계설정을 잘 해야한다.
	public void removeStudy(Study study) {
		this.getStudies().remove(study);
		study.setOwner(null);
	}
	
}
