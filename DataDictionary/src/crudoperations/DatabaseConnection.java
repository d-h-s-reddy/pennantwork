package crudoperations;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public Connection connectiondb() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
					"plf_training_admin", "pff123");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;

	}
}
