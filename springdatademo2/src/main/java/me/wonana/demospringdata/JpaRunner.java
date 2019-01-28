package me.wonana.demospringdata;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Account account = new Account();
		account.setUsername("hanana");
		account.setPassword("hib");

		/*
		 * jpa의 스펙인 entityManager의 persist를 사용하거나 hibernate의 session을 사용해서 영속화할 수 있다.
		 */
//		entityManager.persist(account);
		Session session = entityManager.unwrap(Session.class);
		session.save(account);
		
	}

}
