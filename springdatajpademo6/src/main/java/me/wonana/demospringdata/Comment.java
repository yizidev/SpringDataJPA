package me.wonana.demospringdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

	@Id @GeneratedValue
	private Long id;
	
	private String contents;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Post post;
	
}
