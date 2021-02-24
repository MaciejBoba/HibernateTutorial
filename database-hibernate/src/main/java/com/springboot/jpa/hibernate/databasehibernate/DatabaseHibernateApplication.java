package com.springboot.jpa.hibernate.databasehibernate;

import com.springboot.jpa.hibernate.databasehibernate.entity.FullTimeEmployee;
import com.springboot.jpa.hibernate.databasehibernate.entity.PartTimeEmployee;
import com.springboot.jpa.hibernate.databasehibernate.repository.CourseRepository;
import com.springboot.jpa.hibernate.databasehibernate.repository.EmployeeRepository;
import com.springboot.jpa.hibernate.databasehibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;


@SpringBootApplication
public class DatabaseHibernateApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(DatabaseHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {





		//
		employeeRepository.insertEmployee(new FullTimeEmployee("Jack", new BigDecimal(10000)));
		employeeRepository.insertEmployee(new PartTimeEmployee("Lagherta", new BigDecimal(50)));
//
		logger.info("All PT employees -> {}", employeeRepository.getAllPartTimeEmployees());
		logger.info("All FT employees -> {}", employeeRepository.getAllFullTimeEmployees());



		//		List<Review> reviews = new ArrayList<>();
//		reviews.add(new Review("4*","A"));
//		reviews.add(new Review("4*","B"));
//		reviews.add(new Review("4*","C"));
//		reviews.add(new Review("4*","D"));
//		courseRepository.addReviewsForCourse(10005L, reviews);
//		studentRepository.insertStudentAndCourse(new Student("Muhhamad"),new Course("Decapitation for dummies"));



//		studentRepository.saveStudentWithPassport();
//		Course course = courseRepository.findById(10001L);
//		logger.info("Course 10001 -> {}", course);
//		logger.info("all Courses  -> {}", courseRepository.findAll());
//		logger.info("all ids -> {}", courseRepository.findAllIds());
//		logger.info("save new course -> {}", courseRepository.save(new Course("Microservices 101")));
//		logger.info("Delete 10001");
//		courseRepository.deleteById(10001L);

	}
}
