package com.springboot.jpa.hibernate.databasehibernate.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class FullTimeEmployee extends Employee {
    private BigDecimal salary;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                "id=" + this.getId() +
                " name=" + this.getName()+
                " salary=" + salary +
                '}';
    }
}
