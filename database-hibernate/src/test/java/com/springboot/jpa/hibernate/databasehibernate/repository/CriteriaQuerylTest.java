package com.springboot.jpa.hibernate.databasehibernate.repository;

import com.springboot.jpa.hibernate.databasehibernate.DatabaseHibernateApplication;
import com.springboot.jpa.hibernate.databasehibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

//żeby nie odpalac spring contect z głównego kodu dodajemy annotacje @Runwith i @SpringBootTest i wskazujemy klase którą ma odpalić
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseHibernateApplication.class)
class CriteriaQuerylTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	void criteriaQueryBasicTest() {
		logger.info("criteriaQueryBasicTest test is running");
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		// 3. Define Predicates etc using Criteria Builder
		// 4. Add Predicates etc to the Criteria Query
		// 5. Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
		List<Course> coursesList = query.getResultList();
		logger.info("Criteria query test get courses -> {} ", coursesList);
	}

	@Test
	@Transactional
	void coursesWithBasicName() {
		logger.info("criteriaQueryBasicTest test is running");
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		// 3. Define Predicates etc using Criteria Builder
		Predicate likeBasics = criteriaBuilder.like(courseRoot.get("name"),"%basics");
		// 4. Add Predicates etc to the Criteria Query
		criteriaQuery.where(likeBasics);
		// 5. Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
		List<Course> coursesList = query.getResultList();
		logger.info("Criteria query test get courses -> {} ", coursesList);
	}

	@Test
	@Transactional
	void coursesWithoutStudents() {
		logger.info("criteriaQueryBasicTest test is running");
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		// 3. Define Predicates etc using Criteria Builder
		Predicate isEmpty = criteriaBuilder.isEmpty(courseRoot.get("students"));
		// 4. Add Predicates etc to the Criteria Query
		criteriaQuery.where(isEmpty);
		// 5. Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
		List<Course> coursesList = query.getResultList();
		logger.info("Criteria query test get courses -> {} ", coursesList);
	}

	@Test
	@Transactional
	void joinCoursesWithStudents() {
		logger.info("criteriaQueryBasicTest test is running");
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students");
		// 4. Add Predicates etc to the Criteria Query
		// 5. Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
		List<Course> coursesList = query.getResultList();
		logger.info("Criteria query test get courses -> {} ", coursesList);
	}

	@Test
	@Transactional
	void leftJoinCoursesWithStudents() {
		logger.info("criteriaQueryBasicTest test is running");
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		// 4. Add Predicates etc to the Criteria Query
		// 5. Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
		List<Course> coursesList = query.getResultList();
		logger.info("Criteria query test get courses -> {} ", coursesList);
	}
}
