package crudoperations;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class CrudData implements ActionListener {
	FrameData f = new FrameData();

	Label title = new Label("Crud Operations");
	Label lno = new Label("Empno");
	TextField tno = new TextField();
	Label lname = new Label("EmpName");
	TextField tname = new TextField();
	Label ljob = new Label("Job");
	TextField tjob = new TextField();
	Label lsal = new Label("Salary");
	TextField tsal = new TextField();
	Label ldeptno = new Label("Deptno");
	Choice tdeptno = new Choice();
	Label lhdate = new Label("HireDate");
	TextField thdate = new TextField();
	Label err = new Label("There is not data to be displayed");
	// buttons
	Button first = new Button("First");
	Button next = new Button("Next");
	Button prev = new Button("Prev");
	Button last = new Button("Last");
	Button add = new Button("Add");
	Button edit = new Button("Edit");
	Button del = new Button("Del");
	Button save = new Button("Save");
	Button clear = new Button("Clear");
	Button exit = new Button("Exit");

	int selectedRow = -1;
	JTable jt;
	JScrollPane scrollPane;
	int rowcount;
	String sql = "";
	Retrivingdata d = new Retrivingdata();

	public void mainframe() {
		title.setBounds(430, 50, 100, 30);
		lno.setBounds(250, 100, 50, 30);
		tno.setBounds(340, 100, 100, 30);
		lname.setBounds(500, 100, 70, 30);
		tname.setBounds(590, 100, 100, 30);

		ljob.setBounds(250, 150, 50, 30);
		tjob.setBounds(340, 150, 100, 30);
		lsal.setBounds(500, 150, 70, 30);
		tsal.setBounds(590, 150, 100, 30);

		ldeptno.setBounds(250, 200, 50, 30);
		tdeptno.setBounds(340, 200, 100, 30);
		lhdate.setBounds(500, 200, 70, 30);
		thdate.setBounds(590, 200, 100, 30);

		// buttons
		first.setBounds(250, 250, 70, 30);
		next.setBounds(350, 250, 70, 30);
		prev.setBounds(450, 250, 70, 30);
		last.setBounds(550, 250, 70, 30);
		add.setBounds(250, 300, 70, 30);
		edit.setBounds(350, 300, 70, 30);
		del.setBounds(450, 300, 70, 30);
		save.setBounds(550, 300, 70, 30);
		clear.setBounds(350, 350, 70, 30);
		exit.setBounds(450, 350, 70, 30);

		f.add(title);
		f.add(lno);
		f.add(tno);
		f.add(lname);
		f.add(tname);
		f.add(ljob);
		f.add(tjob);
		f.add(lsal);
		f.add(tsal);
		f.add(ldeptno);
		f.add(first);
		f.add(tdeptno);
		f.add(lhdate);
		f.add(thdate);
		f.add(first);
		f.add(next);
		f.add(prev);
		f.add(last);
		f.add(add);
		f.add(edit);
		f.add(del);
		f.add(save);
		f.add(clear);
		f.add(exit);

		// jtable;
		tabledisplaying();

	}

	public void tabledisplaying() {
		d.retriving();
		ArrayList<Employee> li = new ArrayList();
		li = d.al;
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Empno");
		model.addColumn("empname");
		model.addColumn("job");
		model.addColumn("salary");
		model.addColumn("deptno");
		model.addColumn("hiredate");

		for (Employee e : li) {
			model.addRow(new Object[] { e.empno, e.empname, e.job, e.Salary, e.depnum, e.hdate });

		}

		jt = new JTable(model);
		scrollPane = new JScrollPane(jt);
		scrollPane.setBounds(250, 400, 500, 200);
		f.add(scrollPane);
		rowcount = model.getRowCount();

		jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					selectedRow = jt.getSelectedRow();
					if (selectedRow != -1) {
						err.setText("");
						tdeptno.removeAll();
						tno.setText((int) jt.getValueAt(selectedRow, 0) + "");
						tname.setText((String) jt.getValueAt(selectedRow, 1));
						tjob.setText((String) jt.getValueAt(selectedRow, 2));
						tsal.setText((double) jt.getValueAt(selectedRow, 3) + "");
						thdate.setText(jt.getValueAt(selectedRow, 5) + "");
						tdeptno.add((int) jt.getValueAt(selectedRow, 4) + "");

					}
				}
			}
		});

	}

	CrudData() {
		first.addActionListener(this);
		next.addActionListener(this);
		prev.addActionListener(this);
		last.addActionListener(this);
		add.addActionListener(this);
		edit.addActionListener(this);
		del.addActionListener(this);
		save.addActionListener(this);
		clear.addActionListener(this);
		exit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == first) {
			selectedRow = 0;
			displayingdata(selectedRow);
		} else if (e.getSource() == next) {
			selectedRow++;
			displayingdata(selectedRow);
		} else if (e.getSource() == prev) {
			selectedRow--;
			displayingdata(selectedRow);
		} else if (e.getSource() == last) {
			selectedRow = rowcount - 1;
			displayingdata(selectedRow);
		} else if (e.getSource() == add) {
			tno.setText("");
			tname.setText("");
			tjob.setText("");
			tsal.setText("");
			thdate.setText("");
			tdeptno.removeAll();
		} else if (e.getSource() == edit) {
			sql = "update emp185 set emp_name='" + tname.getText() + "' where emp_num="
					+ Integer.parseInt(tno.getText()) + "";
			d.updating(sql);
			f.remove(scrollPane);
			tabledisplaying();
		} else if (e.getSource() == del) {
			sql = "delete from emp185 where emp_num=" + Integer.parseInt(tno.getText()) + "";
			d.deleting(sql);
			f.remove(scrollPane);
			tabledisplaying();
		} else if (e.getSource() == save) {

			sql = "insert into emp185 values(" + Integer.parseInt(tno.getText()) + ",'" + tname.getText() + "','"
					+ tjob.getText() + "'," + Double.parseDouble(tsal.getText()) + ","
					+ Integer.parseInt(tdeptno.getSelectedItem()) + ",'" + thdate.getText() + "')";
			d.adding(sql);
			f.remove(scrollPane);
			tabledisplaying();
		} else if (e.getSource() == clear) {
			tno.setText("");
			tname.setText("");
			tjob.setText("");
			tsal.setText("");
			thdate.setText("");
			tdeptno.removeAll();
		} else if (e.getSource() == exit) {
			f.dispose();
		}

	}

	public void displayingdata(int SelectedRow) {
		if (selectedRow >= rowcount || selectedRow <= -1) {
			tno.setText("");
			tname.setText("");
			tjob.setText("");
			tsal.setText("");
			thdate.setText("");
			tdeptno.removeAll();
			err.setText("There is not data to be displayed");
			err.setBounds(300, 600, 200, 30);
			f.add(err);
		}

		else {
			err.setText("");
			tdeptno.removeAll();
			tno.setText((int) jt.getValueAt(selectedRow, 0) + "");
			tname.setText((String) jt.getValueAt(selectedRow, 1));
			tjob.setText((String) jt.getValueAt(selectedRow, 2));
			tsal.setText((double) jt.getValueAt(selectedRow, 3) + "");
			thdate.setText(jt.getValueAt(selectedRow, 5) + "");
			tdeptno.add((int) jt.getValueAt(selectedRow, 4) + "");
		}

	}

	public static void main(String[] args) {
		CrudData c = new CrudData();
		c.mainframe();

	}

}