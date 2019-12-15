package work;
/**
* Bank GUI is for both employee and customer, which is used as a boundary class to interact with the user.
*Demo--<b>BankSystem</b>
*
*@author shenlinger
*
*@version  2019/05/11
*
*/
import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory; 
import org.apache.commons.io.FileUtils;

public class BankGUI implements ActionListener {
	/**
	 * Declare component variables for GUI.
	 */
	private JFrame preFrame,empFrame,mainFrame,depositFrame,createFrame,withdrawFrame,closeFrame;
	private JButton pre_emp, pre_depo,pre_cus,createAcc, depositAcc, withdrawAcc,closeAcc,suspendAcc,submit_withdraw;
	private JLabel label,depoLabel_acc,depoLabel_num,withdrawLabel_acc,withdrawLabel_PIN,withdrawLabel_num,closeLabel_acc,closeLabel_PIN,createLabel_name,createLabel_add,createLabel_DOB,createLabel_credit;
	private JTextField depoInput_acc,depoInput_num,withdrawInput_acc,withdrawInput_PIN,withdrawInput_num,closeInput_acc,closeInput_PIN,createInput_name,createInput_add,createInput_DOB,createInput_credit;
	private JComboBox<String> selectDeposit,selectClose,selectAccType;
	private String withdrawInput_method;
	private String closeInput_method;
	private JTextField createInput_option = new JTextField(16);
	private JLabel createLabel_option=new JLabel();
	private JButton submit_create = new JButton("Create");
	private JButton back_create = new JButton("Back");
	private JButton back_close = new JButton("Back");
	private JButton back_deposit= new JButton("Back");
	private JButton back_withdraw = new JButton("Back");
	private JButton back_emp = new JButton("Back");
	private String[] method= { "Cash","Check"};
	private String[] cloSus= { "Close","Suspend"};
	private String[] accType= { "Junior","Saver","Current"};
	private BankControl cus;
	private JPanel prePanel1,mainPanel1, depoPanel1,depoPanel2,withdrawPanel1,withdrawPanel2,closePanel1,closePanel2,createPanel1,createPanel2,createPanel3,createPanel4;
	private int type;
	private JButton submit_deposit = new JButton("Deposit");
	private JButton submit_close = new JButton("Submit");
	Object[] options = {"OK"};
	/** Framework set up
	 */
	public BankGUI(){
		cus=new BankControl();
		cus.autoClear();
		preInterface();
	}
	public void preInterface() {
		preFrame=new JFrame();
		preFrame.setTitle("Welcome to Bank System.");
		preFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		preFrame.setLayout(new BorderLayout());
		
		prePanel1 = new JPanel();
		prePanel1.setLayout(new GridLayout(3,1,10,10));
		
		pre_emp = new JButton("Employee");
		pre_emp.addActionListener(this);
		prePanel1.add(pre_emp);
		
		pre_depo = new JButton("Depositor");
		pre_depo.addActionListener(this);
		prePanel1.add(pre_depo);
		
		pre_cus = new JButton("Withdrawer");
		pre_cus.addActionListener(this);
		prePanel1.add(pre_cus);
		
		preFrame.add(prePanel1, BorderLayout.CENTER);
		preFrame.pack();
		preFrame.setVisible(true);
		preFrame.setSize(300, 200);
		preFrame.setLocationRelativeTo(null);
	}
	
	public void mainInterface() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Employee system");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		
		mainPanel1 = new JPanel();
		mainPanel1.setLayout(new GridLayout(4,1,10,10));
		
		label = new JLabel("please choose an option.");
		mainPanel1.add(label);
		
		createAcc = new JButton("Create an account");
		createAcc.addActionListener(this);
		mainPanel1.add(createAcc);
		
		closeAcc = new JButton("Close or Suspend");
		closeAcc.addActionListener(this);
		mainPanel1.add(closeAcc);
		
		back_emp.addActionListener(this);
		mainPanel1.add(back_emp);
		
