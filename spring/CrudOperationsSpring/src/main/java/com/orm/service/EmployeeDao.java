package com.orm.service;

import java.util.List;

import com.orm.model.Employee;

public interface EmployeeDao {
	int addemployee(Employee e);

	List<Employee> getemployees();

	boolean updateemployee(Integer empid, String newEmail);
}
