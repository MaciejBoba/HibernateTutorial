package com.springboot.jpa.hibernate.databasehibernate.repository;

import com.springboot.jpa.hibernate.databasehibernate.DatabaseHibernateApplication;
import com.springboot.jpa.hibernate.databasehibernate.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

//żeby nie odpalac spring contect z głównego kodu dodajemy annotacje @Runwith i @SpringBootTest i wskazujemy klase którą ma odpalić
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = DatabaseHibernateApplication.class)
public class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository repository;

	@Test
	public void findById_CoursePresent(){
		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());
	}

	@Test
	public void findById_CourseAbsent(){
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());
	}

	@Test
	public void playWithCrudMethods(){
		Course course = new Course("CRUD test");
		// insert or update => save
		repository.save(course);
		course.setName("CRUD updated");
		repository.save(course);

		logger.info("All courses => {}", repository.findAll());
		logger.info("Count courses => {}", repository.count());
	}

	@Test
	public void sortByNameDescending(){
		Sort sort = Sort.by(Sort.Direction.DESC, "name");
		logger.info("Sort (descending) courses => {}", repository.findAll(sort));
	}


	@Test
	public void pagination(){
		PageRequest pageRequest = PageRequest.of(0,3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First page of courses => {}", firstPage.getContent());
		Pageable nextPageable= firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(nextPageable);
		logger.info("Second page of courses => {}", secondPage.getContent());
	}

	@Test
	public void findUsingNames(){
		logger.info("Find by name => {}", repository.findByName("dummies"));
	}
}
