package com.springboot.jpa.hibernate.databasehibernate.entity;

import javax.persistence.*;

//domyślnie:
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//nazwa kolumny dla child class przy InheritanceType.SINGLE_TABLE
@DiscriminatorColumn(name="Employee_Type")

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //nie tworzy tabeli dla klasy abstrakcyjnej

//@Inheritance(strategy = InheritanceType.JOINED) //tworzy table z klasą główną i podklasami które joinuje

//ostatnia forma zarządzania dziedziczeniem, nie pokazuje klasy nadrzędnej (czyli tej przy której jest wpisane)
@MappedSuperclass
//@Entity //jak jest @MappedSuperclass to nie może być @Entity
public abstract class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Employee(){
    }

    public Employee(String name) {
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
