package com.springboot.jpa.hibernate.databasehibernate.repository;

import com.springboot.jpa.hibernate.databasehibernate.DatabaseHibernateApplication;
import com.springboot.jpa.hibernate.databasehibernate.entity.Course;
import com.springboot.jpa.hibernate.databasehibernate.entity.Review;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

//żeby nie odpalac spring contect z głównego kodu dodajemy annotacje @Runwith i @SpringBootTest i wskazujemy klase którą ma odpalić
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseHibernateApplication.class)
class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	void findByIdBasicTestCase() {
		logger.info("findByIdBasicTestCase test is running");
		Course course = repository.findById(10001L);
		assertEquals("JPA basics", course.getName());
	}

	@Test
	@Transactional // first level cache automatycznie obejmuje scope tranzakcji
	void findByIdFirstLevelCache() {
		logger.info("findByIdFirstLevelCache test is running");
		Course course = repository.findById(10001L);
		logger.info("course => {}", course);
		Course course2 = repository.findById(10001L);
		logger.info("course2 => {}", course2);
		assertEquals("JPA basics", course.getName());
	}

	@Test
	@DirtiesContext
	void deleteByIdBasicTestCase() {
		logger.info("deleteByIdBasicTestCase test is running");
		repository.deleteById(10002L);
//		nie przejdzie bo zmieniem course na save delete poprzez:
//		pole is_deleted
//		@SQLDelete
//		@Where(clause="is_Deleted=false")
//		@PreRemove
//		assertNull(repository.findById(10002L));
	}

	@Test
	// spowoduje że baza danych nie bedzie zmieniona
	@DirtiesContext
	void saveUpdateTestCase() {
		logger.info("saveUpdateTestCase test is running");
		Course course=repository.findById(10003L);
		course.setName("Java2 4 advanced");
		repository.save(course);
		assertEquals("Java2 4 advanced",repository.findById(10003L).getName());
	}

	@Test
	// spowoduje że baza danych nie bedzie zmieniona
	@DirtiesContext
	void saveNewTestCase() {
		logger.info("saveNewTestCase test is running");
		Course course= new Course("Docker");
		repository.save(course);
		Long id=course.getId();
		assertEquals("Docker",repository.findById(id).getName());
	}

	@Test
	void testPlayWithEntityManager(){
		repository.playWithEntityManager();
	}

	@Test
	@Transactional
	void retrieveReviewsFromCourse(){
		Course course = repository.findById(10005L);
		logger.info("Reviews for 10005 course -> {}", course.getReviews());
	}

	@Test
	@Transactional
	void retrieveCourseFromReview(){
		Review review = em.find(Review.class,60001L);
		logger.info("Course for 60001 review -> {}", review.getCourse());
	}

	@Test
	@Transactional
	void createNplusOneProblem(){
		List<Course> courses = em.createNamedQuery("find_all_courses", Course.class).getResultList();
		for (Course course: courses) {
			logger.info("Course => {}, Students => {}", course, course.getStudents());
		}
	}

	@Test
	@Transactional
	void solveNplusOneProblem_EntityGraph(){
		//konfigurujemy joiny aby pobrac cours i students za jednym razem
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		entityGraph.addSubgraph("students");
		List<Course> courses = em.createNamedQuery("find_all_courses", Course.class).
				setHint("javax.persistence.loadgraph", entityGraph).
				getResultList();
		for (Course course: courses) {
			logger.info("Course => {}, Students => {}", course, course.getStudents());
		}
	}

	@Test
	@Transactional
	void solveNplusOneProblem_joinFetch(){
		//tworzymy named query z join fetch
		List<Course> courses = em.createNamedQuery("find_all_courses_join_fetch", Course.class).
				getResultList();
		for (Course course: courses) {
			logger.info("Course => {}, Students => {}", course, course.getStudents());
		}
	}
}
