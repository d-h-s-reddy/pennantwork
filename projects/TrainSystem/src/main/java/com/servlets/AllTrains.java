package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import org.json.JSONArray;

import com.dao.LoginFetech;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AllTrains extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoginFetech l = new LoginFetech();
		try {

			PrintWriter out = response.getWriter();
			ResultSet rs = l.stations();
			JSONArray jsonArray = new JSONArray();
			while (rs.next()) {
				jsonArray.put(rs.getString("station_name"));
			}
			out.print(jsonArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
