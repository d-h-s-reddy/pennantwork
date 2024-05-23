package bankapplication;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinEntry {
	Bank b = new Bank();
	Label l = new Label("Enter the four digit Pin number:");
	TextField tf = new TextField();
	Label l1 = new Label("Reenter the pin number");
	Button sub = new Button("Submit");

	Account a;
	Account a1;

	PinEntry(Account a, Account a1) {
		this.a = a;
		this.a1 = a1;

		l.setBounds(100, 100, 180, 30);
		tf.setBounds(290, 100, 60, 25);
		tf.setEchoChar('*');
		sub.setBounds(200, 150, 50, 30);

		sub.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int pin = Integer.parseInt(tf.getText());
				System.out.println(a.getPin());
				if (pin == a.getPin()) {
					checking(a1);
					b.setVisible(false);
				} else {
					l1.setBounds(170, 200, 150, 30);
					b.add(l1);
				}

			}

		});

		b.add(l);
		b.add(tf);
		b.add(sub);

	}

	public void checking(Account a1) {
		if (a1 instanceof BalanceClass) {
			BalanceClass b = (BalanceClass) a1;
			b.balanceDisplay();
		} else if (a1 instanceof Deposit) {
			Deposit d = (Deposit) a1;
			d.deposit();
		} else if (a1 instanceof Withdraw) {
			Withdraw w = (Withdraw) a1;
			w.withdraw();
		} else if (a1 instanceof Transfer) {
			Transfer t = (Transfer) a1;
			t.transfer();
		} else if (a1 instanceof Profile) {
			Profile p = (Profile) a1;
			p.profile();
		}
	}

}
