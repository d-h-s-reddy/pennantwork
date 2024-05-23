package bankapplication;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transfer extends Account {
	Account a, a2;
	ConnectionDatabase c = new ConnectionDatabase();
	Bank b = new Bank();
	Connection con = c.ConnectionDatabases();
	Label l1 = new Label("Enter the Account number:");
	TextField t1 = new TextField();

	Label l2 = new Label("Enter the Amount to Transfer:");
	TextField t2 = new TextField();

	Button b1 = new Button("Transfer");
	Button home = new Button("Home");
	PreparedStatement ps;
	ResultSet rs;

	Label l3 = new Label();

	Transfer(Account a) {
		this.a = a;
	}

	public void transfer() {
		l1.setBounds(120, 100, 150, 30);
		t1.setBounds(290, 100, 100, 30);
		l2.setBounds(120, 150, 180, 30);
		t2.setBounds(320, 150, 100, 30);
		b1.setBounds(200, 200, 50, 30);
		b.add(l1);
		b.add(t1);
		b.add(l2);
		b.add(t2);
		b.add(b1);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					a2 = new Account();
					ps = con.prepareStatement("select * from acc where accno=?");
					ps.setInt(1, Integer.parseInt(t1.getText()));
					rs = ps.executeQuery();
					rs.next();
					if (rs.isFirst()) {
						a2.setBalance(rs.getDouble("balance"));
						a2.setUsername(rs.getString("username"));
						updating();
						t1.setText("");
						t2.setText("");
						a2 = null;
					} else {
						l3.setText("Please check the account number once:");
						l3.setBounds(150, 250, 200, 30);
						b.add(l3);
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		home.setBounds(270, 200, 50, 30);
		b.add(home);
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				b.setVisible(false);
				ApplicationPage p = new ApplicationPage(a.getUsername());

			}

		});
	}

	public void updating() {
		try {
			ps = con.prepareStatement("update acc set balance=? where username=?");
			Double amt = Double.parseDouble(t2.getText());
			ps.setDouble(1, a2.getBalance() + amt);
			ps.setString(2, a2.getUsername());
			ps.addBatch();
			ps.setDouble(1, a.getBalance() - amt);
			ps.setString(2, a.getUsername());
			ps.addBatch();
			int arr[] = ps.executeBatch();
			boolean flag = true;
			for (int i : arr) {
				if (i == 1) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
			if (flag) {
				l3.setText("The amount has been transfered");
				l3.setBounds(150, 250, 200, 30);
				b.add(l3);
			} else {
				l3.setText("The amount has not transfered");
				l3.setBounds(150, 250, 200, 30);
				b.add(l3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
