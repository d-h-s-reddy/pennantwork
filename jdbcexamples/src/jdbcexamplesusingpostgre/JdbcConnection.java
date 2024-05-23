package jdbcexamplesusingpostgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
				"plf_training_admin", "pff123");
		Statement st = cn.createStatement();
		System.out.println(st.executeUpdate(
				"create table account(id int primary key,name varchar(30),accno varchar(10),balance DOUBLE)"));
		cn.close();

	}

}
