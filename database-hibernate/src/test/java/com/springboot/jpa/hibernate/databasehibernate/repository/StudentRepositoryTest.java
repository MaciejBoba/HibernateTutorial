package com.springboot.jpa.hibernate.databasehibernate.repository;

import com.springboot.jpa.hibernate.databasehibernate.DatabaseHibernateApplication;
import com.springboot.jpa.hibernate.databasehibernate.entity.Address;
import com.springboot.jpa.hibernate.databasehibernate.entity.Course;
import com.springboot.jpa.hibernate.databasehibernate.entity.Passport;
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

import static org.junit.Assert.assertEquals;

//żeby nie odpalac spring contect z głównego kodu dodajemy annotacje @Runwith i @SpringBootTest i wskazujemy klase którą ma odpalić
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatabaseHibernateApplication.class)
class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	@Autowired
	EntityManager em;

	@Test
	//transacional dlatego że lazy fetching
	@Transactional
	void retrieveStudentAdnPassport() {
		logger.info("retrieveStudentAdnPassport test is running");
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("student passport -> {}", student.getPassport());
	}

	@Test
	//transacional dlatego że lazy fetching
	@Transactional
	void retrievePassportAndStudent() {
		logger.info("retrievePassportAndStudent test is running");
		Passport passport	 = em.find(Passport.class, 40001L);
		logger.info("passport -> {}", passport);
		logger.info("passport student -> {}", passport.getStudent());
	}

	@Test
	//transacional dlatego że lazy fetching
	@Transactional
	void retrieveStudentAndCourses() {
		logger.info("retrieveStudentAndCourses test is running");
		Student student	 = em.find(Student.class, 20005L);
		logger.info("student's courses -> {}", student.getCourses());
	}

	@Test
	//transacional dlatego że lazy fetching
	@Transactional
	void retrieveCourseAndStudent() {
		logger.info("retrieveCourseAndStudent test is running");
		Course course	 = em.find(Course.class, 10001L);
		logger.info("courses's students -> {}", course.getStudents());
	}

	@Test
	//transacional dlatego że lazy fetching
	@Transactional
	void setAddressToStudent() {
		logger.info("setAddressToStudent test is running");
		Address addr = new Address("abc","bcd", 12323, "MyCity", "MyCountry");
		Student student = em.find(Student.class, 20001L);
		student.setAddress(addr);
		em.flush();
		logger.info("Students address -> {}", student.getAddress());
		assertEquals(addr,student.getAddress());
	}

}
