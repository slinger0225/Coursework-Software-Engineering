
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.io.*;
import java.io.Serializable;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
/**
 *  This part matches to the part which the administrator clicks the button which name is "check user's usage".
 *  To check user's usage, the administrator must input the correct QM ID of the student which is already in the database.
 *  It also contains the page which name is "send user report".
 *  The administrator can send a massage to the user by clicking "send user report", a txt file will be created in the /src fold.
 *  The form of the name of the txt file is "UserReport_QMNumber"
 *
 * @author Group 44
 * @version 3.1
 */
public class AdministratorGUI_UserReport extends CheckWeeklyReport implements ActionListener {
	private JButton send, back;
	private JFrame userReport;
	private JTextArea report;
	JPanel panel1,panel2;
	private Object[] options= {"OK"};
	public AdministratorGUI_UserReport(String idNumber) {
		id=idNumber;
		userReport=new JFrame();
		Container panel = userReport.getContentPane();
		panel.setLayout(new BorderLayout());
		panel1=new JPanel();
		//panel1.setPreferredSize(new Dimension(400, 600));
		//panel1.setSize(15,20);
		report = new JTextArea(30,20);
		report.setLineWrap(true);        //break into a new line automatically
		report.setWrapStyleWord(true);
		report.setEditable(false);
		checkReport(new Comp7DaysFromNow());
		//checkReport(new CompGreaterthan5());
		report.setText("ID number:"+idNumber+"\n");
		report.append("---------Detail--------------\n");
		for (int i=0;i<outputList.size();i++) {
			report.append(outputList.get(i));
		}
		report.append("-----------------------------\n");
		report.append(summary);  //set the output

		panel1.add(new JScrollPane(report));


		panel2=new JPanel();
		//panel2.setPreferredSize(new Dimension(400, 100));
		//panel2.setSize(1,1);
		send=new JButton("Send weekly report");
		send.addActionListener(this);
		panel2.add(send);
		back=new JButton("Back");
		back.addActionListener(this);
		panel2.add(back);

		panel.add(panel1,BorderLayout.CENTER);
		panel.add(panel2,BorderLayout.SOUTH);


		userReport.setTitle("Administrator");
		userReport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userReport.pack();
		userReport.setSize(300, 400);
		userReport.setLocationRelativeTo(null);
		userReport.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();
		if (eventSource == send)
		{
			sendReport();
			JOptionPane.showOptionDialog(null,"The user report has been sent to the user","Successfully send out",JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);

		}
		else if (eventSource == back)
		{
			AdministratorGUI_HomePage main = new AdministratorGUI_HomePage();//jump to another page
			main.pack();
			main.setSize(300, 200);
			main.setLocationRelativeTo(null);
			main.setVisible(true);
			userReport.dispose();//destroy the current page
		}

	}
	/*
	public static void main(String[] args) {
		Admistrator_UserReport myDemo = new Admistrator_UserReport();
		myDemo.pack();
		myDemo.setVisible(true);
	}
	*/
}
