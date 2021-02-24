package com.springboot.jpa.hibernate.databasehibernate.repository;

import com.springboot.jpa.hibernate.databasehibernate.DatabaseHibernateApplication;
import com.springboot.jpa.hibernate.databasehibernate.entity.Course;
import com.springboot.jpa.hibernate.databasehibernate.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

//żeby nie odpalac spring contect z głównego kodu dodajemy annotacje @Runwith i @SpringBootTest i wskazujemy klase którą ma odpalić
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseHibernateApplication.class)
class JpqlTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void createQueryBasicTest() {
		logger.info("createQueryBasicTest test is running");
		Query query = em.createQuery("Select c from Course c");
		List coursesList = query.getResultList();
		logger.info("Select c from Course c -> {} ", coursesList);
	}

	@Test
	void createQueryTypedTest() {
		logger.info("createQueryTypedTest test is running");
		TypedQuery<Course> query = em.createQuery("Select c from Course c", Course.class);
		List<Course> coursesList = query.getResultList();
		logger.info("Select c from Course c -> {} ", coursesList);
	}

	@Test
	void createWhereQueryTypedTest() {
		logger.info("createWhereQueryTypedTest test is running");
		TypedQuery<Course> query = em.createQuery("Select c from Course c where name like '% basics'", Course.class);
		List<Course> coursesList = query.getResultList();
		logger.info("Select c from Course c -> {} ", coursesList);
	}

	@Test
	@Transactional
	void jpqlSelectCoursesWithoutStudents(){
		//SELECT * FROM COURSE_DETAILS where course_details.id not in (select course_id from student_course)
		logger.info("jpqlSelectCoursesWithoutStudents test is running");
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> coursesList = query.getResultList();
		logger.info("Courses without students -> {} ", coursesList);
	}

	@Test
	@Transactional
	void jpqlCoursesWithAtLeastTwoStudents(){
		logger.info("jpglCoursesWithAtLeastTwoStudenst test is running");
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >=2", Course.class);
		List<Course> coursesList = query.getResultList();
		logger.info("Courses with at least 2 students -> {} ", coursesList);
	}

	@Test
	@Transactional
	void jpqlCoursesWiStudentsSorted(){
		logger.info("jpglCoursesWiStudentsSorted test is running");
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> coursesList = query.getResultList();
		logger.info("Courses with students sorted -> {} ", coursesList);
	}

	@Test
	@Transactional
	void jpqlStudentsWithPassportPattern(){
		logger.info("jpglStudentsWithPassportPattern test is running");
		TypedQuery<Student> query = em.createQuery("Select c from Student c where c.passport.number like '%1234%'", Student.class);
		List<Student> studentsList = query.getResultList();
		logger.info("Students withs 1234 in passport -> {} ", studentsList);
	}

	@Test
	@Transactional
	void jpqlJoinTest(){
		//JOIN => Select c, s from course c Join c.students s
		//jak course nie bedzie miał dopasowania to nie zostanie dołączony do listy wyników
		logger.info("jpglJoinTest test is running");
		Query query = em.createQuery("Select c, s from Course c Join c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("size of result list => {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course {} Student {}", result[0], result[1]);
		}

	}

	@Test
	@Transactional
	void jpqlLeftJoinTest(){
		//LEFT JOIN => Select c, s from course c LEFT JOIN c.students s
		//jak course nie bedzie miał dopasowania to i tak bedzie dołączony do listy wyników
		logger.info("jpglJoinTest test is running");
		Query query = em.createQuery("Select c, s from Course c Left Join c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("size of result list => {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course {} Student {}", result[0], result[1]);
		}

	}

	@Test
	@Transactional
	void jpqlCrossJoinTest(){
		//CROSS JOIN => Select c, s from course c CROSS JOIN c.students s
		// łączy wszystko z c ze wszyskim z s
		logger.info("jpglJoinTest test is running");
		Query query = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("size of result list => {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course {} Student {}", result[0], result[1]);
		}

	}

}
