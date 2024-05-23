package sanju;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
	List<Employee> getAllEmp() throws SQLException;

	void add(Employee employee) throws SQLException;

	void update(Employee employee) throws SQLException;

	void delete(int empId) throws SQLException;

	Employee getFirstEmployee() throws SQLException;

	Employee getLastEmployee() throws SQLException;
}
