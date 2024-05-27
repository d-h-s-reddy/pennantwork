package com.orm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orm.model.Employee;
import com.orm.service.EmployeeDao;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeDao ed;

	@PostMapping(value = "/addempl")
	public ResponseEntity<String> addemployee(@RequestBody Employee formdata) {
		int flag = ed.addemployee(formdata);
		if (flag == 1) {
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK);
		} else {
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
