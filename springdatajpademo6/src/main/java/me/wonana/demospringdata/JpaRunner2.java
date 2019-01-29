package me.wonana.demospringdata;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JpaRunner2 implements ApplicationRunner {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
//		Post post = new Post();
//		post.setTitle("게시글(부모) 입니다."); 
//		
//		Comment comment = new Comment();
//		comment.setContents("댓글(자식) 입니다.");
//		post.addComment(comment);
//		
//		Comment comment1 = new Comment();
//		comment1.setContents("두번째 자식 입니다.");
//		post.addComment(comment1);
		Session session = entityManager.unwrap(Session.class);
		
		// @OneToMany cascade 속성에 Persist 를 주면 부모에서 자식에게로 상태를 전파하기 때문에
		// 부모 객체만 save 해도 자식 객체가 세이브된다.
//		session.save(post);
		
		Post post = session.get(Post.class, new Long(14));
		
		// 현재 테스트 버전에서 delete 시에 cascade.Persist 작동 안함 
		System.out.println(post.getComments());
		session.delete(post);
	}
}
