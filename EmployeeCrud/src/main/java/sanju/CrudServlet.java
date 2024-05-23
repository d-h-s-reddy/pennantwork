package sanju;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/crudserve")
public class CrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDAO employeeDAO;

	public CrudServlet() {
		super();
		employeeDAO = new Dboperations();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String val = request.getParameter("action");
		if (val != null) {
			try {
				switch (val) {
				case "first":
					firstEmployee(request, response);
					break;
				case "last":
					lastEmployee(request, response);
					break;
				case "add":
					addEmployee(request, response);
					break;
				case "update":
					updateEmployee(request, response);
					break;
				case "delete":
					deleteEmployee(request, response);
					break;
				case "next":
					nextEmployee(request, response);
					break;
				case "previous":
					previousEmployee(request, response);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void nextEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("crud.jsp").forward(request, response);
	}

	private void previousEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("crud.jsp").forward(request, response);
	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		String name = request.getParameter("Empname");
		int id = Integer.parseInt(request.getParameter("Empid"));
		String job = request.getParameter("job");
		int department = Integer.parseInt(request.getParameter("Department"));
		int salary = Integer.parseInt(request.getParameter("Sal"));

		Employee employee = new Employee();
		employee.setEmpid(id);
		employee.setEmp_name(name);
		employee.setJob(job);
		employee.setDept(department);
		employee.setSalary(salary);

		employeeDAO.add(employee);

		response.sendRedirect("EServlet");
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("Empid"));
		String name = request.getParameter("Empname");
		String job = request.getParameter("job");
		int department = Integer.parseInt(request.getParameter("Department"));
		int salary = Integer.parseInt(request.getParameter("Sal"));

		Employee employee = new Employee();
		employee.setEmpid(id);
		employee.setEmp_name(name);
		employee.setJob(job);
		employee.setDept(department);
		employee.setSalary(salary);

		employeeDAO.update(employee);

		response.sendRedirect("EServlet");
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("Empid"));
		employeeDAO.delete(id);
		response.sendRedirect("EServlet");
	}

	private void firstEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			Employee firstEmployee = employeeDAO.getFirstEmployee();
			if (firstEmployee != null) {
				request.setAttribute("firstEmployee", firstEmployee);
			}
			request.getRequestDispatcher("crud.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void lastEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			Employee lastEmployee = employeeDAO.getLastEmployee();
			if (lastEmployee != null) {
				request.setAttribute("lastEmployee", lastEmployee);
			}
			request.getRequestDispatcher("crud.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
