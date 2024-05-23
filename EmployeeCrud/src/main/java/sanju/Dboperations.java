package sanju;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Dboperations implements EmployeeDAO {
	private Connection con;

	public Dboperations() {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("db");
			Class.forName(bundle.getString("driver"));
			con = DriverManager.getConnection(bundle.getString("url"), bundle.getString("username"),
					bundle.getString("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Employee> getAllEmp() throws SQLException {
		List<Employee> list = new ArrayList<>();
		try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery("select * from sanjana187e")) {
			while (rs.next()) {
				Employee e = new Employee();
				e.setEmpid(rs.getInt("empno"));
				e.setDept(rs.getInt("deptno"));
				e.setEmp_name(rs.getString("ename"));
				e.setJob(rs.getString("job"));
				e.setSalary(rs.getInt("monthly"));
				list.add(e);
			}
		}
		return list;
	}

	@Override
	public void add(Employee employee) throws SQLException {
		try (PreparedStatement pt = con.prepareStatement(
				"insert into sanjana187e (empno, ename, job, deptno, monthly) values (?, ?, ?, ?, ?)")) {
			pt.setInt(1, employee.getEmpid());
			pt.setString(2, employee.getEmp_name());
			pt.setString(3, employee.getJob());
			pt.setInt(4, employee.getDept());
			pt.setInt(5, employee.getSalary());
			pt.executeUpdate();
		}
	}

	@Override
	public void update(Employee employee) throws SQLException {
		try (PreparedStatement pt = con.prepareStatement(
				"update sanjana187e set ename = ?, job = ?, deptno = ?, monthly = ? where empno = ?")) {
			pt.setString(1, employee.getEmp_name());
			pt.setString(2, employee.getJob());
			pt.setInt(3, employee.getDept());
			pt.setInt(4, employee.getSalary());
			pt.setInt(5, employee.getEmpid());
			pt.executeUpdate();
		}
	}

	@Override
	public void delete(int empId) throws SQLException {
		try (PreparedStatement pt = con.prepareStatement("delete from sanjana187e where empno = ?")) {
			pt.setInt(1, empId);
			pt.executeUpdate();
		}
	}

	@Override
	public Employee getFirstEmployee() throws SQLException {
		List<Employee> employees = getAllEmp();
		return employees.isEmpty() ? null : employees.get(0);
	}

	@Override
	public Employee getLastEmployee() throws SQLException {
		List<Employee> employees = getAllEmp();
		return employees.isEmpty() ? null : employees.get(employees.size() - 1);
	}
}
