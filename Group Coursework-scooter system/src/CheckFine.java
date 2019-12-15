

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * This class is used to check if the user has overused the scooter. If the user used more than 30 minutes at a time or more than 2 hours a day, the parameter "fine" in the Student object will be set.
 * Methods in this class are used before the user pick up a scooter and after the user return a scooter.
 * @author Group 44
 * @version 2.0
 */
public class CheckFine {
	private Calendar calendar;
	private int month;
	private int date;
	private int hour;
	private int minute;
	private int time ;
	private ArrayList<int[]> totalList = new ArrayList<int[]>();
	private int totalWeekTime;
	public CheckFine() {
		calendar = Calendar.getInstance();
		month = calendar.get(Calendar.MONTH);
		date = calendar.get(Calendar.DATE);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		time = ((month * 30 + date) * 24 + hour) * 60 + minute;
	}
	
	//set user's pickup time.

	/**
	 * If the user is picking up a scooter, the current system time will be saved to his or her account.
	 * @param IDNum The student's QM number.
	 */
    protected void setUserTime(String IDNum){
    		Student student=new Student();
    		StudentIO io=new StudentIO();
   	 	try {
   	 		student=io.studentInput(IDNum);
   	 	} catch (ClassNotFoundException | IOException e) {
   	 		e.printStackTrace();
   	 	}
    		student.setCurrentTime(time);
    		if (student.getDifferentDay(month, date) == false) {

    			} else {
    				student.setDataMonth(month);
    				student.setDataDay(date);
    				student.setTotalTime(0);
    			}
    			io.studentOutput(student);
    		}

    //check whether it is one day later, so that we can reset the daily usage to 0.

	/**
	 * Compare the current system time with the time saved in the student's account. If they are in a different day, the value of "totalTime" in the student's account will be set to 0.
	 *
	 * @param IDNum Student's QM number.
	 */
    protected void checkDifferentDay(String IDNum) {
    		Student student=new Student();
		StudentIO io=new StudentIO();
		try {
   	 		student=io.studentInput(IDNum);
   	 	} catch (ClassNotFoundException | IOException e) {
   	 		e.printStackTrace();
   	 	}
   		if (student.getDifferentDay(month, date) == false) {
		} else {
			student.setTotalTime(0);
		}
		io.studentOutput(student);
    	
    }

	/**
	 * check if the student's account should pay the fine.
	 * @param IDNum Student's QM number
	 * @return false when the user need to pay the fine, true when the user do not need to pay.
	 */
	//check if the user has exceeded usage.
    protected boolean checkFine(String IDNum){
    		Student student=new Student();
		StudentIO io=new StudentIO();
   	 	try {
   	 		student=io.studentInput(IDNum);
   	 	} catch (ClassNotFoundException | IOException e) {
   	 		e.printStackTrace();
   	 	}
        int t=time-student.getCurrentTime();
        student.setTotalTime(t+student.getTotalTime());
        totalList=student.calTotalList(month, date, t);
        student.setTotalList(totalList);
        student.setCurrentTime(0);
        if (t>=30||student.getTotalTime()>=120) {
               	student.setFine(false);
               	student.setDataDay(date);
               	student.setDataMonth(month);
               	io.studentOutput(student);
               	return false; // If the user  need to pay finement, then return false;
         } else {
               	student.setDataDay(date);
               	student.setDataMonth(month);
               	io.studentOutput(student);
                return true;
         }
         
    }

}
