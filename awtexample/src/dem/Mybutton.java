package dem;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class somerandom implements ActionListener {
	Frame f1 = new Frame();

	somerandom(Frame f) {

		f1 = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("re");
		System.out.println("hi");
		Label l = new Label();
		l.setText("hello world");
		l.setBounds(200, 200, 30, 40);
		l.setBackground(Color.red);
		f1.add(l);

	}

}

class somerandom2 implements ActionListener {
	Frame f1 = new Frame();

	somerandom2(Frame f) {

		f1 = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("re");
		System.out.println("hi");
		Label l = new Label();
		l.setText("hello ");
		l.setBounds(300, 200, 30, 40);
		l.setBackground(Color.cyan);
		f1.add(l);

	}

}

public class Mybutton {
	public static void main(String[] args) {
		Frame f = new Frame();
		Button b = new Button("CLICK ME");
		Button b1 = new Button("Save");
		b.setBounds(160, 80, 80, 40);
		b.setBackground(Color.blue);
		b1.setBounds(300, 80, 80, 40);
		// actionlistner
		somerandom s = new somerandom(f);
		b.addActionListener(s);
		somerandom2 s1 = new somerandom2(f);
		b1.addActionListener(s1);

		f.add(b);
		f.add(b1);
		f.setBackground(Color.gray);
		f.setSize(400, 300);
		f.setLayout(null);
		f.setVisible(true);

	}
}