		mainFrame.add(mainPanel1, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setSize(300, 200);
		mainFrame.setLocationRelativeTo(null);
	}
	public void createInterface() {
		createFrame = new JFrame();
		createFrame.setTitle("Create Account");
		createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createFrame.setLayout(new BorderLayout());
		
		createPanel1 = new JPanel();
		createPanel1.setLayout(new GridLayout(1,1,10,10));
		selectAccType = new JComboBox<String>(accType);
		selectAccType.addActionListener(this);
		selectAccType.setBorder(BorderFactory.createTitledBorder("Please choose what kind of Account you want to create:"));  
		createPanel1.add(selectAccType);
		
		createPanel2 = new JPanel();
		createPanel2.setLayout(new GridLayout(5,2,10,10));
		
		createLabel_name = new JLabel("Name:");
		createPanel2.add(createLabel_name);
		
		createInput_name = new JTextField(16);
		createPanel2.add(createInput_name);
		
		createLabel_add = new JLabel("Address:");
		createPanel2.add(createLabel_add);
		
		createInput_add = new JTextField(16);
		createPanel2.add(createInput_add);
		
		createLabel_DOB = new JLabel("Birthday(e.g.19980225):");
		createPanel2.add(createLabel_DOB);
		
		createInput_DOB = new JTextField(16);
		createPanel2.add(createInput_DOB);
		
		createLabel_credit = new JLabel("Credit Num:");
		createPanel2.add(createLabel_credit);
		
		createInput_credit = new JTextField(16);
		createPanel2.add(createInput_credit);
		
		createLabel_option = new JLabel(); //For saver,it stands for notice period.
		createPanel2.add(createLabel_option);//For current, it stands for overdraft limit.
		createInput_option.setVisible(false);//It is set to be invisible, unless the user chooses the account type.
		createPanel2.add(createInput_option);

		createPanel3=new JPanel();
		createPanel3.setLayout(new GridLayout(1,2,10,10));
		submit_create.setVisible(false);//Set to be invisible, unless the user chooses the account type.
		submit_create.addActionListener(this);
		createPanel3.add(submit_create);
		
		back_create.addActionListener(this);
		createPanel3.add(back_create);
		
		createFrame.add(createPanel1,BorderLayout.NORTH);
		createFrame.add(createPanel2,BorderLayout.CENTER);
		createFrame.add(createPanel3,BorderLayout.SOUTH);
		
		createFrame.pack();
		createFrame.setSize(400,250);
		createFrame.setLocationRelativeTo(null);
		createFrame.setVisible(true);
		
	}
	public void depoInterface() {
		depositFrame = new JFrame();
		depositFrame.setTitle("Deposit");
		depositFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		depositFrame.setLayout(new BorderLayout());
		
		depoPanel1 = new JPanel();
		depoPanel1.setLayout(new GridLayout(3,2,10,10));
		
		depoLabel_acc = new JLabel("Account Number:");
		depoPanel1.add(depoLabel_acc);
		
		depoInput_acc = new JTextField(16);
		depoPanel1.add(depoInput_acc);
		
		depoLabel_num = new JLabel("Deposit Amount:");
		depoPanel1.add(depoLabel_num);
		
		depoInput_num = new JTextField(16);
		depoPanel1.add(depoInput_num);
		
		submit_deposit.addActionListener(this);
		submit_deposit.setVisible(false);
		depoPanel1.add(submit_deposit);
		
		back_deposit.addActionListener(this);
		depoPanel1.add(back_deposit);
		
		depoPanel2 = new JPanel();
		depoPanel2.setLayout(new GridLayout(1,1,10,10));
		
		selectDeposit = new JComboBox<String>(method);
		selectDeposit.addActionListener(this);
		selectDeposit.setBorder(BorderFactory.createTitledBorder("Please choose what kind of method to deposit:"));  
		depoPanel2.add(selectDeposit);
		
		
		
		depositFrame.add(depoPanel1, BorderLayout.CENTER);
		depositFrame.add(depoPanel2, BorderLayout.NORTH);
		
		depositFrame.pack();
		depositFrame.setSize(400,250);
		depositFrame.setLocationRelativeTo(null);
		depositFrame.setVisible(true);
		
	}
	public void withdrawInterface() {
		withdrawFrame = new JFrame();
		withdrawFrame.setTitle("Withdraw");
		withdrawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		withdrawFrame.setLayout(new BorderLayout());
		
		withdrawPanel1 = new JPanel();
		withdrawPanel1.setLayout(new GridLayout(3,2,10,10));
		
		withdrawLabel_acc = new JLabel("Account Number:");
		withdrawPanel1.add(withdrawLabel_acc);
		
		withdrawInput_acc = new JTextField(16);
		withdrawPanel1.add(withdrawInput_acc);
		
		withdrawLabel_PIN=new JLabel("PIN:");
		withdrawPanel1.add(withdrawLabel_PIN);
		
		withdrawInput_PIN = new JTextField(16);
		withdrawPanel1.add(withdrawInput_PIN);
		
		withdrawLabel_num = new JLabel("Withdraw Amount:");
		withdrawPanel1.add(withdrawLabel_num);
		
		withdrawInput_num = new JTextField(16);
		withdrawPanel1.add(withdrawInput_num);
		
		withdrawPanel2 = new JPanel();
		withdrawPanel2.setLayout(new GridLayout(1,2,10,10));
		
		submit_withdraw = new JButton("Withdraw");
		submit_withdraw.addActionListener(this);
		withdrawPanel2.add(submit_withdraw);
		
		back_withdraw.addActionListener(this);
		withdrawPanel2.add(back_withdraw);
		
		withdrawFrame.add(withdrawPanel1, BorderLayout.CENTER);
		withdrawFrame.add(withdrawPanel2, BorderLayout.SOUTH);
		
		withdrawFrame.pack();
		withdrawFrame.setSize(400,250);
		withdrawFrame.setLocationRelativeTo(null);
		withdrawFrame.setVisible(true);
		
	}
	public void closeInterface() {
		closeFrame = new JFrame();
		closeFrame.setTitle("Close or Suspend");
		closeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		closeFrame.setLayout(new BorderLayout());
		
		closePanel1 = new JPanel();
		closePanel1.setLayout(new GridLayout(3,2,10,10));
		
		closeLabel_acc = new JLabel("Account Number:");
		closePanel1.add(closeLabel_acc);
		
		closeInput_acc = new JTextField(16);
		closePanel1.add(closeInput_acc);
		
		closeLabel_PIN = new JLabel("PIN:");
		closePanel1.add(closeLabel_PIN);
		
		closeInput_PIN = new JTextField(16);
		closePanel1.add(closeInput_PIN);
		
		submit_close.addActionListener(this);
		closePanel1.add(submit_close);
		submit_close.setVisible(false);
		
		back_close.addActionListener(this);
		closePanel1.add(back_close);
	
		closePanel2 = new JPanel();
		closePanel2.setLayout(new GridLayout(1,1,10,10));
		
		selectClose = new JComboBox<String>(cloSus);
		selectClose.addActionListener(this);
		selectClose.setBorder(BorderFactory.createTitledBorder("Please choose whether you want to close or suspend:"));  
		closePanel2.add(selectClose);
		
		
		
		closeFrame.add(closePanel1, BorderLayout.CENTER);
		closeFrame.add(closePanel2, BorderLayout.NORTH);
		
		closeFrame.pack();
		closeFrame.setSize(400,250);
		closeFrame.setLocationRelativeTo(null);
		closeFrame.setVisible(true);
	}
	
