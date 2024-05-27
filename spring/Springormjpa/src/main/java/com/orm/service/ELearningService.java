package com.orm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orm.model.Courses;
import com.orm.model.Student;
import com.orm.repository.CourseRepository;
import com.orm.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class ELearningService implements ELearningDao {

	@Autowired
	CourseRepository cr;

	@Autowired
	StudentRepository sr;

	@Transactional
	public void addcourses() {
		Student s1 = new Student();
		s1.setStudent_name("Vinay");
		Student s2 = new Student();
		s2.setStudent_name("Sanjeev");

		Courses c1 = new Courses();
		c1.setCourse_name("Java Full Stack");
		Courses c2 = new Courses();
		c2.setCourse_name("Data Science");

		// Save courses first
		cr.save(c1);
		cr.save(c2);

		// Now, set up relationships and save students
		s1.getCourses().add(c1);
		s1.getCourses().add(c2);
		s2.getCourses().add(c2);
		c1.getStudents().add(s1);

		sr.save(s1);
		sr.save(s2);

	}

}
