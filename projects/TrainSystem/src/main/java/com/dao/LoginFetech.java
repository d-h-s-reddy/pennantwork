package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoginFetech {
	Connection con = null;

	public LoginFetech() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
					"plf_training_admin", "pff123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean credentials(String usr, String pass) throws Exception {

		String sql = "select * from i197_users where username=? and password=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, usr);
		ps.setString(2, pass);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public ResultSet stations() throws Exception {
		String sql = "select * from i213_stations";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		return rs;
	}

	public ResultSet trainnames(String from, String to, String day) throws SQLException {

		ArrayList<String> al = new ArrayList<>();

		String sql = "select station_id from i213_stations where station_name=? or station_name=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, from);
		ps.setString(2, to);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			al.add(rs.getString("station_id"));
		}

		sql = "select * from i213_trains as t where t.train_no in (SELECT t1.train_no\r\n"
				+ "FROM i213_trainstations t1\r\n" + "JOIN i213_trainstations t2 ON t1.train_no = t2.train_no\r\n"
				+ "WHERE t1.station_id = ? \r\n" + "AND t2.station_id = ?) and train_schedule LIKE ? ";
		ps = con.prepareStatement(sql);
		ps.setString(1, al.get(0));
		ps.setString(2, al.get(1));
		ps.setString(3, "%" + day + "%");
		rs = ps.executeQuery();

		return rs;
	}

}
