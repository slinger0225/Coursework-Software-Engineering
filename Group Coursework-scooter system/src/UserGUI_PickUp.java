
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
 * This part is the GUI of the pick up page.
 * In this part the system will distribute scooters for users to pick up
 *
 * @author Group 44
 * @version 3.0
 */
public class UserGUI_PickUp extends UserGUI_SelectScooter{
	/**
	 * The pick up page, the user can click the scooter which is distributed by the system to pick up.
	 * @param available the number of scooters available in the station.
	 * @param total the number of scooters a station can carry.
	 * @param IDNum The user's QM number
	 * @param buttonChecker the station which is chose by user to pick up a scooter.
	 */
	public UserGUI_PickUp(int available,int total,String IDNum,int buttonChecker){
		super(available,total,IDNum,buttonChecker);
		JButton[ ] button = new JButton[total];
		PickUPFrame.setTitle("Pick up scooters");
		label = new JLabel("The slot "+(total-available+1)+ "~slot 8 is available. And the slot "+(total-available+1)+" is open for you  to pick up now. Please click it to pick up the scooter within 60 seconds." );
		label2=new JLabel("Your remaining using time today: "+(120-student.getTotalTime()));
		jp1.add(label);
		jp1.add(label2);
		jp1.setBounds(40, 0, 1000, 40);
		PickUPFrame.add(jp1);
		
		MyThread CountDown=new MyThread();
		CountDown.start(); // class of time
		
		 light.setImage(light.getImage().getScaledInstance(20, 35, light.getImage().SCALE_DEFAULT));
	     light_off.setImage(light_off.getImage().getScaledInstance(20, 35, light_off.getImage().SCALE_DEFAULT));
	     for (int i=0;i<(total-available);i++)
 		{
 			img.add(new JLabel(light_off,JLabel.CENTER));
 			jp2.add(img.get(i));
 		}
 
 		img.add(new JLabel(light,JLabel.CENTER));
 		jp2.add(img.get(total-available));
 		for (int i=(total-available+1);i<total;i++)
 		{
 			img.add(new JLabel(light_off,JLabel.CENTER));
 			jp2.add(img.get(i));
 		}
 		
 		for (int i = 0; i <(total-available); i++) { 
			//button[i] = new JButton("slot"+i);
			button[i] = new JButton();
			button[i].setIcon(icon1);
			icon1.setImage(icon1.getImage().getScaledInstance(100, 100, icon1.getImage().SCALE_DEFAULT));
			button[i].addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showOptionDialog(null,"This slot has no scooter, please go to the slot with the light flashing on its head.","Please click again",JOptionPane.DEFAULT_OPTION,
    						JOptionPane.WARNING_MESSAGE,null,options,options[0]);
						}
					}
				
					);
			jp3.add(button[i]);
		}
		button[total-available] = new JButton();
		button[total-available].setIcon(icon3);
		icon3.setImage(icon3.getImage().getScaledInstance(100, 100, icon3.getImage().SCALE_DEFAULT));
		button[total-available].addActionListener(
				new ActionListener() {
					
					
					public void actionPerformed(ActionEvent e) {
						img.get(total-available).setIcon(light_off);
						CountDown.exit = true; 
						JOptionPane.showOptionDialog(null,"Successfully pick up the scooter","Successful message",JOptionPane.DEFAULT_OPTION,
        						JOptionPane.WARNING_MESSAGE,null,options,options[0]);
							BorrowBike borrow=new BorrowBike();
						borrow.borrowBike(buttonChecker,IDNum);     	
        						UserGUI_HomePage login = new UserGUI_HomePage();
        						login.pack();
        						login.setVisible(true);
        						login.setSize(300, 200);
        						login.setLocationRelativeTo(null);
        						PickUPFrame.dispose();

					}
				}
				);
		jp3.add(button[total-available]);

		for (int i =(total-available+1); i <total; i++) { 
		//button[i] = new JButton("slot"+i);
		button[i] = new JButton();
		button[i].setIcon(icon2);
		icon2.setImage(icon2.getImage().getScaledInstance(100, 100, icon2.getImage().SCALE_DEFAULT));
		button[i].addActionListener(
    			new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					JOptionPane.showOptionDialog(null,"This slot hasn't been open for picking up,please go to the slot with the light flashing on its head.","Please click again",JOptionPane.DEFAULT_OPTION,
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
	   class MyThread extends Thread implements Runnable // class of time 
	    {
	    public boolean exit;
		int time = 61; // can change to 60
	    public void run() {
	    while (time > 0)
	    {
	    time--;
	    UserGUI_PickUp.label1.setText(time + ""); 
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