	/**
	 * Action listener, which would call the control class.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton)
		{
			JButton eventSource_JButton = (JButton) e.getSource();
			if (eventSource_JButton == createAcc) {
				this.createInterface();
				mainFrame.dispose();
			}
			else if (eventSource_JButton == pre_emp) {
				this.mainInterface();
				preFrame.dispose();
			}
			else if (eventSource_JButton == pre_depo) {
				this.depoInterface();
				preFrame.dispose();
			}
			else if (eventSource_JButton == pre_cus) {
				this.withdrawInterface();
				preFrame.dispose();
			}
			else if (eventSource_JButton == closeAcc) {
				this.closeInterface();
				mainFrame.dispose();
			}
			else if (eventSource_JButton == back_create) {
				this.preInterface();
				createFrame.dispose();
			}
			else if (eventSource_JButton == back_withdraw) {
				this.preInterface();
				withdrawFrame.dispose();
			}
			else if (eventSource_JButton == back_deposit) {
				this.preInterface();
				depositFrame.dispose();
			}
			else if (eventSource_JButton == back_close) {
				this.preInterface();
				closeFrame.dispose();
			}
			else if (eventSource_JButton == back_emp) {
				this.preInterface();
				mainFrame.dispose();
			}
			else if (eventSource_JButton == submit_create) {
				//Check empty
				if((type==2||type==3)&&(createInput_name.getText().isEmpty()||createInput_add.getText().isEmpty()||createInput_DOB.getText().isEmpty()||createInput_option.getText().isEmpty()||createInput_credit.getText().isEmpty())) {
					JOptionPane.showOptionDialog(null,"Your input is not complete!","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				//Check empty
				else if((type==1)&&(createInput_name.getText().isEmpty()||createInput_add.getText().isEmpty()||createInput_DOB.getText().isEmpty()||createInput_credit.getText().isEmpty())) {
					JOptionPane.showOptionDialog(null,"Your input is not complete!","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				//Check format,whether it is digit or word.
				else if((type==2||type==3)&&(isAllWords(createInput_name.getText())||isAllWords(createInput_add.getText())||isNumeric(createInput_DOB.getText())||isNumeric(createInput_credit.getText())||isNumeric(createInput_option.getText()))) {
					JOptionPane.showOptionDialog(null,"Your input format is not correct","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				//Check format,whether it is digit or word.
				}
				else if((type==1)&&(isAllWords(createInput_name.getText())||isAllWords(createInput_add.getText())||isNumeric(createInput_DOB.getText())||isNumeric(createInput_credit.getText()))) {
					JOptionPane.showOptionDialog(null,"Your input format is not correct","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				else if (createInput_DOB.getText().length()!=8) {
					JOptionPane.showOptionDialog(null,"Your birthday input format is not correct","please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				//check whether the user input a valid birthday.
				else if (Integer.parseInt(createInput_DOB.getText().substring(0,4))>2020 || Integer.parseInt(createInput_DOB.getText().substring(0,4))<1900 ||Integer.parseInt(createInput_DOB.getText().substring(4,6))>13||Integer.parseInt(createInput_DOB.getText().substring(6,8))>32) {
					JOptionPane.showOptionDialog(null,"Your birthday input format is not correct","please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				//check whether the credit number is more than 100.
				else if(Double.parseDouble(createInput_credit.getText())<100)
				{
					JOptionPane.showOptionDialog(null,"Your credit number should be more than 100.","Create account failure",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				//check whether the user is on the bad-record list.
				else if (readCredit("/Users/shenlinger/Documents/java/Bank/src/work/credit.txt").contains(createInput_name.getText())) //judge whether the user has a bad credit history.
				{
					JOptionPane.showOptionDialog(null,"Your credit record is not satisfied. You can't open an account","Create account failure",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				
				//check whether the user is less than  16, than can open an junior account.
				else if ((type==1)&&(checkbirth(Integer.parseInt(createInput_DOB.getText().substring(0,4)), Integer.parseInt(createInput_DOB.getText().substring(4,6)), Integer.parseInt(createInput_DOB.getText().substring(6,8))))) {
					JOptionPane.showOptionDialog(null,"Your age is larger than 16,you could not open a junior account","Create account failure",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				else {
					String DOB=createInput_DOB.getText();
					int year = Integer.parseInt(DOB.substring(0, 4));
					int month = Integer.parseInt(DOB.substring(4, 6));
					int day = Integer.parseInt(DOB.substring(6, 8));
					String name=createInput_name.getText();
					String address=createInput_add.getText();
					int option=0;
					if (type==2||type==3) {
						option=Integer.parseInt(createInput_option.getText());
					}
					double creditNum=Double.parseDouble(createInput_credit.getText());
					cus.createAccount(type,name,address,year,month,day,creditNum,option);
					this.mainInterface();
					createFrame.dispose();
				}

			}
			else if(eventSource_JButton == submit_close) {
				//Check empty
				if (closeInput_acc.getText().isEmpty()||closeInput_PIN.getText().isEmpty()) {
					JOptionPane.showOptionDialog(null,"Your input is not complete!","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				//check whether it is digit or string.
				else if (isNumeric(closeInput_acc.getText())||isNumeric(closeInput_PIN.getText())) {
					JOptionPane.showOptionDialog(null,"Your input format is not correct!","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				else {
					int accNo=Integer.parseInt(closeInput_acc.getText());
					int PIN=Integer.parseInt(closeInput_PIN.getText());
					if (closeInput_method.equals("Close")) {
						cus.closeAcc(accNo, PIN);
						this.mainInterface();
						closeFrame.dispose();
					}
					else if(closeInput_method.equals("Suspend")) {
						cus.suspendAcc(accNo, PIN);
						this.mainInterface();
						closeFrame.dispose();
					}
					
					
				}
					
			}
			else if(eventSource_JButton == submit_withdraw) {
				if (withdrawInput_acc.getText().isEmpty()||withdrawInput_PIN.getText().isEmpty()||withdrawInput_num.getText().isEmpty()) {
					JOptionPane.showOptionDialog(null,"Your input is not complete!","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				else if (isNumeric(withdrawInput_acc.getText())||isNumeric(withdrawInput_PIN.getText())||isNumeric(withdrawInput_num.getText())) {
					JOptionPane.showOptionDialog(null,"Your input format is not correct!","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				//Check if the withdraw amount is less than 0.
				else if (Double.parseDouble(withdrawInput_num.getText())<=0) {
					JOptionPane.showOptionDialog(null,"The credit amount is not valid, it should be greater than zero.","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				else {
					int accNo=Integer.parseInt(withdrawInput_acc.getText());
					int PIN=Integer.parseInt(withdrawInput_PIN.getText());
					double num=Double.parseDouble(withdrawInput_num.getText());
					cus.withdrawAcc(accNo, PIN,num);
					this.mainInterface();
					withdrawFrame.dispose();
				}
					
			}
			else if(eventSource_JButton == submit_deposit) {
				if (depoInput_acc.getText().isEmpty()||depoInput_num.getText().isEmpty()) {
					JOptionPane.showOptionDialog(null,"Your input is not complete!","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				else if (isNumeric(depoInput_acc.getText())||isNumeric(depoInput_num.getText())) {
					JOptionPane.showOptionDialog(null,"Your input format is not correct!","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				//Check if the withdraw amount is less than 0.
				else if (Double.parseDouble(depoInput_num.getText())<=0) {
					JOptionPane.showOptionDialog(null,"The deposit amount is not valid, it should be greater than zero.","Please check",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				}
				else {
					int accNo=Integer.parseInt(depoInput_acc.getText());
					double num=Double.parseDouble(depoInput_num.getText());
					if  (withdrawInput_method.equals("Cash")) {
						cus.depositAcc(accNo,num,true);
					}
					else if ((withdrawInput_method.equals("Check"))) {
						cus.depositAcc(accNo,num,false);
					}
					this.mainInterface();
					depositFrame.dispose();
					
				}
					
			}
		}
		/**
		 * Record the JComboBox option of the user and pass it when user press submit button.
		 */
		else if(e.getSource() instanceof JComboBox){
			JComboBox<String> eventSource_JComboBox = (JComboBox) e.getSource();
			if (eventSource_JComboBox== selectDeposit) {
				withdrawInput_method = eventSource_JComboBox.getSelectedItem().toString();
				submit_deposit.setVisible(true); 
			}
			else if (eventSource_JComboBox== selectClose) {
				closeInput_method = eventSource_JComboBox.getSelectedItem().toString();
				submit_close.setVisible(true);
				System.out.println(closeInput_method);
			}
			else if (eventSource_JComboBox== selectAccType) {
				String createInput_accType = eventSource_JComboBox.getSelectedItem().toString();
				submit_create.setVisible(true);
				if (createInput_accType.equals("Junior"))
				{
					type=1;
				}
				else if (createInput_accType.equals("Saver"))
				{	
					type=2;
					createInput_option.setVisible(true);
					createLabel_option.setText("Notice Period:");
				}
				else if (createInput_accType.equals("Current"))
				{	
					type=3;
					createInput_option.setVisible(true);
					createLabel_option.setText("OverDraft Limit:");
				}
			}
		 }
	}

	 /**
	  *  Input check interface: Check whether the input is digit.
	  */
	public boolean isNumeric (String IDText) {
		for(int i=0; i<IDText.length(); i++) {
			if(!Character.isDigit(IDText.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	 /**
	  *  Input check interface: Check whether the input is string.
	  */
	private boolean isAllWords(String nameText){
		  char[] array = nameText.toCharArray();
		  for(Character ch : array){
		   if(ch<'A' || ch>'z' || (ch<'a' && ch>'Z')) {
			   return true ;
		   }
		   else {
			   return false;
		   }
		  }
		  return true;
	}

	 /**
	  *  Input check interface: Check whether the user is in the bad-record list.
	  */
	public List<String> readCredit(String path)
	{
		List<String> creditName=new ArrayList<String>();
		try {
			creditName =FileUtils.readLines(new File(path), "UTF-8");
		}catch(IOException e){
            e.printStackTrace();
		}
		return creditName;
	}

	 /**
	  *  Input check interface: Check whether the user is less than 16.
	  */
	public boolean checkbirth(int year, int month, int day) {
		boolean val = true;
		int y, m, d, age;
		Calendar cal = Calendar.getInstance();		// Get the current date.
		y = cal.get(Calendar.YEAR);
		m = (cal.get(Calendar.MONTH)) + 1;
		d = cal.get(Calendar.DATE);

		if (m < month)
			age = y - year - 1;
		else if (d < day)
			age = y - year - 1;
		else
			age = y - year;

		if (age < 16)
			val = false;

	return val;
	}
	
	public static void main(String[] args) {
		BankGUI demo = new BankGUI();
	}
	
	
}
