package sanju;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		ResourceBundle bundle = ResourceBundle.getBundle("db");
		String url = bundle.getString("url");
		String dbUsername = bundle.getString("username");
		String dbPassword = bundle.getString("password");

		try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {
			String sql = "SELECT * FROM i195_user_credentials WHERE user_name=? AND password=?";
			System.out.println("hi");
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) { // If the query returns a row, the credentials are valid
						HttpSession session = request.getSession();
						session.setAttribute("username", username);
						response.sendRedirect("EServlet"); // Redirect to your EmployeeServlet
					} else {
						response.sendRedirect("login.jsp?error=invalid"); // Redirect back to login page with an error
																			// message
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("login.jsp?error=db"); // Redirect back to login page with an error message
		}
	}
}
