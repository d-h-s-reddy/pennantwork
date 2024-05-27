package com.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orm.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
