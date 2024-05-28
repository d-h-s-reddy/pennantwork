package com.orm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orm.model.Employee;
import com.orm.service.EmployeeDao;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class EmployeeController {

	@Autowired
	EmployeeDao ed;

	@GetMapping(value = "/getempl")
	public ResponseEntity<List<Employee>> getemployee() {

		List<Employee> employee = ed.getemployees();
		if (employee != null) {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping(value = "/updateemp")
	public ResponseEntity<String> updateemployee(@RequestParam String empid, @RequestParam String emailid) {

		System.out.println("hi hello");
		int Empid = Integer.parseInt(emailid);

		boolean updatedquery = ed.updateemployee(Empid, emailid);
		if (updatedquery == true) {
			return ResponseEntity.ok("Employee email updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update employee email");
		}

	}

	@PostMapping(value = "/addempl", consumes = { "application/json" })
	public ResponseEntity<String> addemployee(@RequestBody Employee data) {
		System.out.println("called");
		int flag = ed.addemployee(data);
		if (flag == 1) {
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK).body("ok done");
		} else {
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not ok");
		}
	}

}
