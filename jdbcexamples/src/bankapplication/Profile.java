package bankapplication;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Profile extends Account {
	Account a;
	Bank b = new Bank();
	ConnectionDatabase c = new ConnectionDatabase();
	Connection con = c.ConnectionDatabases();
	PreparedStatement ps;
	ResultSet rs;

	Label name, usern, phoneno, address, ndis, nuser, nph, nadd;
	Button home = new Button("Home");

	Profile(Account a) {
		this.a = a;
	}

	public void profile() {
		try {
			ps = con.prepareStatement("select * from profile where username=?");
			ps.setString(1, a.getUsername());
			rs = ps.executeQuery();
			rs.next();

			name = new Label("Name of the Account Holder:");
			usern = new Label("User name of the Account Holder:");
			phoneno = new Label("Phone number is:");
			address = new Label("Address is:");
			ndis = new Label(rs.getString(2));
			nuser = new Label(rs.getString(3));
			nph = new Label(rs.getString(4));
			nadd = new Label(rs.getString(5));
			name.setBounds(50, 50, 150, 30);
			ndis.setBounds(210, 50, 100, 30);
			usern.setBounds(50, 100, 200, 30);
			nuser.setBounds(250, 100, 100, 30);
			phoneno.setBounds(50, 150, 150, 30);
			nph.setBounds(210, 150, 100, 30);
			address.setBounds(50, 200, 150, 30);
			nadd.setBounds(210, 200, 100, 30);
			b.add(name);
			b.add(ndis);
			b.add(usern);
			b.add(nuser);
			b.add(phoneno);
			b.add(nph);
			b.add(address);
			b.add(nadd);

			// button
			home.setBounds(150, 250, 50, 30);
			b.add(home);
			home.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					b.setVisible(false);
					ApplicationPage p = new ApplicationPage(a.getUsername());

				}

			});

		} catch (Exception e1) {
			System.out.println(e1);
		}
	}
}
