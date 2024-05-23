package demo;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Account {
	int balance = 10000;

	public void deposit(double amt) {
		balance += amt;
	}

	public void withdraw(double amt) {
		balance -= amt;
	}

	public int bal() {
		return balance;
	}

	public static void main(String[] args) {
		final Account a = new Account();
		final Frame f = new Frame();
		Label l = new Label();
		l.setText("Hi welcome ");
		Font font = new Font("Arial", Font.PLAIN, 20);
		l.setFont(font);
		l.setBounds(230, 50, 110, 40);
		l.setBackground(Color.white);

		// placing the checkboxes
		Panel panel = new Panel();
		CheckboxGroup group = new CheckboxGroup();
		final Checkbox ch1 = new Checkbox("Deposit", group, false);
		Checkbox ch2 = new Checkbox("Withdraw", group, false);
		Checkbox ch3 = new Checkbox("Balance", group, false);
		ch1.setBounds(230, 100, 100, 50);
		ch2.setBounds(230, 150, 100, 50);
		ch3.setBounds(230, 200, 100, 50);
		f.add(ch1);
		f.add(ch2);
		f.add(ch3);
		final TextField t1 = new TextField();
		final Label l1 = new Label();
		final Label l2 = new Label();
		final Button b = new Button("Submit");
		final Button b1 = new Button("Submit");

		ch1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				f.remove(l1);
				f.remove(t1);
				l1.setText("Enter the amount:");
				l1.setBounds(130, 250, 100, 30);
				t1.setBounds(230, 250, 100, 30);
				f.add(l1);
				f.add(t1);
				b.setBounds(350, 250, 50, 30);
				b.setBackground(Color.white);
				f.add(b);

				// button details
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int amt = Integer.parseInt(t1.getText());
						a.deposit(amt);
						t1.setText("");
						int l = a.bal();
						l2.setText("The Balance of the amount:" + l);
						l2.setBounds(220, 300, 200, 30);
						f.add(l2);
					}
				});

			}
		});
		ch2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				f.remove(l1);
				f.remove(t1);
				f.remove(l2);
				f.remove(b);

				l1.setText("Enter the amount to withdraw:");
				l1.setBounds(70, 250, 180, 30);
				t1.setBounds(250, 250, 100, 30);
				f.add(l1);
				f.add(t1);

				// button details
				b1.setBounds(370, 250, 50, 30);
				b1.setBackground(Color.white);
				f.add(b1);

				b1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int amt = Integer.parseInt(t1.getText());
						a.withdraw(amt);
						t1.setText("");
						int l = a.bal();
						l2.setText("The Balance of the amount:" + l);
						l2.setBounds(250, 300, 200, 30);
						f.add(l2);
					}
				});
			}
		});
		ch3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				f.remove(l1);
				f.remove(t1);
				f.remove(b);
				f.remove(b1);
				f.remove(l2);
				int l = a.bal();
				l1.setText("The Balance of the amount:" + l);
				l1.setBounds(230, 250, 200, 30);
				f.add(l1);
			}
		});

		f.add(l);
		f.setSize(600, 600);
		f.setBackground(Color.cyan);
		f.setLayout(null);
		f.setVisible(true);

	}

}
