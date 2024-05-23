package awtexample;

import java.awt.Choice;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataDict extends Frame {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Label l1;
	Label l2;
	Label l3 = new Label();
	Choice c = new Choice();
	Choice cols = new Choice();
	String sql = "";
	String tablename = "";

	Label[] headers = new Label[] {};
	Label[][] data = new Label[][] {};
	String[] header = { "sno", "column_name", "constraint_type" };
	String[][] d;
	int co = 0;

	DataDict() throws Exception {
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training", "plf_training_admin",
				"pff123");
		sql = "select table_name from plf_training.information_schema.tables order by table_name";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		// adding label
		l1 = new Label("Table");
		l1.setBounds(30, 40, 50, 30);

		// adding choice
		while (rs.next()) {
			c.add(rs.getString(1));
		}
		c.setBounds(30, 80, 150, 30);

		// adding itemlistener

		c.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					cols.removeAll();
					tablename = c.getSelectedItem();
					columnchoice(tablename);

				}
			}

		});

		l2 = new Label("Columns");
		l2.setBounds(250, 40, 70, 30);

		cols.setBounds(250, 80, 150, 30);

		add(l1);
		add(c);
		add(l2);
		add(cols);
		setTitle("Data Dictionary");
		setSize(500, 500);
		setLayout(null);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void columnchoice(String tablename) {
		sql = "select column_name from plf_training.information_schema.columns where table_name=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, tablename);
			rs = ps.executeQuery();
			while (rs.next()) {
				cols.add(rs.getString(1));
			}

			// constraints
			sql = "SELECT c.column_name,t.constraint_type FROM information_schema.table_constraints t JOIN information_schema.constraint_column_usage c ON t.constraint_name = c.constraint_name WHERE t.table_name =?";
			System.out.println(tablename);
			ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, tablename);
			rs = ps.executeQuery();

			// if (co > 0) {
			// removingdata();
			// }
			// co++;

			int count = 0;
			while (rs.next()) {
				count++;
			}

			d = new String[count][3];
			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {
				d[i][0] = "" + (i + 1);
				d[i][1] = rs.getString(1);
				d[i][2] = rs.getString(2);
				i++;
			}

			displaydata();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void displaydata() {
		l3.setText("Constraints");
		l3.setBounds(50, 100, 100, 50);
		add(l3);
		headers = new Label[header.length];

		// header data
		for (String str : header) {
			System.out.println(str);
		}

		for (int i = 0; i < header.length; i++) {
			headers[i].setText(header[i]);
			headers[i].setBounds(50 + (i * 100), 140, 100, 30);
			add(headers[i]);
		}
		// data = new Label[d.length][header.length];
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < header.length; j++) {
				data[i][j].setText(d[i][j]);
				data[i][j].setBounds(50 + (j * 100), 190 + (i * 30), 100, 30);
				add(data[i][j]);
			}
		}
	}

	// public void removingdata() {
	// l3.setText("");
	// for (int i = 0; i < header.length; i++) {
	// headers[i].setText("");
	// }
	// for (int i = 0; i < d.length; i++) {
	// for (int j = 0; j < header.length; j++) {
	// data[i][j].setText("");
	// }
	// }
	//
	// }

	public static void main(String[] args) throws Exception {

		DataDict d = new DataDict();

	}

}
