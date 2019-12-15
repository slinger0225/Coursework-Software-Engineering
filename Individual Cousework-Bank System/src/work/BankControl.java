package work;
/**
* Control class that get input from GUI and implements operation to change data in entity class.
*Demo--<b>BankSystem</b>
*
*@author shenlinger
*
*@version  2019/05/11
*@param Get user's info from GUI.
*@return {@code true} if the user successfully implements the account.
* {@code false} if the user fail.
*/
import java.io.File;
import java.io.IOException;
import java.util.*;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import work.BankAccount;
import work.CurrentAccount;
import work.JuniorAccount;
import work.SaverAccount;


public class BankControl {
	private ArrayList<BankAccount> accountList=new ArrayList<BankAccount>();
	private static long timerInterval = 10000;
	private ArrayList<BankAccount> unclearAccount = new ArrayList<BankAccount>();
	Object[] options = { "OK"};
	public BankControl(){
		getJson();
	}
	
	/*A thread which is used to clear uncleared funds every 10 sec.*/
	public class clearTask extends TimerTask {
		public void run() {
			System.out.println("Clearing funds.");
			clearFunds();
		}
	
	}
	public void autoClear() {
		Timer tmr = new Timer();
		tmr.scheduleAtFixedRate(new clearTask(), 0, timerInterval);
	}
	/**
	 * Input and output stream to read json format file as ArrayList.
	 */
	public void getJson() {
        String input="";
        try {
            input = FileUtils.readFileToString(new File("src/work/BankAccount.json"), "UTF-8");
            List jsonList = JSONObject.parseObject(input, List.class);
            for(int i=0;i<jsonList.size();i++) {
                accountList.add(JSONObject.parseObject(JSONObject.toJSONString(jsonList.get(i)),BankAccount.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public void writeJson(ArrayList<BankAccount> output) {
        try {
            FileUtils.write(new File("src/work/BankAccount.json"),JSON.toJSONString(output), "UTF-8",false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	/**
	 * Create Account.
	 * 
	 * 	
	 * 
	 */
	public boolean createAccount(int type, String name, String address,
			int year,int month,int day, double creditNum,int option) {
		  	System.out.println("createAccount now...");
			BankAccount acc = null;
			Random r=new Random();
			int PIN=100000+r.nextInt(900000);// Generate a random 6-digit PIN.
			Random r2=new Random();
			int accNo=10000+r2.nextInt(90000);// Generate a random 5-digit accNo.

			if (type == 3) {
				acc = new CurrentAccount(accNo,name,PIN);// Create a current account.
				acc.setLimit(option);
			} else if (type == 2) {
				acc = new SaverAccount(accNo,name,PIN);	// Create a saver account.
				acc.setNoticePeriod(option);	// Set notice period.
			} else if (type == 1) {			//Create a junior account.
					acc = new JuniorAccount(accNo, name,PIN);
			} else												
				System.out.println("Type return error");
			
			// Successfully created a new account.
			if (acc != null) {
				acc.setAddress(address);
				acc.setBirthday(year+""+month+""+day+"");
				acc.setBalance(creditNum);
				accountList.add(acc);
				writeJson(accountList);
				JOptionPane.showOptionDialog(null,"You have created new account!\nYour account number: "+accNo
						+"\nYour issued PIN: "+PIN
						+"\nPlease make sure you remember it!", "Create success",JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
				System.out.println(acc);
				return true;
			} else {
				System.out.println("Create account return type error!");
				return false;
			}
		}
	/**
	 * Close Account.
	 *
	 * 	
	 * 
	 */
	public boolean closeAcc(int accNo, int PIN) {
		BankAccount acc = null;
		for (int i = 0; i < accountList.size(); i++) {
			acc=accountList.get(i);
	        if ( acc.getAccNo()==accNo && acc.getpIN() == PIN) {
	        	 	break;
		    }
			else {
				acc = null;
			}
		}

		if (acc == null) {
			JOptionPane.showOptionDialog(null,
					"Account number or PIN is not correct!",
					"Close failure", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			return false;

		} else {
			if (acc.getBalance()!=0)
			{
				JOptionPane.showOptionDialog(null,
						"Your balance must be 0 before you leave  the bank, your current balance is "+acc.getBalance()+".", "Close failure",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE,null,options,options[0]);
				return false;
			}
			else {
				accountList.remove(acc);
				writeJson(accountList);
				JOptionPane.showOptionDialog(null,
					"Close account success!", "Close success",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
				return true;
			}
		}
		
	}
	/**
	 * Suspend Account.
	 * 
	 * 	
	 * 
	 */
	public boolean suspendAcc(int accNo, int PIN) {
		BankAccount acc = null;
		for (int i = 0; i < accountList.size(); i++) {
			acc=accountList.get(i);
	        if ( acc.getAccNo()==accNo && acc.getpIN() == PIN) {
	        	 	break;
		    }
			else {
				acc = null;
			}
		}

		if (acc == null) {
			JOptionPane.showOptionDialog(null,
					"Account number or PIN is not correct!",
					"Close failure", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			return false;

		} else {
			if (acc.getIsActive() == false) {
				acc.setIsActive(true);
				JOptionPane.showOptionDialog(null,
					"You have suspended your account!", "Suspend success",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
			}
			else {
				acc.setIsActive(false);
				JOptionPane.showOptionDialog(null,
					"You have activated your account!", "Activate success",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
			}
			writeJson(accountList);
			return true;
		}
		
	}
	/**
	 * Withdraw money.
	 * 
	 * 	
	 * 
	 */
	public boolean withdrawAcc(int accNo, int PIN, double num) {
		BankAccount acc = null;
		int replace=0;
		int val=-1;
		for (int i = 0; i < accountList.size(); i++) {
			acc=accountList.get(i);
	        if ( acc.getAccNo()==accNo && acc.getpIN() == PIN) {
	        		replace=i;
	        	 	break;
		    }
			else {
				acc = null;
			}
		}
		if (acc == null) {
			JOptionPane.showOptionDialog(null,
					"Account number or PIN is not correct!", "Withdraw failure",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);

		} else {
			String input=acc.getAccType();
			System.out.println(input);
			if (acc.getAccType().equals("Junior")) {
		        JuniorAccount acc1= JSONObject.parseObject(JSONObject.toJSONString(acc),JuniorAccount.class);
				//JuniorAccount acc1=(JuniorAccount)acc;
				val=acc1.withdraw(num);
				accountList.set(replace,acc1);
				
			}
			else if (acc.getAccType().equals("Current")) {
				CurrentAccount acc1= JSONObject.parseObject(JSONObject.toJSONString(acc),CurrentAccount.class);
				val=acc1.withdraw(num);
				accountList.set(replace,acc1);
			}
			else if (acc.getAccType().equals("Saver")) {
				SaverAccount acc1= JSONObject.parseObject(JSONObject.toJSONString(acc),SaverAccount.class);
				val=acc1.withdraw(num);
				accountList.set(replace,acc1);
			}
			
			
			if (val==0) {
				writeJson(accountList);
				JOptionPane.showOptionDialog(null,"You have withdrawed: " + num+ "\nYou still have: "+ (acc.getBalance()-num), "Withdraw success",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
				return true;
			}
			else if (val == 1) {
				JOptionPane.showOptionDialog(null, "The account is suspended.",
						"Withdraw failure", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				return false;
			} else if (val== 2) {
				JOptionPane.showOptionDialog(null,
						"You have withdrawed more than you have!\nYour current balance is:"+acc.getBalance()+"\nYou're trying to withdraw:"+num, "Withdraw failure",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				return false;
			} else if (val == 3) {
				JOptionPane.showOptionDialog(null, "You have withdrawed more than your overdraft limit!\n your current balance is:"+acc.getBalance()+"\nYour overdraft limit is:"+acc.getLimit()+"\nYou're trying to withdraw:"+num, "Withdraw failure",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			} else {
				JOptionPane.showOptionDialog(null,
						"This is a saver account, you could only withdraw after "+val+" days",
						"Withdraw failure", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				return false;
			}
		}
		return false;
	}
	/**
	 * Deposit money. if the user use check, add the deposit money to {@code uncleared} 
	 * and add the account to an {@code ArrayList} that waits to be uncleared by TimerTask.
	 * 
	 * 	
	 * 
	 */
	public boolean depositAcc(int accNo, double amount, boolean isCash) {
		BankAccount acc = null;
		int val=-1;
		for (int i = 0; i < accountList.size(); i++) {
			acc=accountList.get(i);
	        if ( acc.getAccNo()==accNo) {
	        	 	break;
		    }
			else {
				acc = null;
			}
		}
		if (acc == null) {
			JOptionPane.showOptionDialog(null,
					"Account number is not correct!", "Withdraw failure",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
		} else {
			val = acc.deposit(amount, isCash);
			if (val == 1) {
				JOptionPane.showOptionDialog(null,
							"The account is suspended.", "Deposit failed!",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);
				return false;
			} else if (val == 0) {
				JOptionPane.showOptionDialog(null,"After deposited $ " + amount
							+ "\n, your balance now is: "
							+ acc.getBalance(), "Deposit success",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, options,
							options[0]);
				writeJson(accountList);
				return true;
			} else if (val==2) {
				unclearAccount.add(acc);
				JOptionPane.showOptionDialog(null,"You are using check to deposit $ " + amount
						+ "\n, your deposit  will be issued after 5 mins.", "Deposit success",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options,
						options[0]);
				writeJson(accountList);
				return true;
				}
			else {
				System.out.println("deposit withdraw return error");
				return false;
			}
		}
		return false;

	}

	/**
	 * Clear Funds. iterate the account in {@code ArrayList}, and issue them immediately.
	 */

	public void clearFunds() {
		BankAccount acc = null;
		for (int i = 0; i < unclearAccount.size(); i++) {
			acc=unclearAccount.get(i);
	        acc.setBalance(acc.getBalance()+acc.getUncleared());
	        acc.setUncleared(0.0);
	        unclearAccount.remove(acc);
	        JOptionPane.showOptionDialog(null,"Your check has been issued now.\n After deposited, your balance now is:"+ acc.getBalance(), "Deposit success",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options,
					options[0]);
	}
		
	}

	


}
