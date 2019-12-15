
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

//This page is the home page for administrator.
/**
 * This is the home page for administrators.
 * It contains three buttons, by clicking each of them there will be a jump to other pages respectively.
 *
 * @author Group 44
 * @version 3.1
 */
public class AdministratorGUI_HomePage extends JFrame implements ActionListener {
	public JButton registerButton, checkUserButton,checkStationButton;
	public JLabel label;
	int number = 0;
	public JPanel Admin_p1,Admin_p2,Admin_p3,Admin_p4;


	public AdministratorGUI_HomePage() {
		this.setTitle("Administrator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		Admin_p1=new JPanel();
		Admin_p2=new JPanel();
		Admin_p3=new JPanel();
		Admin_p4=new JPanel();
		Admin_p1.setLayout(new GridLayout(4,1,10,10));

		label = new JLabel("Please select an option.");
		Admin_p1.add(label);
		//this.getContentPane().add(label);
		//this.add(Admin_p1);

		registerButton = new JButton("Register");
		registerButton.addActionListener(this);
		//this.getContentPane().add(registerButton);
		Admin_p1.add(registerButton);
		//this.add(Admin_p2);

		checkUserButton= new JButton("Check user's usage");
		checkUserButton.addActionListener(this);
		//this.getContentPane().add(checkUserButton);
		Admin_p1.add(checkUserButton);
		//this.add(Admin_p3);

		checkStationButton= new JButton("Check station's situation");
		checkStationButton.addActionListener(this);
		//this.getContentPane().add(checkStationButton);
		Admin_p1.add(checkStationButton);
		//this.add(Admin_p4);

		this.add(Admin_p1,BorderLayout.CENTER);
		this.add(Admin_p2,BorderLayout.SOUTH);
		this.add(Admin_p3,BorderLayout.WEST);
		this.add(Admin_p4,BorderLayout.EAST);
		this.pack();
		this.setVisible(true);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
	}

	/**
	 * The action listener.
	 * @param e the action event: when user click a button.
	 */
	public void actionPerformed(ActionEvent e) {
		JButton eventSource = (JButton) e.getSource();
		if (eventSource == registerButton) {
			AdministratorGUI_Register register = new AdministratorGUI_Register();//jump to another frame.
			this.dispose();//destroy the current page

		}
		else if (eventSource == checkUserButton) {
			GUI_InputBox inputbox = new GUI_InputBox(0,0,false);//jump to another frame.
			this.dispose();//destroy the current page
		}
		else if (eventSource == checkStationButton) {
			AdministratorGUI_CheckStation checkStation = new AdministratorGUI_CheckStation();//jump to another frame.
			this.dispose();//destroy the current page
		}
	}

	public static void main(String[] args) {
		AdministratorGUI_HomePage main = new AdministratorGUI_HomePage();
	}
}
