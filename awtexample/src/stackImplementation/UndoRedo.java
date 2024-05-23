package stackImplementation;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

public class UndoRedo extends Frame {
	TextArea ta;
	Button undo;
	Button redo;
	Label l = new Label();
	Stack<String> un = new Stack<>();
	Stack<String> re = new Stack<>();
	Label l1 = new Label();

	UndoRedo() {
		ta = new TextArea();
		undo = new Button("Undo");
		redo = new Button("Redo");

		l.setText("Undo and Redo Operation");
		l.setBounds(200, 50, 150, 30);
		l.setBackground(Color.white);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		ta.setBounds(100, 100, 300, 200);
		undo.setBounds(130, 350, 80, 30);
		redo.setBounds(250, 350, 80, 30);
		add(ta);
		add(l);
		add(undo);
		add(redo);

		// adding the actionListeners
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!un.isEmpty()) {
					remove(l1);
					re.push(ta.getText());
					ta.setText(un.pop());
				} else {
					un.push(ta.getText());
					System.out.println(un);
				}
			}
		});
		redo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!re.isEmpty()) {
					remove(l1);
					un.push(ta.getText());
					ta.setText(re.pop());
				} else {
					l1.setText("This operation is not possible for starting");
					l1.setBounds(200, 400, 200, 30);
					l1.setBackground(Color.white);
					add(l1);
				}

			}
		});
		setBackground(Color.darkGray);
		setTitle("Undo and redo");
		setSize(600, 600);
		setLayout(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		UndoRedo ur = new UndoRedo();
	}

}
