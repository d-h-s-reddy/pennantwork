package bankapplication;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BalanceClass extends Account {
	Account a1;
	Bank f = new Bank();
	Label l = new Label();
	Button home = new Button("Home");

	BalanceClass(Account a1) {
		this.a1 = a1;

	}

	public void balanceDisplay() {
		l.setText("The balance of your account:" + a1.getAccno() + " is:" + a1.getBalance());
		l.setBounds(100, 150, 300, 30);
		f.add(l);
		home.setBounds(200, 200, 50, 30);
		f.add(home);
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				ApplicationPage p = new ApplicationPage(a1.getUsername());

			}

		});
	}
}
