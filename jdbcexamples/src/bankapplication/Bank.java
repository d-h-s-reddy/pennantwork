package bankapplication;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Bank extends Frame {
	Bank() {
		setTitle("Bank Application");
		setSize(500, 500);
		setLayout(null);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
