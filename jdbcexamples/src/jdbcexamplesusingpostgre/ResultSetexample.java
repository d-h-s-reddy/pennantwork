package jdbcexamplesusingpostgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ResultSetexample {

	public static void main(String[] args) throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
				"plf_training_admin", "pff123");
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
