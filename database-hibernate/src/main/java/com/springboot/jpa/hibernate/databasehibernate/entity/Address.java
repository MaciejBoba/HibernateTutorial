package com.springboot.jpa.hibernate.databasehibernate.entity;

import javax.persistence.Embeddable;

//nie tworzy nowej tabeli tylko bezpośrednio przechwouje w Courses
@Embeddable
public class Address {
    private String line1;
    private String line2;
    private Integer postalCode;
    private String city;
    private String country;


    public Address(String line1, String line2, int postalCode, String city, String country) {
        this.line1 = line1;
        this.line2 = line2;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public Address() {
    }
}
