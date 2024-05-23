package crudoperations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

class Employee {
	int empno;
	String empname;
	String job;
	double Salary;
	int depnum;
	Date hdate;

	public Employee(int empno, String empname, String job, double salary, int depnum, Date hdate) {
		this.empno = empno;
		this.empname = empname;
		this.job = job;
		Salary = salary;
		this.depnum = depnum;
		this.hdate = hdate;
	}

}

public class Retrivingdata {
	DatabaseConnection c = new DatabaseConnection();
	Connection con = c.connectiondb();
	ResultSet rs;
	Statement st;
	ArrayList<Employee> al = new ArrayList();
	Employee e;

	public void retriving() {
		try {
			al.clear();
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT * FROM emp185");
			while (rs.next()) {
				al.add(new Employee(rs.getInt("emp_num"), rs.getString("emp_name"), rs.getString("job"),
						rs.getDouble("salary"), rs.getInt("dept_num"), rs.getDate("hire_Date")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adding(String sql) {
		int i = 0;
		try {
			i = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(i);
	}

	public void deleting(String sql) {
		int i = 0;
		try {
			i = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(i);

	}

	public void updating(String sql) {
		int i = 0;
		try {
			i = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(i);

	}
}