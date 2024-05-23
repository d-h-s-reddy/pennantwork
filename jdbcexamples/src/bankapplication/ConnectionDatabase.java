package bankapplication;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabase {

	public Connection ConnectionDatabases() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6688563", "sql6688563",
					"AzMLRTxjdH");
		} catch (Exception e) {
			System.out.println(e);
		}

		return con;
	}
}
