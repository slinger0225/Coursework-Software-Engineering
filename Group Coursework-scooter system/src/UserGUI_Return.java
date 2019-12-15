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
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.IOException;
import java.awt.Image;

import javax.swing.*;
/**
 * This part is the GUI of the return page.
 * In this part the system will distribute slots for users to return.
 *
 * @author Group 44
 * @version 3.0
 */
public class UserGUI_Return extends UserGUI_SelectScooter {
	/**
	 * The return page, the user can click the slot which is distributed by the system to return.
	 * @param available the number of scooters available in the station.
	 * @param total the number of scooters a station can carry.
	 * @param IDNum The user's QM number
	 * @param buttonChecker the station which is chose by user to return a scooter.
	 */
	public UserGUI_Return(int available, int total, String IDNum, int buttonChecker) {
		super(available, total, IDNum, buttonChecker);
		JButton[] button = new JButton[total];
		PickUPFrame.setTitle("Return scooters");
		label = new JLabel("You could return the scooter in the slot 1 ~ slot " + (total - available)
				+ ". And the slot " + (total - available)
				+ " is open for you to return now. \nPlease click it to return the scooter within 60 seconds.");
		label2 = new JLabel("");
		jp1.add(label);
		jp1.add(label2);
		jp1.setBounds(40, 0, 1000, 40);
		PickUPFrame.add(jp1);

		YourThread CountDown1 = new YourThread();
		CountDown1.start(); // class of time

		light.setImage(light.getImage().getScaledInstance(20, 35, light.getImage().SCALE_DEFAULT));
		light_off.setImage(light_off.getImage().getScaledInstance(20, 35, light_off.getImage().SCALE_DEFAULT));
		for (int i = 0; i < (total - available - 1); i++) {
			img.add(new JLabel(light_off, JLabel.CENTER));
			jp2.add(img.get(i));
		}

		img.add(new JLabel(light, JLabel.CENTER));
		jp2.add(img.get(total - available - 1));
		for (int i = (total - available); i < total; i++) {
			img.add(new JLabel(light_off, JLabel.CENTER));
			jp2.add(img.get(i));
		}
		
  		for (int i = 0; i <(total-available-1); i++) { 
			//button[i] = new JButton("slot"+i);
			button[i] = new JButton();
			button[i].setIcon(icon1);
			icon1.setImage(icon1.getImage().getScaledInstance(100, 100, icon1.getImage().SCALE_DEFAULT));
			button[i].addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showOptionDialog(null,"This slot hasn't been open for returning ,please go to the slot with the light flashing on its head.","Please click again",JOptionPane.DEFAULT_OPTION,
		    						JOptionPane.WARNING_MESSAGE,null,options,options[0]);        						
							}
					}
				
					);
			jp3.add(button[i]);
		}
		button[total-available-1] = new JButton();
		button[total-available-1].setIcon(icon4);
		icon4.setImage(icon4.getImage().getScaledInstance(100, 100, icon4.getImage().SCALE_DEFAULT));
		button[total-available-1].addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						img.get(total-available-1).setIcon(light_off);
						CountDown1.exit = true;
						JOptionPane.showOptionDialog(null,"Successfully return the scooter","Successful message",JOptionPane.DEFAULT_OPTION,
	    						JOptionPane.WARNING_MESSAGE,null,options,options[0]);
						ReturnBike ret=new ReturnBike();
						boolean finement=ret.returnBike(buttonChecker,IDNum);
						//!!!check if it exceeds time(30 mins/time || 2 hours/day )
						
						
						//!!!check if it exceeds time(only 30 mins/time or both situations take place)
					if(finement==false)
					{
							int op=JOptionPane.showOptionDialog(null,"You have overused the scooter, please pay the finement: $100.","finement message",JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE,null,option1,option1[0]);
									if(op==0)
							{
								student.setFine(true);
								io.studentOutput(student);

							}

						}
						
						/*
						//!!!check if it exceeds time(only 2 hours/day)
						else if((!exceed30mins)&&(exceed2hours))
						{
							boolean pay=false;
							int op=JOptionPane.showOptionDialog(null,"You have used more than 2 hours today, please pay the finement: $100.","finement message",JOptionPane.DEFAULT_OPTION,
    	    						JOptionPane.WARNING_MESSAGE,null,option1,option1[0]);
							if(op==0)
							{
								pay=!pay;
							}
							System.out.println(pay);
						}
						*/

						UserGUI_HomePage login = new UserGUI_HomePage();
						login.pack();
						login.setVisible(true);
						login.setSize(300, 200);
						login.setLocationRelativeTo(null);
				
						PickUPFrame.dispose();
					}
				}
				);
		jp3.add(button[total-available-1]);

		for (int i =(total-available); i <total; i++) { 
		//button[i] = new JButton("slot"+i);
		button[i] = new JButton();
		button[i].setIcon(icon2);
		icon2.setImage(icon2.getImage().getScaledInstance(100, 100, icon2.getImage().SCALE_DEFAULT));
		button[i].addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					JOptionPane.showOptionDialog(null,"This slot has been occupied, please go to the slot with the light flashing on its head.","Please click again",JOptionPane.DEFAULT_OPTION,
        						JOptionPane.WARNING_MESSAGE,null,options,options[0]);
    				}
    			}
    				
    		);
		jp3.add(button[i]);
		}
	}
	/**
	 * The count down timer.
	 */
	class YourThread extends Thread implements Runnable // class of time 
    {
    public boolean exit;
	int time = 61; // can change to 60
    public void run() {
    while (time > 0)
    {
    time--;
    UserGUI_Return.label1.setText(time + ""); 
    try
    {
    Thread.sleep(1000); 
    if(exit)
    	this.join();
    }
    catch (Exception e)
    {
    e.printStackTrace();
    }

    }
    for (int i = 0; i < img.size(); i++) {
		
		img.get(i).setIcon(light_off);
	}
    JOptionPane.showOptionDialog(null,"time is out,please login again","failure message",JOptionPane.DEFAULT_OPTION,
    		JOptionPane.WARNING_MESSAGE,null,options,options[0]);

    PickUPFrame.dispose();
	UserGUI_HomePage login = new UserGUI_HomePage();
	login.pack();
	login.setVisible(true);
	login.setSize(300, 200);
	login.setLocationRelativeTo(null);
    }
    };

}
