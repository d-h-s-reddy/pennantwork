package bankapplication;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Withdraw extends Account {
	Account a;
	Bank f = new Bank();
	ConnectionDatabase c = new ConnectionDatabase();
	Connection con = c.ConnectionDatabases();
	PreparedStatement ps;
	Label l = new Label("Enter the Amount to be withdraw:");
	Label out = new Label();
	TextField tf = new TextField();
	Button b = new Button("Submit");
	Label l1 = new Label("Insufficient Balance");
	Button home = new Button("Home");

	Withdraw(Account a) {
		this.a = a;
	}

	public void withdraw() {
		l.setBounds(100, 100, 180, 30);
		tf.setBounds(290, 100, 70, 30);
		b.setBounds(200, 150, 50, 30);
		f.add(tf);
		f.add(l);
		f.add(b);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double amt = Double.parseDouble(tf.getText());
				if (checking(amt)) {
					try {
						System.out.println("hii");
						ps = con.prepareStatement("update acc set balance=? where username=?");
						ps.setDouble(1, a.getBalance() - amt);
						ps.setString(2, a.getUsername());
						int i = ps.executeUpdate();
						if (i == 1) {
							tf.setText("");
							l1.setText("Amount has been withdraw");
							l1.setBounds(160, 200, 150, 30);
							f.add(l1);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		home.setBounds(270, 150, 50, 30);
		f.add(home);
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				ApplicationPage p = new ApplicationPage(a.getUsername());

			}

		});

	}

	public boolean checking(double amt) {
		if (a.getBalance() < amt) {
			l1.setBounds(160, 200, 150, 30);
			f.add(l1);
			return false;
		} else {
			return true;
		}
	}
}
