package com.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orm.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
