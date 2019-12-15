
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.io.*;
/**
 * Check how many scooters in each station and how many scooters on the way.
 * @author Group 44
 * @version 2.0
 */
public class CheckStationReport {
	private ParkingLot QM=new ParkingLot();
	private ParkinglotIO pio=new ParkinglotIO();
	String stationReport;
	public void CheckStaionReport() {
		try{
			QM=pio.parkingLotInput();
		}catch(ClassNotFoundException | IOException ex){
		ex.printStackTrace();
		}
		stationReport="Station A:\nAvailable:"+QM.getBikeInA()+" Total:8\n---------\nStation B:\nAvailable:"+QM.getBikeInB()+" Total:8\n--------\nStation C:\nAvailable:"+QM.getBikeInC()+" Total:8\n\n-----------------\nThere are "+QM.getBikeOnTheWay()+" scooters on the way.";

	}

}
