package jdbcexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6688563",
				"sql6688563", "AzMLRTxjdH");
		Statement st = cn.createStatement();
		System.out.println(st.executeUpdate(
				"create table account(id int primary key,name varchar(30),accno varchar(10),balance DOUBLE)"));
		cn.close();

	}

}
