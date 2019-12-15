import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import java.util.*;

public class ScooterTest_Return1 {

	@Test
	public void test() {
		ParkingLot QM=new ParkingLot();
		ParkinglotIO ParkingIO=new ParkinglotIO();
		try {
	 		QM=ParkingIO.parkingLotInput();
	 	} catch (ClassNotFoundException | IOException e) {
	 		e.printStackTrace();
	 	}
		
		Student student=new Student();
		StudentIO io=new StudentIO();
	 	try {
	 		student=io.studentInput("169999777");
	 	} catch (ClassNotFoundException | IOException e) {
	 		e.printStackTrace();
	 	}
	 	Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int time = ((month * 30 + date) * 24 + hour) * 60 + minute;
	 	student.setDataMonth(4);
		student.setDataDay(24);
	 	student.setCurrentTime(time-20);
	 	student.setTotalTime(101);
	 	io.studentOutput(student);
	 	System.out.println(student);
		
		ReturnBike demo= new ReturnBike();
		boolean val=demo.returnBike(1, "169999777");
		assertFalse(val);
		assertEquals((QM.getBikeInA()+1),demo.QM.getBikeInA());
	
		
	}
	

}
