package com.springboot.jpa.hibernate.databasehibernate.repository;

import com.springboot.jpa.hibernate.databasehibernate.entity.Course;
import com.springboot.jpa.hibernate.databasehibernate.entity.Review;
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
public class CourseRepository {

    @Autowired
    EntityManager em;

    Logger logger= LoggerFactory.getLogger(this.getClass());

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    public List<Course> findAll(){
        TypedQuery<Course> find_all_courses = em.createNamedQuery("find_all_courses", Course.class);
        return find_all_courses.getResultList();
    }

    public List<Long> findAllIds(){
        TypedQuery<Long> find_all_ids = em.createNamedQuery("find_all_ids", Long.class);
        return find_all_ids.getResultList();
    }

    public void deleteById(Long id){
        Course course=findById(id);
        em.remove(course);
    }

    public Course save(Course course) {
        if (course.getId()==null) em.persist(course);
        else em.merge(course);
        return course;
    }

    public void playWithEntityManager(){
        logger.info("Playing with entity manager-start");

        Course course1 = new Course("WebServices for dummies");
        em.persist(course1);
        Course course2 = new Course("Frontend for dummies");
        em.persist(course2);
        em.flush();
//        em.detach(course2);
        course1.setName("Advanced WebServices");
        course2.setName("Advanced Frontend");
//        course.setName("FUK!");
    }

    public void TimeUpdateWithEntityManager(){
        logger.info("Playing time with entity manager-start");
        Course course1 = new Course("CyberSecurity for dummies");
        em.persist(course1);
        em.flush();
        Long id=course1.getId();
        Course course2 = findById(id);
        course2.setName("Advanced CyberSecurity");
        Course course3 = findById(10001L);
        course3.setName("Advanced JPA");
//        em.flush();
    }

    public void addReviewsForCourse(){
        //find course
        Course course= findById(10002L);
        //create reviews
        Review review1 = new Review("6X", "Da bezd!");
        Review review2 = new Review("1X", "Da Worzdezd!");
        //setting up relations
        course.addReview(review1);
        review1.setCourse(course);
        course.addReview(review2);
        review2.setCourse(course);
        //save to data base
        em.persist(review1);
        em.persist(review2);
//        logger.info("addReviewsForCourse step 1 - course obtained -> {}", course);
//        logger.info("addReviewsForCourse step 1.1 - course reviews -> {}", course.getReviews());
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews){
        //find course
        Course course= findById(courseId);
        logger.info("Existing reviews -> {}", course.getReviews());
        //create reviews
        for (Review review: reviews){
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    }

}
