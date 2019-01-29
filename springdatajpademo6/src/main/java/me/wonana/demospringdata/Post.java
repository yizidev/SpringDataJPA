package me.wonana.demospringdata;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

	@Id @GeneratedValue
	private Long id;
	
	private String title;
	
//	@OneToMany(mappedBy = "post", cascade = CascadeType.DETACH)
	@OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
	private Set<Comment> comments = new HashSet<>();
	
	public void addComment(Comment comment) {
		this.getComments().add(comment);
		comment.setPost(this);
	}
}
