package jdbcexamplesusingpostgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TypeScrollables {

	public static void main(String[] args) throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://sql6.freesqldatabase.com:3306/sql6688563",
				"sql6688563", "AzMLRTxjdH");
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery("select * from account");
		rs.next();
		// get the 5th row of the data
		rs.absolute(5);
		System.out.println("id:" + rs.getInt(1));
		rs.first();
		// using the relative
		rs.absolute(-1);
		System.out.println("id:" + rs.getInt(1));
		rs.first();
		rs.relative(3);
		System.out.println("id:" + rs.getInt(1));

		// adding the new row
		/*
		 * rs.moveToInsertRow(); rs.updateInt(1, 7); rs.updateString(2, "Hari"); rs.updateString(3, "508");
		 * rs.updateDouble(4, 10000.0); rs.insertRow();
		 */

		// update the row
		/*
		 * rs.absolute(3); rs.updateString(3, "503"); rs.updateRow();
		 */

		// Delete the row that is created
		rs.last();
		rs.deleteRow();

		con.close();

	}

}
