package com.orm.service;

import java.util.List;
import java.util.Optional;

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
		Employee save = er.save(e);

		if (save != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Employee> getemployees() {
		return er.findAll();
	}

	@Override
	public boolean updateemployee(Integer empid, String newEmail) {

		Optional<Employee> emp = er.findById(empid);

		if (emp.isPresent()) {
			Employee e = emp.get();
			e.setEmail(newEmail);
			er.save(e);
			return true;
		} else {
			return false;
		}
	}
}
