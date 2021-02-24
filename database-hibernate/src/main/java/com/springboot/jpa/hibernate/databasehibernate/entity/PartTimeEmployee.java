package com.springboot.jpa.hibernate.databasehibernate.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PartTimeEmployee extends Employee {
    private BigDecimal hourlyWage;

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "id=" + this.getId() +
                " name=" + this.getName()+
                " hourlyWage=" + hourlyWage +
                '}';
    }
}
