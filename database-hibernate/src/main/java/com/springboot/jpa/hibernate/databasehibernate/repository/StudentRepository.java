package com.springboot.jpa.hibernate.databasehibernate.repository;

import com.springboot.jpa.hibernate.databasehibernate.entity.Course;
import com.springboot.jpa.hibernate.databasehibernate.entity.Passport;
import com.springboot.jpa.hibernate.databasehibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    EntityManager em;

    Logger logger= LoggerFactory.getLogger(this.getClass());

    public Student findById(Long id){
        return em.find(Student.class, id);
    }

    public List<Student> findAll(){
        TypedQuery<Student> find_all_Students = em.createNamedQuery("find_all_Students", Student.class);
        return find_all_Students.getResultList();
    }

    public List<Long> findAllIds(){
        TypedQuery<Long> find_all_ids = em.createNamedQuery("find_all_ids", Long.class);
        return find_all_ids.getResultList();
    }

    public void deleteById(Long id){
        Student student=findById(id);
        em.remove(student);
    }

    public Student save(Student student) {
        if (student.getId()==null) em.persist(student);
        else em.merge(student);
        return student;
    }

    public void saveStudentWithPassport(){
        Passport passport = new Passport("X1234567");
        em.persist(passport);
        Student student = new Student("John Kowalsky");
        student.setPassport(passport);
        em.persist(student);
    }

    public void insertStudentAndCourse(){
        // create new data & populate to db
        Student student = new Student("Muhhamad");
        Course course = new Course("Decapitation for dummies");
        em.persist(student);
        em.persist(course);
        // set relations
        student.addCourse(course);
        course.addStudent(student);
        //populate relation (owning side)
        em.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course){
        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
        em.persist(course);
    }
}
