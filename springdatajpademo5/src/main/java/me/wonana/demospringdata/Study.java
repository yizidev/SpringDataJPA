package me.wonana.demospringdata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Study {

	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@ManyToOne
	private Account owner;
	
	@Builder
	public Study(Long id, String name, Account owner) {
		this.id = id;
		this.name = name;
		this.owner = owner;
	}
	
}
