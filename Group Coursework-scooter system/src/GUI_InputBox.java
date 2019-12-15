
import java.io.IOException;
import java.io.*;
import java.io.Serializable;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Calendar;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;

//this page is used to enter ID number.
/**
 * This part contains a series of GUIs before users pick up or return scooters, and administrators create accounts for students.
 * Users and administrators should input texts in a correct form so that they can pick up/ return a scooter or create accounts.
 * @author Group 44
 * @version 1.2
 */
public class GUI_InputBox extends CheckInput implements ActionListener {
	public JPanel IDpanel1;
	public JLabel IDlabel1;
	public JTextField IDtext;
	public JButton login, back_login, check, back_check;
	private Object[] options = { "OK" };
	private Object[] option1 = { "pay now", "back-out" };
	private String idNumberBuffer;
	private int available1;// the number of scooter in each station
	private Student student = new Student();
	private StudentIO io = new StudentIO();
	private int buttonChecker;
	private JFrame inputFrame;
	int total = 8;

	public GUI_InputBox(int Available, int buttonCheck, boolean isLogin) {
		buttonChecker = buttonCheck;
		available1 = Available;
		inputFrame=new JFrame();
		inputFrame.setTitle("Log in");
		inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = inputFrame.getContentPane();
		c.setLayout(new BorderLayout());

		IDpanel1 = new JPanel();
		IDpanel1.setLayout(new GridLayout(4, 2, 10, 10));
		IDlabel1 = new JLabel("please input campus ID number");
		IDpanel1.add(IDlabel1);
		IDtext = new JTextField(16);
		IDpanel1.add(IDtext);

		if (isLogin) {
			login = new JButton("Log in");
			login.addActionListener(this);
			IDpanel1.add(login);
			back_login = new JButton("Back");
			back_login.addActionListener(this);
			IDpanel1.add(back_login);
		} else {
			check = new JButton("Check User");
			check.addActionListener(this);
			IDpanel1.add(check);
			back_check = new JButton("Back");
			back_check.addActionListener(this);
			IDpanel1.add(back_check);
		}

		c.add(IDpanel1, BorderLayout.CENTER);
		inputFrame.pack();
		inputFrame.setSize(300,200);
		inputFrame.setLocationRelativeTo(null);
		inputFrame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();
		if (eventSource == login || eventSource == check) {
			File file = new File("" + IDtext.getText());
			idNumberBuffer = IDtext.getText();
			if (IDtext.getText().isEmpty()) {
				JOptionPane.showOptionDialog(null, "Please input your ID number!", "Please enter again",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else if (isNumeric(IDtext.getText())) {
				JOptionPane.showOptionDialog(null, "You input ID number should be digit", "Please enter again",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			}

			else if (file.exists() == false)// check if the user ID is in database
			{
				JOptionPane.showOptionDialog(null, "The user's ID is not in the database", "Please enter again",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else if (IDtext.getText().length() != 9) {

				JOptionPane.showOptionDialog(null, "You input ID number is not correct", "Please use your QM number",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else {
				if (eventSource == login) {
					CheckFine check=new CheckFine();
					check.checkDifferentDay(idNumberBuffer);
					try {
						student = io.studentInput(idNumberBuffer);
					} catch (ClassNotFoundException | IOException ex) {
						ex.printStackTrace();
					}
					boolean pickUp = student.checkStatus();
					if (pickUp)// pickup scenario
					{

						if (available1 == 0)// if no available scooter
						{
							JOptionPane.showOptionDialog(null, "This station has no scooter!", "Please try again",
									JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

							inputFrame.dispose();
							UserGUI_HomePage login = new UserGUI_HomePage();
							login.pack();
							login.setVisible(true);
							login.setSize(300, 200);
							login.setLocationRelativeTo(null);
						} else// has available scooters
						{
							// check if the user has used more than 2 hours.
							// !!!check if he has unpaid fine .
							if (!(student.getFine())) {
								int op = JOptionPane.showOptionDialog(null,
										"You have unpaid finement! Please pay the finement: $100.", "finement message",
										JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, option1,
										option1[0]);
								if (op == 0) {
									student.setFine(true);
									io.studentOutput(student);
									if (student.getTotalTime() >= 120) {
										JOptionPane.showOptionDialog(null, "You have used more than 2 hour today.",
												"Ban!!!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
												options, options[0]);
										UserGUI_HomePage login = new UserGUI_HomePage();
										login.pack();
										login.setVisible(true);
										login.setSize(300, 200);
										login.setLocationRelativeTo(null);
										inputFrame.dispose();
									} else {
										inputFrame.dispose();// destroy the current page
										new UserGUI_PickUp(available1, total, idNumberBuffer, buttonChecker);
									}
								} else {
									UserGUI_HomePage login = new UserGUI_HomePage();
									login.pack();
									login.setVisible(true);
									login.setSize(300, 200);
									login.setLocationRelativeTo(null);
									inputFrame.dispose();
								}
							} else {
								if (student.getTotalTime() >= 120) {
									JOptionPane.showOptionDialog(null, "You have used more than 2 hour today.",
											"Ban!!!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
											options, options[0]);
									inputFrame.dispose();// destroy the current page
									UserGUI_HomePage login = new UserGUI_HomePage();
									login.pack();
									login.setVisible(true);
									login.setSize(300, 200);
									login.setLocationRelativeTo(null);
								} else {
									inputFrame.dispose();// destroy the current page
									new UserGUI_PickUp(available1, total, idNumberBuffer, buttonChecker);
								}
							}
						}
					} else// return scenario
					{

						if (total - available1 == 0)// if no available slots
						{
							int op = JOptionPane.showOptionDialog(null, "This station has no slot!", "Please try again",
									JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
							if (op == 0) {
								inputFrame.dispose();
								UserGUI_HomePage login = new UserGUI_HomePage();
								login.pack();
								login.setVisible(true);
								login.setSize(300, 200);
								login.setLocationRelativeTo(null);
							}
						} else// have available slot
						{
							inputFrame.dispose();// destroy the current page
							new UserGUI_Return(available1, total, idNumberBuffer, buttonChecker);
						}
					}

				} else if (eventSource == check) {
					AdministratorGUI_UserReport UserReport = new AdministratorGUI_UserReport(idNumberBuffer);// 为跳转的界面
					inputFrame.dispose();// destroy the current page
				}
			}
		} else if (eventSource == back_login) {
			UserGUI_HomePage back = new UserGUI_HomePage();
			back.pack();
			back.setSize(300, 200);
			back.setLocationRelativeTo(null);
			back.setVisible(true);
			inputFrame.dispose();// destroy the current page
		} else if (eventSource == back_check) {
			AdministratorGUI_HomePage back = new AdministratorGUI_HomePage();//jump to another frame.
			back.pack();
			back.setSize(300, 200);
			back.setLocationRelativeTo(null);
			back.setVisible(true);
			inputFrame.dispose();// destroy the current page
		}
	}

}
