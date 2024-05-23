package bankapplication;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Deposit extends Account {
	Bank f = new Bank();
	ConnectionDatabase c = new ConnectionDatabase();
	Connection con = c.ConnectionDatabases();
	PreparedStatement ps;
	Label l = new Label("Enter the Amount to be deposit:");
	Label out = new Label();
	TextField tf = new TextField();
	Button b = new Button("Submit");
	Button home = new Button("home");
	Account a;

	Deposit(Account a) {
		this.a = a;

	}

	public void deposit() {
		l.setBounds(100, 100, 180, 30);
		tf.setBounds(290, 100, 70, 30);
		b.setBounds(200, 150, 50, 30);
		f.add(tf);
		f.add(l);
		f.add(b);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int amt = Integer.parseInt(tf.getText());
					ps = con.prepareStatement("update acc set balance=? where username=?");
					ps.setDouble(1, a.getBalance() + amt);
					ps.setString(2, a.getUsername());
					int i = ps.executeUpdate();
					if (i == 1) {
						out.setText("your money is deposited successfully");
						out.setBounds(170, 200, 150, 30);
						tf.setText("");
						f.add(out);
					}
				} catch (Exception e1) {
					System.out.println(e1);
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
}
