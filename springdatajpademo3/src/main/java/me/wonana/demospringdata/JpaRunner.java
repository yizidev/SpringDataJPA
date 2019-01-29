package me.wonana.demospringdata;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Account account = Account.builder()
				.username("wonana")
				.password("jpa")
				.build();
		
		Study study = Study.builder()
				.name("Spring Data JPA")
				.owner(account)
				.build();
		
		Session session = entityManager.unwrap(Session.class);
		session.save(account);
		session.save(study);
	}

}
