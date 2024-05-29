package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Employee;

public interface EmployeeDao {
	int addemployee(Employee e);

	List<Employee> getemployees();

	boolean updateemployee(Integer empid, String newEmail);

	void deleteemployee(Integer empid);
}
