package com.springboot.jpa.hibernate.databasehibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//ponoc jak sie ma wiecej niż 1 NamedQuery to trzeba użyc @NamedQueries jak poniżej, ale u mnie działało o.O
//@NamedQuery(name="find_all_courses", query = "select p from Course p")
//@NamedQuery(name="find_all_ids", query = "select p.id from Course p")
@NamedQueries(value = {@NamedQuery(name="find_all_courses", query = "select p from Course p"),
        @NamedQuery(name="find_all_courses_join_fetch", query = "select c from Course c join fetch c.students s"),
        @NamedQuery(name="find_all_ids", query = "select p.id from Course p")
})
//potrzebne gdy ta klasa ma inna nazwe niz tabela w db
@Table(name="course_details")
@Cacheable
@SQLDelete(sql="update course set is_Deleted=true where id=?")
@Where(clause="is_Deleted=false")
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    //potrzebne gdy to pole ma inna nazwe niz kolumna w tabeli w db
    @Column(name="fullname", nullable = false, unique = false, insertable = true, updatable = true, length = 255, precision = 0, scale = 0)
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;
    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isDeleted;

    @PreRemove
    private void preRemove(){
        this.isDeleted=true;
    }

    /* inne annotacje związane z "cyklem życia"
    * @PostLoad
    * @PostPersist
    * @PostRemove
    * @PostUpdate
    * @PrePersist
    * @PreUpdate
    */

    public Course(){
    }

    public Course(String name) {
        this.name = name;
    }

    public void addReview(Review review) {
         this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
