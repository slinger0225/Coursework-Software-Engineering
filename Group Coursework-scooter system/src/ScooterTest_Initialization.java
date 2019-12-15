

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * This part is used to initialise the scooters in each station and on the way.
 * Please run this file to initialise.
 *
 *
 * @author Group 44
 * @version 1.0
 */
public class ScooterTest_Initialization {
	ScooterTest_Initialization(){
	/*
	ParkingLot QM=new ParkingLot();
	ParkinglotIO ParkingIO=new ParkinglotIO();
	try {
 		QM=ParkingIO.parkingLotInput();
 	} catch (ClassNotFoundException | IOException e) {
 		e.printStackTrace();
 	}
	QM.setBikeInA(5);
	QM.setBikeInB(5);
	QM.setBikeInC(5);
	QM.setBikeOnTheWay(9);
	ParkingIO.parkingLotOutput(QM);
	System.out.println(QM);
	*/
	
	Student student=new Student();
	StudentIO io=new StudentIO();
 	try {
 		student=io.studentInput("162898321");
 	} catch (ClassNotFoundException | IOException e) {
 		e.printStackTrace();
 	}
 	int[] eachTime={4,29,29};
	int[] eachTime1={4,31,29};
	int[] eachTime2={4,31,29};
	int[] eachTime3={4,31,29};
	int[] eachTime4={4,31,40};
	ArrayList<int[]> totalList=new ArrayList<int[]>();
	totalList.add(eachTime);
	totalList.add(eachTime1);
	totalList.add(eachTime2);
	totalList.add(eachTime3);
	totalList.add(eachTime4);
 	student.setTotalList(totalList);
 	student.setCurrentTime(0);
 	student.setFine(false);
	student.setTotalTime(127);
	student.setDataMonth(4);
	student.setDataDay(31);
 	//student.setFine(false);
 	 
 	/*
 	ArrayList<int[]> totalList=new ArrayList<int[]>();
 	//student.setCurrentTime(50);
 	//student.setFine(false);
 	//student.setTotalTime(3);
 	
 	int[] eachTime={4,17,50};
	int[] eachTime1={4,19,29};
	int[] eachTime2={4,19,29};
	int[] eachTime3={4,19,29};
	int[] eachTime4={4,19,40};
	
	totalList.add(eachTime);
	totalList.add(eachTime1);
	totalList.add(eachTime2);
	totalList.add(eachTime3);
	totalList.add(eachTime4);
 	student.setTotalList(totalList);
 	System.out.println(student);
 	
 	//System.out.println(student);
 	*/
 	//io.studentOutput(student);
 	/*
 	for (int i=0;i<totalList.size();i++) {
 		int[] each=totalList.get(i);
 		System.out.println("month:"+each[0]+"date:"+each[1]+"time:"+each[2]+"\n");
 	}
 	*/
	//assertFalse(bike.student1.getFine());
	//assertEquals(bike.student1.getTotalTimeInWeek(),10);
	//assertEquals(bike.student1.getStudentName(),"Zeyuan Dong");
	//assertEquals("Zeyuan Dong",StudentIO.student.getStudentName());
	//assertEquals("161194886",StudentIO.student.getQMNum());
	//System.out.println(demo.student);
	}
	
	public static void main(String[] args) {
		ScooterTest_Initialization demo=new ScooterTest_Initialization();
	}
	

}
