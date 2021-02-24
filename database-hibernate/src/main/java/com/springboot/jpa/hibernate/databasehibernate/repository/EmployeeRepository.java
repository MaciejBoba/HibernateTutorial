package com.springboot.jpa.hibernate.databasehibernate.repository;

import com.springboot.jpa.hibernate.databasehibernate.entity.Employee;
import com.springboot.jpa.hibernate.databasehibernate.entity.FullTimeEmployee;
import com.springboot.jpa.hibernate.databasehibernate.entity.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    @Autowired
    EntityManager em;

    Logger logger= LoggerFactory.getLogger(this.getClass());

    public void insertEmployee(Employee employee){
        em.persist(employee);
    }

    public List<? extends Employee> getAllPartTimeEmployees(){
        return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<? extends Employee> getAllFullTimeEmployees(){
        return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }
}
