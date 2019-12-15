
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
import java.awt.*;

/**
 * This part matches to the part which the administrator clicks the button which
 * name is "Register". The administrator should input the user information
 * correctly so that he or she can successfully create a student account.
 * 
 * @author Group 44
 * @version 3.1
 */
public class AdministratorGUI_Register extends CheckInput implements ActionListener {
	private JButton register, back;
	private JLabel id_label, name_label, email_label;
	private JTextField id_text, name_text, email_text;
	private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7;
	private String idBuffer, nameBuffer, emailBuffer;
	private Object[] options = { "OK" };
	private Student student = new Student();
	private JFrame registerFrame;
	private StudentIO io = new StudentIO();

	public AdministratorGUI_Register() {
		registerFrame = new JFrame();
		registerFrame.setTitle("Administrator");
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = registerFrame.getContentPane();
		c.setLayout(new BorderLayout());

		panel1 = new JPanel();
		// panel1.setPreferredSize(new Dimension(400, 600));
		// panel1.setSize(15,20);
		panel1.setLayout(new GridLayout(4, 2, 10, 10));
		id_label = new JLabel("ID Numer:");
		panel1.add(id_label);
		id_text = new JTextField(8);
		panel1.add(id_text);
		name_label = new JLabel("      Name:");
		panel1.add(name_label);
		name_text = new JTextField(8);
		panel1.add(name_text);
		email_label = new JLabel("      Email:");
		panel1.add(email_label);
		email_text = new JTextField(8);
		panel1.add(email_text);

		// panel2=new JPanel();

		// panel2.setPreferredSize(new Dimension(1, 1));
		// panel2.setSize(1,1);
		register = new JButton("Register");
		register.addActionListener(this);
		panel1.add(register);
		back = new JButton("Back");
		back.addActionListener(this);
		panel1.add(back);
		// panel2.setLayout(new GridLayout(0,2,10,10));

		// panel7=new JPanel();
		// panel7.add(panel1);
		// panel7.add(panel2);
		// panel7.setLayout(new GridLayout(2,1,10,10));

		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		c.add(panel1, BorderLayout.CENTER);
		c.add(panel6, BorderLayout.SOUTH);
		c.add(panel3, BorderLayout.WEST);
		c.add(panel4, BorderLayout.EAST);
		c.add(panel5, BorderLayout.NORTH);

		registerFrame.pack();
		registerFrame.setSize(300, 200);
		registerFrame.setLocationRelativeTo(null);
		registerFrame.setVisible(true);

	}

	/**
	 * The action listener, which contains the checking method to judge if the
	 * administrator inputs the correct form of each information.
	 * 
	 * @param e
	 *            the action event: when user click a button.
	 */
	public void actionPerformed(ActionEvent e) {

		JButton eventSource = (JButton) e.getSource();
		if (eventSource == register) {
			File file = new File("" + id_text.getText());
			if (id_text.getText().isEmpty() || name_text.getText().isEmpty() || email_text.getText().isEmpty())
			{
				JOptionPane.showOptionDialog(null, "You information is not fill out completely", "Please enter again",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else if ((Character.isLowerCase(name_text.getText().charAt(0))))// check input format
			{
				JOptionPane.showOptionDialog(null, "Your name should be started with a upper case. e.g.:Shenlinger",
						"Please enter again", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
						options[0]);
			} else if (isAllWords(name_text.getText())) {
				JOptionPane.showOptionDialog(null, "Your name should not contain a digit. e.g.:Shenlinger",
						"Please enter again", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
						options[0]);
			} else if ((isNumeric(id_text.getText())) || (id_text.getText().length() != 9)) {
				JOptionPane.showOptionDialog(null, "Your id should be a 9-digit number. e.g.:161197197",
						"Please enter again", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
						options[0]);
			} else if (file.exists() == true)// check if the user ID is in database
			{
				JOptionPane.showOptionDialog(null, "You have already registered with this ID.", "Please enter another ID.",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else if (validateEmail(email_text.getText())) {
				JOptionPane.showOptionDialog(null, "Your email format is not valid. e.g.:161197197@163.com",
						"Please enter again", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
						options[0]);
			}

			/*
			 * File file = new File(id_text.getText());
			 * System.out.println(id_text.getText()); System.out.println(file.exists());
			 * else if (new File(id_text.getText()).exists()==false)//check if the user ID
			 * is in database {
			 * JOptionPane.showOptionDialog(null,"Your information has met the requirements"
			 * ,"Keep going",JOptionPane.DEFAULT_OPTION,
			 * JOptionPane.INFORMATION_MESSAGE,null,options,options[0]); }
			 */

			else {

				idBuffer = id_text.getText();
				nameBuffer = name_text.getText();
				emailBuffer = email_text.getText();
				Student student = new Student();
				student.setQMNum(idBuffer);
				student.setStudentName(nameBuffer);
				student.setEmail(emailBuffer);
				io.studentOutput(student);
				JOptionPane.showOptionDialog(null, "Successfully register", "success", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				System.out.println("idBuffer=" + idBuffer);
				System.out.println("nameBuffer=" + nameBuffer);
				System.out.println("emailBuffer=" + emailBuffer);
				AdministratorGUI_HomePage main = new AdministratorGUI_HomePage();
				main.pack();
				main.setSize(300, 200);
				main.setLocationRelativeTo(null);
				main.setVisible(true);
				registerFrame.dispose();
				/*
				 * try{ IOTest.studentInput(); }catch(ClassNotFoundException | IOException ex){
				 * ex.printStackTrace(); } System.out.println(IOTest.student1.getQMNum());
				 * System.out.println(IOTest.student1.getStudentName());
				 * System.out.println(IOTest.student1.getEmail());
				 * System.out.println(IOTest.student1.getCurrentTime());
				 * System.out.println(IOTest.student1.getTotalTime());
				 * System.out.println(IOTest.student1.getDifferentDay());
				 * System.out.println(IOTest.student1.getDataMonth());
				 * System.out.println(IOTest.student1.getDataDay());
				 */
				// Sucessfully log in (JOptionPane)

			}

		} else if (eventSource == back) {
			AdministratorGUI_HomePage main = new AdministratorGUI_HomePage();// 为跳转的界面
			main.pack();
			main.setSize(300, 200);
			main.setLocationRelativeTo(null);
			main.setVisible(true);
			registerFrame.dispose();

		}

	}

	/*
	 * public static void main(String[] args) { Admistrator_Register myDemo = new
	 * Admistrator_Register(); myDemo.pack(); myDemo.setVisible(true); }
	 */

}
