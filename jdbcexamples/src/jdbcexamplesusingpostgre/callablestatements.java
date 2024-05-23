package jdbcexamplesusingpostgre;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class callablestatements {

	public static void main(String[] args) throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
				"plf_training_admin", "pff123");
		CallableStatement cs = cn.prepareCall("call datadisplay(?)");
		cs.setInt(1, 10);
		cs.registerOutParameter(1, Types.INTEGER);
		cs.execute();
		int num = cs.getInt(1);
		System.out.println(num);
		cn.close();

	}

}
