package bankapplication;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ApplicationPage implements ActionListener {
	Bank f1 = new Bank();
	ConnectionDatabase b = new ConnectionDatabase();
	Connection con = b.ConnectionDatabases();
	PreparedStatement ps;
	ResultSet rs;
	Account a = new Account();
	Label l = new Label();
	String username;
	Button cash = new Button("Cashwithdraw");
	Button trans = new Button("Transfer");
	Button dep = new Button("Deposit");
	Button balance = new Button("Balance");
	Button profile = new Button("Profile");

	ApplicationPage(String usr) {
		username = usr;
		fetching();
		l.setText("Welcome " + username);
		l.setBounds(200, 50, 150, 30);
		cash.setBounds(100, 120, 100, 40);
		trans.setBounds(250, 120, 100, 40);
		dep.setBounds(100, 200, 100, 40);
		balance.setBounds(250, 200, 100, 40);
		profile.setBounds(100, 270, 100, 40);

		dep.addActionListener(this);
		balance.addActionListener(this);
		cash.addActionListener(this);
		trans.addActionListener(this);
		profile.addActionListener(this);

		f1.add(l);
		f1.add(cash);
		f1.add(trans);
		f1.add(dep);
		f1.add(balance);
		f1.add(profile);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Account a1;
		PinEntry p;
		if (e.getSource() == balance) {
			a1 = new BalanceClass(a);
			p = new PinEntry(a, a1);

		} else if (e.getSource() == cash) {
			a1 = new Withdraw(a);
			p = new PinEntry(a, a1);

		} else if (e.getSource() == trans) {
			a1 = new Transfer(a);
			p = new PinEntry(a, a1);

		} else if (e.getSource() == dep) {
			a1 = new Deposit(a);
			p = new PinEntry(a, a1);

		} else if (e.getSource() == profile) {
			a1 = new Profile(a);
			p = new PinEntry(a, a1);

		}
		f1.setVisible(false);

	}

	public void fetching() {
		System.out.println(username);
		try {
			ps = con.prepareStatement("select * from acc where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				a.setAccno(rs.getInt(1));
				a.setUsername(rs.getString(2));
				a.setBalance(rs.getDouble(3));
				a.setPin(rs.getInt(4));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
