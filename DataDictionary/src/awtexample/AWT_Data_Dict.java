package awtexample;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AWT_Data_Dict {
	Frame f;
	Font font, font1;
	Panel p;
	Label tab, col, con, column_names[], data[][], sno, colname, type;
	Choice chTab, chCol = new Choice();
	TextArea t = new TextArea();;
	AWT_Data_Dict awtd;
	String option, column_data[] = { "SNo", "Column_name", "Type" };
	String ConsData[][];

	public void afterTable() {
		chCol.removeAll();
		awtd.TabColRetrievalQuery(option);
		chCol.setBounds(320, 125, 100, 20);
		chCol.setBackground(Color.GRAY);
		chCol.setFont(font1);
		f.add(chCol);

		t.setBounds(100, 310, 300, 100);
		t.setVisible(true);
		t.setBackground(Color.DARK_GRAY);
		t.setForeground(Color.white);
		t.setText("");
		awtd.ConstraintsTableRetrievalQuery(option);
		for (String header : column_data) {
			t.append(header + "\t");
		}
		t.append("\n");
		for (int i = 0; i < ConsData.length; i++) {
			for (int j = 0; j < column_data.length; j++) {
				t.append("        " + ConsData[i][j] + "\t");
			}
			t.append("\n");
		}
		f.add(t);
	}

	public void TableRetrievalQuery() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
					"plf_training_admin", "pff123");
			Statement s = c.createStatement();
			String stmt = "select table_name from information_schema.tables order by table_name";
			ResultSet rs = s.executeQuery(stmt);
			while (rs.next()) {
				String tabName = rs.getString("table_name");
				chTab.add(tabName);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void TabColRetrievalQuery(String option) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
					"plf_training_admin", "pff123");
			Statement s = c.createStatement();
			String stmt = "select column_name from information_schema.columns where table_name = '" + option + "'";
			// System.out.println(stmt);
			ResultSet rs = s.executeQuery(stmt);
			while (rs.next()) {
				String colName = rs.getString("column_name");
				chCol.add(colName);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void ConstraintsTableRetrievalQuery(String option) {
		int count = 0;
		try {
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training",
					"plf_training_admin", "pff123");
			Statement s = c.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_FORWARD_ONLY);
			String stmt = "select c.column_name,t.constraint_type from information_schema.table_constraints t JOIN information_schema.constraint_column_usage c ON t.constraint_name = c.constraint_name where t.table_name = '"
					+ option + "'";
			ResultSet rs = s.executeQuery(stmt);
			while (rs.next()) {
				count++;
			}
			ConsData = new String[count][3];
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {

				ConsData[i][0] = "" + (i + 1);
				ConsData[i][1] = rs.getString(1);
				ConsData[i][2] = rs.getString(2);
				i++;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void Frame(final AWT_Data_Dict awtd) {
		this.awtd = awtd;
		f = new Frame();
		f.setVisible(true);
		f.setLayout(null);
		f.setBounds(500, 150, 20, 50);
		f.setSize(500, 500);
		f.setTitle("Data Dictionary");
		f.setBackground(Color.DARK_GRAY);

		font = new Font("Serif", Font.BOLD, 14);
		font1 = new Font("Courier", Font.PLAIN, 12);

		tab = new Label();
		tab.setText("Table");
		tab.setAlignment(Label.CENTER);
		tab.setBounds(90, 100, 100, 20);
		tab.setBackground(Color.lightGray);
		tab.setFont(font);
		f.add(tab);

		col = new Label();
		col.setText("Column");
		col.setAlignment(Label.CENTER);
		col.setBounds(320, 100, 100, 20);
		col.setBackground(Color.lightGray);
		col.setFont(font);
		f.add(col);

		con = new Label();
		con.setText("Constraints");
		con.setBounds(200, 250, 100, 20);
		con.setAlignment(Label.CENTER);
		con.setBackground(Color.lightGray);
		con.setFont(font);
		f.add(con);

		sno = new Label();
		sno.setText("Sno");
		sno.setAlignment(Label.CENTER);
		sno.setBounds(100, 280, 100, 20);
		sno.setBackground(Color.lightGray);
		sno.setFont(font);
		f.add(sno);

		colname = new Label();
		colname.setText("Column_Name");
		colname.setAlignment(Label.CENTER);
		colname.setBounds(200, 280, 100, 20);
		colname.setBackground(Color.lightGray);
		colname.setFont(font);
		f.add(colname);

		type = new Label();
		type.setText("Type");
		type.setAlignment(Label.CENTER);
		type.setBounds(300, 280, 100, 20);
		type.setBackground(Color.lightGray);
		type.setFont(font);
		f.add(type);

		chTab = new Choice();
		awtd.TableRetrievalQuery();
		chTab.setBounds(90, 125, 100, 20);
		chTab.setBackground(Color.GRAY);
		chTab.setFont(font1);
		f.add(chTab);
		chTab.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				System.out.println("option");
				// TODO Auto-generated method stub
				option = chTab.getSelectedItem();
				awtd.afterTable();
			}
		});

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AWT_Data_Dict awtd = new AWT_Data_Dict();
		awtd.Frame(awtd);
	}

}
