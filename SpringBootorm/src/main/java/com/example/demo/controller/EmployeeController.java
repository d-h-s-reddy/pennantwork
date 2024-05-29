package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Employee;
import com.example.demo.service.EmployeeDao;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao ed;

	@RequestMapping(value = "/")
	public String startingpage() {
		return "entryform";
	}

	@GetMapping(value = "/getempl")
	@ResponseBody
	public ResponseEntity<List<Employee>> getemployee() {

		List<Employee> employee = ed.getemployees();
		if (employee != null) {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping(value = "/updateemp")
	@ResponseBody
	public ResponseEntity<String> updateemployee(@RequestParam String empid, @RequestParam String emailid) {

		System.out.println("hi hello");
		int Empid = Integer.parseInt(empid);

		boolean updatedquery = ed.updateemployee(Empid, emailid);
		if (updatedquery == true) {
			return ResponseEntity.ok("Employee email updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update employee email");
		}

	}

	@PostMapping(value = "/addempl", consumes = { "application/json" })
	@ResponseBody
	public ResponseEntity<String> addemployee(@RequestBody Employee data) {
		System.out.println("called");
		int flag = ed.addemployee(data);
		if (flag == 1) {
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.OK).body("ok done");
		} else {
			return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not ok");
		}
	}

	@DeleteMapping(value = "/delemp")
	@ResponseBody
	public ResponseEntity<String> deleteemployee(@RequestParam String empid) {
		System.out.println("delete is invoked");
		int Empid = Integer.parseInt(empid);
		try {
			ed.deleteemployee(Empid);
			return ResponseEntity.ok("the employee has been deleted");
		} catch (RuntimeException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}

}
