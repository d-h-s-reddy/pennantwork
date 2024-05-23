package jdbcexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ResultSetexample {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6688563",
				"sql6688563", "AzMLRTxjdH");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from account");
		while (rs.next()) {

			System.out.println("Id:" + rs.getInt(1) + " Name:" + rs.getString("name") + " Account no:" + rs.getString(3)
					+ " Balance:" + rs.getDouble("balance"));

		}
		// It is not possible because the resultset will move in forward direction
		// rs.first();
		// System.out.println("Id:" + rs.getInt(1) + " Name:" + rs.getString("name") + " Account no:" + rs.getString(3)
		// + " Balance:" + rs.getDouble("balance"));
		con.close();

	}

}
