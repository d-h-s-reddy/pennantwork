package bankapplication;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginpage implements ActionListener {

	Label l1 = new Label("Welcome to Pennant Bank");
	Label l2 = new Label("Enter the user name:");
	Label l3 = new Label("Enter the password:");
	TextField tf1 = new TextField();
	TextField tf2 = new TextField();
	Label tf3 = new Label();
	Button b = new Button("Login");

	// database
	ConnectionDatabase cb = new ConnectionDatabase();
	Connection con = cb.ConnectionDatabases();
	PreparedStatement ps;
	ResultSet rs;
	Bank f = new Bank();

	loginpage() {

		l1.setBounds(180, 50, 200, 30);
		l2.setBounds(130, 100, 120, 30);
		l3.setBounds(130, 150, 120, 30);
		tf1.setBounds(260, 100, 120, 25);
		tf2.setBounds(260, 150, 120, 25);
		tf2.setEchoChar('*');
		b.setBounds(230, 200, 40, 30);
		b.addActionListener(this);
		// adding to the frame
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(tf1);
		f.add(tf2);
		f.add(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (con != null) {
				String usr = tf1.getText();
				String pwd = tf2.getText();
				boolean flag = verification(usr, pwd);
				if (flag == true) {
					tf3.setText("valid user");
					ApplicationPage p = new ApplicationPage(usr);
					f.setVisible(false);
				} else {
					tf3.setText("not a valid user");
				}
				tf3.setBounds(180, 260, 200, 30);
				tf1.setText("");
				tf2.setText("");
				f.add(tf3);
			} else {
				System.out.println("Database is not connected");
			}

		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	public boolean verification(String usr, String pwd) throws Exception {
		ps = con.prepareStatement("select * from login where username=? and password=?");
		ps.setString(1, usr);
		ps.setString(2, pwd);
		rs = ps.executeQuery();

		rs.next();
		if (rs.isFirst()) {
			return true;
		} else {
			return false;
		}
	}

}
