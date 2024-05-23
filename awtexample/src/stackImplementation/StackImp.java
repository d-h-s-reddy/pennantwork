package stackImplementation;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

public class StackImp extends Frame {
	TextField tf;
	Button add;
	Button goback;
	Button goforward;
	Button bstack;
	Stack<String> s = new Stack<>();
	Stack<String> s1 = new Stack<>();
	Label l = new Label();
	Label l1 = new Label();
	Font font = new Font("Courier", Font.BOLD, 14);

	StackImp() {
		l1.setText("URL Collection");
		l1.setBounds(200, 50, 80, 30);
		l1.setBackground(Color.white);
		tf = new TextField();
		tf.setBounds(140, 100, 200, 30);
		add = new Button("add");
		add.setBounds(220, 150, 50, 30);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		goback = new Button("Go Back");
		goforward = new Button("Go forward");
		bstack = new Button("Print");
		goback.setBounds(130, 200, 100, 30);
		goforward.setBounds(250, 200, 100, 30);
		bstack.setBounds(370, 200, 100, 30);
		Performance p = new Performance();
		add.addActionListener(p);
		bstack.addActionListener(p);
		goforward.addActionListener(p);
		goback.addActionListener(p);

		add(goback);
		add(goforward);
		add(tf);
		add(add);
		// add(bstack);
		add(l1);
		setSize(500, 500);
		setBackground(Color.darkGray);
		setLayout(null);
		setVisible(true);

	}

	class Performance implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == add) {
				s.add(tf.getText());
				tf.setText("");

			} else if (e.getSource() == goback) {
				if (s.size() == 0) {
					l.setText("There is no url to goback");
				} else {
					String str = s.pop();
					s1.add(str);
					if (s.size() == 0) {
						l.setText("There is no url to goback");
					} else {
						l.setText(s.peek());
					}
				}
				l.setFont(font);
				l.setBackground(Color.white);

			} else if (e.getSource() == goforward) {
				if (s1.size() == 0) {
					l.setText("there is no forward url");
				} else {
					String str = s1.pop();
					s.add(str);
					l.setText(s.peek());
				}
				l.setFont(font);
				l.setBackground(Color.white);

			} else if (e.getSource() == bstack) {
				System.out.println("The s stack is:" + s);
				System.out.println("The s1 stack is:" + s1);

			}
			l.setBounds(100, 150, 200, 30);
			add(l);
		}
	}

	public static void main(String[] args) {
		StackImp s = new StackImp();
	}

}
