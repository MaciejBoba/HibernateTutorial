package com.springboot.jpa.hibernate.databasehibernate.repository;

import com.springboot.jpa.hibernate.databasehibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);
    List<Course> findByNameAndId(String name, Long Id);
    List<Course> countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    List<Course> deleteByName(String name);
    @Query("Select c from Course c where name like '%dummies'")
    List<Course> coursesWithDummiesInName();
    @Query(value = "Select c from Course c where name like '%dummies'", nativeQuery = true)
    List<Course> coursesWithDummiesInNameUsingNatviveQuery();
    @Query(name="find_all_courses")
    List<Course> coursesUsingNamedQuery();
}
