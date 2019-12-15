import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.io.*;
/**
 * Check user's usage and create a txt file to save the user's usage.
 * @author Group 44
 * @version 1.0
 */
public class CheckWeeklyReport {
	protected Student student=new Student();
	protected StudentIO io=new StudentIO();
	protected Calendar calendar;
	protected String id;
	protected int month;
	protected int date;
	protected ArrayList<int[]> timeList=new ArrayList<int[]>();
	protected ArrayList<String> outputList=new ArrayList<String>();
	protected String reportString;
	protected String summary;
	protected void checkReport(Comparator<int[]> comp) {
		calendar = Calendar.getInstance();
		month = calendar.get(Calendar.MONTH);
		date = calendar.get(Calendar.DATE);
		try {
				student=io.studentInput(id);
		} catch (ClassNotFoundException | IOException ex) {
			ex.printStackTrace();
		}
		timeList=student.getTotalList();
		int[] now= {month,date,1};
		int totalWeekTime=0;
		 for(int[] arr : timeList) {
			if(comp.compare(arr,now)>=0) {
				outputList.add((arr[0]+1)+"."+arr[1]+": "+arr[2]+"mins\n");
				totalWeekTime+=arr[2];
			}
			
	}
		summary="Total usage today:"+student.getTotalTime()+" minutes\n"+"Your total usage this week:"+totalWeekTime+"minutes\n";
	}

	/**
	 * Print the user's usage string to a txt file.
	 */
	protected void sendReport() {
	        try {
	            File file = new File("User Report_"+id);
	            PrintStream ps = new PrintStream(new FileOutputStream("User Report_"+id));
	            ps.println("ID number:"+id+"\n");
	    			ps.append("---------Detail--------------\n");
	    			for (int i=0;i<outputList.size();i++) {
	    				ps.append(outputList.get(i));
	    			}
	    			ps.append("-----------------------------\n");
	    			ps.append(summary);  //set the output

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	}


}
