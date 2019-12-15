
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

//This page is the main page.
/**
 * This class is the home page of the user GUI.
 * The home page has 3 buttons, each of them represents a station, The available scooters in each station are printed on the relative button respectively.
 *
 * @author Group 44
 * @version 3.1
 */
public class UserGUI_HomePage extends JFrame implements ActionListener{
	public JButton AStation, BStation, CStation;
	public JLabel id_input;
	public JTextField id_text;
	public JLabel label;
	public JPanel panel1, panel2; 
	//!!!get the number of available scooters in slotNum_A,B,C

	public int slotNum_A =0;
	public int slotNum_B =0;
	public int slotNum_C =0;
	public int checkStationButton;
	
	public UserGUI_HomePage() {
		ParkinglotIO demo=new ParkinglotIO();
		ParkingLot QM=new ParkingLot();
		try {
			QM=demo.parkingLotInput();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		slotNum_A=QM.getBikeInA();
		slotNum_B=QM.getBikeInB();
		slotNum_C=QM.getBikeInC();
		this.setTitle("Hello");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel1.setLayout(new GridLayout(4,1,10,10));
		
		label = new JLabel("please choose a scooter station");
		panel1.add(label);
		
		AStation = new JButton("Station A: " + slotNum_A + "/8");
		AStation.addActionListener(this);
		panel1.add(AStation);
		
		BStation = new JButton("Station B: " + slotNum_B + "/8");
		BStation.addActionListener(this);
		panel1.add(BStation);
		
		CStation = new JButton("Station C: " + slotNum_C + "/8");
		CStation.addActionListener(this);
		panel1.add(CStation);
		
		this.add(panel1, BorderLayout.CENTER);
		this.add(panel2,BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();
		if (eventSource == AStation) {
			this.dispose();
			//buttonChecker1.setButtonCheck(1);
			//System.out.println("CheckStationButton "+~~~~~~~buttonChecker1.getButtonCheck());
			GUI_InputBox IDNum_A = new GUI_InputBox(slotNum_A,1,true);
		}
		if (eventSource == BStation) {
			this.dispose();
			//buttonChecker1.setButtonCheck(2);
			//System.out.println("CheckStationButton "+~~~~~~~buttonChecker1.getButtonCheck());
			GUI_InputBox IDNum_B = new GUI_InputBox(slotNum_B,2,true);
		}
		if (eventSource == CStation) {
			this.dispose();
			//buttonChecker1.setButtonCheck(3);
			//System.out.println("CheckStationButton "+~~~~~~~buttonChecker1.getButtonCheck());
			GUI_InputBox IDNum_C = new GUI_InputBox(slotNum_C,3,true);
		}
	}
	
	public static void main(String[] args) {
		UserGUI_HomePage login = new UserGUI_HomePage();
		
	}

}
