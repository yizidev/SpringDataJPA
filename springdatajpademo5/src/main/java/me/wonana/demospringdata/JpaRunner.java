package me.wonana.demospringdata;

import java.util.HashSet;
import java.util.Set;

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
		
		Study study = Study.builder()
				.name("Spring Data JPA")
				.build();
		Study study02 = Study.builder()
				.name("Hybernate")
				.build();
		
		
		Set<Study> studies = new HashSet<>();
		
		
		studies.add(study);
		studies.add(study02);
		
		Account account = Account.builder()
				.username("wonana")
				.password("jpa")
//				.studies(studies)
				.build();
		
//		study.setOwner(account);
		
//		account.addStudy(study);
		account.addStudies(studies);
		
		Session session = entityManager.unwrap(Session.class);
		session.save(account);
		session.save(study);
		session.save(study02);
	}

}
