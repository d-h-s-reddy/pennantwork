package com.orm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orm.model.Employee;
import com.orm.repository.EmployeeRepository;

@Service
public class EmployeeService implements EmployeeDao {

	@Autowired
	EmployeeRepository er;

	@Override
	public int addemployee(Employee e) {
		Employee saved = er.save(e);
		if (saved != null) {
			return 1;
		} else {
			return 0;
		}
	}
}
