import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains all the information of students.
 * @author Group 44
 * @version 1.5
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 5469789632629415141L;
    private ArrayList<int[]> totalList = new ArrayList<int[]>();
    private String QMNum = "";
    private String name;
    private String email;
    private int currentTime = 0;
    private int totalTime = 0;
    private boolean differentDay = false;
    private int dataMonth = 0;
    private int dataDay = 0;
    private boolean fine=true;//If the user hasn't got unpaid finement, return true.
    public Student() {

    }
    public Student(String QMNum, String name, String email) {
        this.QMNum = QMNum;
        this.name = name;
        this.email = email;
    }

    public void setQMNum(String QMNum) {
        this.QMNum = QMNum;
    }

    public String getQMNum() {
        return this.QMNum;
    }

    public void setStudentName(String name) {
        this.name = name;
    }

    public String getStudentName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public int getCurrentTime() {
        return this.currentTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return this.totalTime;
    }


    public void setDifferentDay(boolean differentDay) {
        this.differentDay = differentDay;
    }

    public boolean getDifferentDay() {
        return this.differentDay;
    }

    public boolean getDifferentDay(int dataMonth, int dataDay) {
        if (this.dataMonth == dataMonth && this.dataDay == dataDay)
            return false;
        else
            return true;
    }

    public void setDataMonth(int dataMonth) {
        this.dataMonth = dataMonth;
    }

    public int getDataMonth() {
        return this.dataMonth;
    }

    public void setDataDay(int dataDay) {
        this.dataDay = dataDay;
    }

    public int getDataDay() {
        return this.dataDay;
    }
    public void setFine(boolean fine){
        this.fine=fine;
    }
    public boolean getFine(){
        return this.fine;
    }
 
    public void setTotalList(ArrayList<int[]> totalList) {
		this.totalList=totalList;
    }
    public ArrayList<int[]> getTotalList() {
		return this.totalList; 
    }
    public  ArrayList<int[]> calTotalList(int month,int day, int time) {
    		int[] eachTime= {month,day,time};
    		totalList=this.getTotalList();
    		totalList.add(eachTime);
    		return totalList;
    }
    public boolean checkStatus() {
	 	if (this.getCurrentTime() == 0) {
            return true;
        } else {
        		return false;
        }
}

    public String toString() {
		return "QM Number:"+QMNum+"\n"+"name: " + name+ "\n"+"email:"+email+"\n" + "current time: " + currentTime
				+ "\n"  + "total time: " + totalTime + "\n" 
				 + "different day: "+ differentDay + "\n"  + "Month: "
				+ dataMonth + "\n" + "Day: "+ dataDay + "\n"+"fine:"+fine+"\n"+"total list:"+totalList+"\n";
	}
}
