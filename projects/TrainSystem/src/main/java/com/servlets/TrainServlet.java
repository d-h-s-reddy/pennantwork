package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import com.dao.LoginFetech;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TrainServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginFetech l = new LoginFetech();
		String from = request.getParameter("s_from");
		String to = request.getParameter("s_to");
		String day = request.getParameter("t_day");
		day = day.substring(0, 1).toLowerCase();
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		try {
			ResultSet rs = l.trainnames(from, to, day);
			JSONArray trainnames = new JSONArray();
			while (rs.next()) {
				System.out.println(rs.getString("train_name"));
				trainnames.put(rs.getString("train_name"));
			}
			out.print(trainnames.toString());

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
