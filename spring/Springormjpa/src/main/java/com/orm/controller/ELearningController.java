package com.orm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orm.service.ELearningDao;

@Controller
public class ELearningController {

	@Autowired
	ELearningDao el;

	@RequestMapping(value = "/addcourses", method = RequestMethod.GET)
	@ResponseBody
	public String addcourses() {
		System.out.println("the data is being called");
		el.addcourses();
		return "<h1>data added successfully</h1>";
	}
}
