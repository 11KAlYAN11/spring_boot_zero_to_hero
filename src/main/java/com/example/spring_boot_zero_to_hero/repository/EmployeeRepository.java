package com.example.spring_boot_zero_to_hero.repository;

import com.example.spring_boot_zero_to_hero.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    /*
    means:

    Employee → entity
    Integer → primary key type


    🚀 NOW YOU AUTOMATICALLY GET

        Without writing code:

        save()
        findAll()
        findById()
        deleteById()
        count()
        existsById()
     */
}
