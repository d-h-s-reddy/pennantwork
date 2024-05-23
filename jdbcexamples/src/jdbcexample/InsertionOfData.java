package jdbcexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InsertionOfData {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6688563",
				"sql6688563", "AzMLRTxjdH");
		// using the statement method for insertion
		Statement s;
		s = con.createStatement();
		String sql = "insert into account values(1,'Hari Sankar Reddy',501,1000000.0)";
		// System.out.println(s.executeUpdate(sql));

		// using the preparestatement
		sql = "insert into account values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, 2);
		ps.setString(2, "Vinay Sanjeev");
		ps.setString(3, "502");
		ps.setDouble(4, 1500000.0);
		// ps.executeUpdate();

		// batch processing
		// s.addBatch("insert into account values(3,'Reddy',504,1000000.0)");
		// s.addBatch("insert into account values(4,'Reddy1',505,2000000.0)");
		// s.addBatch("insert into account values(5,'Reddy2',506,4000000.0)");
		// s.addBatch("insert into account values(6,'Reddy3',507,5000000.0)");
		s.addBatch("insert into acc values(501,'harisankarreddy.d',10000.0,3548)");
		s.addBatch("insert into acc values(502,'vinaysanjeev.p',10000.0,9090)");
		s.addBatch("insert into acc values(503,'swarup.p',10000.0,8081)");
		s.addBatch("insert into acc values(504,'mukund.t',10000.0,7767)");

		int arr[] = s.executeBatch();
		for (int i : arr) {
			System.out.println(i);
		}

		con.close();

	}

}
