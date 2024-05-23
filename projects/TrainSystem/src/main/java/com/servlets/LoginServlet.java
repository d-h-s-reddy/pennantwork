package com.servlets;

import java.io.IOException;

import com.dao.LoginFetech;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginFetech l = new LoginFetech();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			if (l.credentials(username, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				response.sendRedirect("Booking.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
