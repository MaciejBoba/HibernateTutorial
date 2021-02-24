package com.springboot.jpa.hibernate.databasehibernate.entity;

import javax.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String number;

    // mappedBy spowodowało tutaj że to klasa Student jest właścicielem relacji (passport - nazwa pola w Student)
    //mappedBy dodaje się do klasy która NIE jest właścicielem relacji
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;

    public Passport(){
    }

    public Passport(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + number + '\'' +
                '}';
    }
}
