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

//@Component
public class JpaRunner implements ApplicationRunner {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Study study = Study.builder()
				.name("Spring Data JPA")
				.build();
		// entity의 Transient 상태: JPA가 전혀 모르는 상태
		Study study02 = Study.builder()
				.name("Hybernate")
				.build();
		// entity의 Transient 상태: JPA가 전혀 모르는 상태
		
		
		Set<Study> studies = new HashSet<>();
		
		
		studies.add(study);
		studies.add(study02);
		
		Account account = Account.builder()
				.username("wonana")
				.password("jpa")
//				.studies(studies)
				.build();
		// entity의 Transient 상태: JPA가 전혀 모르는 상태
		
//		study.setOwner(account);
		
//		account.addStudy(study);
		account.addStudies(studies);
		
		Session session = entityManager.unwrap(Session.class);
		session.save(account);	// Persistent 상태: JPA가 관리중인 상태(1차 캐시, Dirty Checking, Write Behind, ...)
		session.save(study);	// Persistent 상태: JPA가 관리중인 상태(1차 캐시, Dirty Checking, Write Behind, ...)
		session.save(study02);	// Persistent 상태: JPA가 관리중인 상태(1차 캐시, Dirty Checking, Write Behind, ...)
		
		// persistent 상태(session이 있기때문) select query가 발생하지 않는다. db접근이 줄기 때문에 성능상 유리
		Account wonana = session.load(Account.class, account.getId());
		// 객체의 변경사항을 알아서 update하게 된다. 이것을 Dirty Checking -> 객체의 변경상태를 계속 Check
		wonana.setUsername("hana");
		wonana.setUsername("hana2");
		wonana.setUsername("hana3");
		// 객체의 상태가 save 후 Persistent 상태로 이루어지게된 그 시점과 동일 하기 때문에 update가 아예 일어나질 않는다. Write Behind -> 객체의 상태 변화를 데이터베이스에 최대한 늦게 적용하려 한다.
		wonana.setUsername("wonana");
		System.out.println("=====================");
		System.out.println(wonana.getUsername());
	}
	/**
	 transaction이 종료되서 session에서 관리되던 객체가 반납되면 Detached 상태가 된다. 
	 Detached된 객체는 1차 캐싱, Dirty Checking, Write Behind, Lazy Loading등의 hibernate, JPA의 이점을 누릴 수 없다.
	 재적용하려면 re atached 상태로 전환시키거나 혹은 Cascade option을 사용한다.
	 전자의 경우 Session.update() / merge() / saveOrUpdate() / get() / load() / iterate() 등을 이용
	 후자의 경우 Cascade 속성을 설정한다. 상태변화를 전이시킨다.
	 Cascade 속성은 Parent - Child Relationship 인 Object에 적용할 수 있다.
	**/
	
}
