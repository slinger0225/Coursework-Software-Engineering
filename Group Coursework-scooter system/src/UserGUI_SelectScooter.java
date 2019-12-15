

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
 * This class is used to put images and set frames on the pick up or return page.
 *
 * @author Group 44
 * @version 2.0
 */
public class UserGUI_SelectScooter {
	//private static final long serialVersionUID = 1L;
	public static JLabel label1;
	protected JFrame PickUPFrame;
	protected Object[] options= {"OK"};
	protected Object[] option1= {"pay now", "pay later"};
	protected ArrayList<JLabel> img=new ArrayList<JLabel>();
	protected ImageIcon light = new ImageIcon("images/light.gif");
	protected ImageIcon light_off = new ImageIcon("images/light_off.png");
	protected Student student=new Student();
	protected StudentIO io=new StudentIO();
	protected JTextArea text;
	protected JLabel label,l1,label2;
	protected JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7;
	protected ImageIcon icon1 = new ImageIcon("images/timg2.jpg");
	protected ImageIcon icon2 = new ImageIcon("images/timg.jpg");
	protected ImageIcon icon3 = new ImageIcon("images/timg3.jpg");
	protected ImageIcon icon4 = new ImageIcon("images/timg4.jpg");
    public UserGUI_SelectScooter(int available,int total,String IDNum,int buttonChecker){
    	 	try {
	    	 		student=io.studentInput(IDNum);
    	 	} catch (ClassNotFoundException | IOException ex) {
    	 		ex.printStackTrace();
    	 	}
    	    JLabel[] slot_name=new JLabel[total];
        PickUPFrame=new JFrame();
    		PickUPFrame.setLayout(null);  
    		PickUPFrame.setPreferredSize(new Dimension(1100, 340));
    		
    		//this panel is used to hold messages.
    		jp1=new JPanel();
    		
		//this panel is used to hold "Time" label
		jp6 = new JPanel();
		jp6.add(new JLabel("Time"));
		jp6.setBounds(900, 40, 80, 40);
		PickUPFrame.add(jp6);

		//this panel is used to hold timer.
		jp5=new JPanel(); 
		label1 = new JLabel();
		jp5.add(label1);
		jp5.setBounds(900,80,80,40);
    		PickUPFrame.add(jp5);
    		
    		//This panel is used to hold back button;
    		jp7=new JPanel();
    		JButton back= new JButton("Back");
    		back.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							PickUPFrame.dispose();
							UserGUI_HomePage login = new UserGUI_HomePage();
						}
					}
				
					);
    		jp7.add(back);
    		jp7.setBounds(40,40,80,40);
    		PickUPFrame.add(jp7);
    		
    		//this panel is used to hold light.
    	    jp2 = new JPanel();
    	    jp2.setLayout(new GridLayout(0,total));
        jp2.setBounds(40, 120,1000, 40);
        PickUPFrame.add(jp2);   
        
        //this panel is used to choose scooter.
        jp3 = new JPanel();
        jp3.setLayout(new GridLayout(0,total));
        jp3.setBounds(40,160, 1000, 150);
        PickUPFrame.add(jp3);
		
        //this panel is used to display slot name.
        jp4 = new JPanel();
        jp4.setLayout(new GridLayout(0,total));
        for (int i=0;i<total;i++)
        {
            		slot_name[i]= new JLabel("slot"+(i+1),JLabel.CENTER);
            		jp4.add(slot_name[i]);
        }
        jp4.setBounds(40,310, 1000, 40);
        PickUPFrame.add(jp4);
        PickUPFrame.setVisible(true);
        PickUPFrame.pack();
        PickUPFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
 
    
    /*
    public static void main(String[] args) {
    		int available=6;
        int total= 8;
        
        
        //!!!check whether the user is going to pick up or return.
        boolean pickUp=true;
        new PickUp(available,total,pickUp);
        //demo.pack();
		//demo.setVisible(true);
		//demo.setSize(1000, 320);
		//demo.setLocationRelativeTo(null);
    }
    */
 


     
     


}

