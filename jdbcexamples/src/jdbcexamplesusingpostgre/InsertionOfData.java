package jdbcexamplesusingpostgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertionOfData {

	public static void main(String[] args) throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
				"plf_training_admin", "pff123");
		// using the statement method for insertion
		System.out.println(con);
		Statement s;
		s = con.createStatement();
		// String sql = "insert into account values(1,'Hari Sankar Reddy',501,1000000.0)";
		// // System.out.println(s.executeUpdate(sql));
		//
		// // using the preparestatement
		// sql = "insert into account values(?,?,?,?)";
		// PreparedStatement ps = con.prepareStatement(sql);
		// ps.setInt(1, 2);
		// ps.setString(2, "Vinay Sanjeev");
		// ps.setString(3, "502");
		// ps.setDouble(4, 1500000.0);
		// // ps.executeUpdate();

		// batch processing
		s.addBatch(
				"insert into hariemployee values(1,'hari sankar reddy','Ase',400000,'harisankarreddy.d@gmail.com','vishkapatanam')");
		s.addBatch(
				"insert into hariemployee values(2,'Vinay Sanjeev','Ase',420000,'vinaysanjeev.p@gmail.com','vishkapatanam')");
		s.addBatch(
				"insert into hariemployee values(3,'swarup reddy','sde1',5000000,'swarupreddy@gmail.com','vishkapatanam')");
		s.addBatch(
				"insert into hariemployee values(4,'sai mukund','sde2',3000000,'mukund60@gmail.com','vishkapatanam')");

		int arr[] = s.executeBatch();
		for (int i : arr) {
			System.out.println(i);
		}

		con.close();

	}

}
