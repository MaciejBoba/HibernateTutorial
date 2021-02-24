package com.springboot.jpa.hibernate.databasehibernate.repository;

import com.springboot.jpa.hibernate.databasehibernate.DatabaseHibernateApplication;
import com.springboot.jpa.hibernate.databasehibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

//żeby nie odpalac spring contect z głównego kodu dodajemy annotacje @Runwith i @SpringBootTest i wskazujemy klase którą ma odpalić
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseHibernateApplication.class)
class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void nativeQueryBasicTest() {
		logger.info("nativeQueryBasicTest test is running");
		Query query = em.createNativeQuery("Select * from course_details", Course.class);
		List coursesList = query.getResultList();
		logger.info("Select * from course_details -> {} ", coursesList);
	}

	@Test
	void nativeQueryTest() {
		logger.info("nativeQueryBasicTest test is running");
		Query query = em.createNativeQuery("Select * from course_details where id = ?", Course.class);
		query.setParameter(1,10001L);
		List coursesList = query.getResultList();
		logger.info("Select * from course_details where id = ? -> {} ", coursesList);
	}

	@Test
	void nativeQueryWithNamedParameterTest() {
		logger.info("nativeQueryBasicTest test is running");
		Query query = em.createNativeQuery("Select * from course_details where id = :id", Course.class);
		query.setParameter("id",10001L);
		List coursesList = query.getResultList();
		logger.info("Select * from course_details where id = ? -> {} ", coursesList);
	}

	@Test
	@Transactional
	@DirtiesContext
	void nativeQueryMassUpdateTest() {
		logger.info("nativeQueryBasicTest test is running");
		Query query = em.createNativeQuery("update course_details set LAST_UPDATED_DATE= :localDateTime", Course.class);
		query.setParameter("localDateTime", LocalDateTime.now());
		int numRowsAffected = query.executeUpdate();
		logger.info("mass update -> {} ", numRowsAffected);
	}
}
