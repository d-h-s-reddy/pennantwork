package jdbcexamplesusingpostgre;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;

public class GettingData {

	public static void main(String[] args) throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
				"plf_training_admin", "pff123");
		CallableStatement cs = cn.prepareCall("call get_employee(?,?");
		cs.setInt(1, 1);
		cs.setRef(2, null);
		cs.registerOutParameter(2, Types.REF_CURSOR);
		cs.executeQuery();
		ResultSet rs = (ResultSet) cs.getObject(2);
		System.out.println(rs);

	}

}
