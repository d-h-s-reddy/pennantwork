package com.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orm.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Integer> {

}
