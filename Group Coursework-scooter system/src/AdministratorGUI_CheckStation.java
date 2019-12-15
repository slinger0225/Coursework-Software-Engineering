

import javax.swing.*;
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
import java.util.Calendar;



/**
 *  This part matches to the part which the administrator clicks the button which name is "check station's situation".
 *	it will print how many scooters are in each station and how many scooters are on the way.
 * @author Group 44
 * @version 3.1
 */
public class AdministratorGUI_CheckStation extends CheckStationReport implements ActionListener {
	private JButton back;
	private JTextArea report;
	private JPanel panel1,panel2;
	private JFrame checkSta;
	
	public AdministratorGUI_CheckStation() {
		checkSta=new JFrame();
		Container c = checkSta.getContentPane();
		c.setLayout(new BorderLayout());

		panel1=new JPanel();
		//panel1.setPreferredSize(new Dimension(400, 600));
		//panel1.setSize(15,20);
		CheckStaionReport();
		report = new JTextArea(5,20);
		report.setLineWrap(true);        //break into a new line automatically
		report.setWrapStyleWord(true);
		report.setEditable(false);
		report.setText(stationReport);  //output string
		panel1.add(new JScrollPane(report));


		panel2=new JPanel();
		//panel2.setPreferredSize(new Dimension(1, 1));
		//panel2.setSize(1,1);
		back=new JButton("Back");
		back.addActionListener(this);
		panel2.add(back);

		c.add(panel1,BorderLayout.CENTER);
		c.add(panel2,BorderLayout.SOUTH);

		checkSta.setTitle("Administrator");
		checkSta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkSta.pack();
		checkSta.setSize(300, 200);
		checkSta.setLocationRelativeTo(null);
		checkSta.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();
		if (eventSource == back)
		{
			AdministratorGUI_HomePage main = new AdministratorGUI_HomePage();//jump to another frame.
			main.pack();
			main.setSize(300, 200);
			main.setLocationRelativeTo(null);
			main.setVisible(true);
			checkSta.dispose();//destroy the current page
		}

	}
}
