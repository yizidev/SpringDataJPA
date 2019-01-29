package me.wonana.demospringdata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Study {

	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Builder
	public Study(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
}
