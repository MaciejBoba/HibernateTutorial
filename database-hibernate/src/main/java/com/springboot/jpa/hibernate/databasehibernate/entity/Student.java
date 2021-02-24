package com.springboot.jpa.hibernate.databasehibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;

    // OneToOne to mapowanie 1 student ma tylko 1 passport i na odwót, fetchtypy=eager passport zawsze bedzie pobierany,
    // Lazy tylko wtedy jak zawołamy metode (która musi być @trasacional bo inaczej bedzie lazy exception)
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;


    @ManyToMany
    // po stronie właściciela relacji możemy zmienić nazwe jointable i jej kolumn
    @JoinTable(name="student_course",
            joinColumns = @JoinColumn(name="Student_id"),
            inverseJoinColumns = @JoinColumn(name="Course_id"))
    private List<Course> courses = new ArrayList<>();


    @Embedded
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Student(){
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course courses) {
        this.courses.add(courses);
    }

    public void removeCourse(Course courses) {
        this.courses.remove(courses);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
