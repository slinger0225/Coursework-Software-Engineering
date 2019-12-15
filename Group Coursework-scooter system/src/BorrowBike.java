
import java.io.IOException;
import java.util.Calendar;
/**
 * This part realises the "Borrowing scooter"function.
 *
 * @author Group 44
 * @version 3.0
 */
public class BorrowBike extends CheckFine {
	private Calendar calendar = Calendar.getInstance();
	private int month = calendar.get(Calendar.MONTH);
	private int date = calendar.get(Calendar.DATE);
	private int hour = calendar.get(Calendar.HOUR_OF_DAY);
	private int minute = calendar.get(Calendar.MINUTE);
	private int time = ((month * 30 + date) * 24 + hour) * 60 + minute;
	private ParkinglotIO pio=new ParkinglotIO();
	private StudentIO io=new StudentIO();
	private ParkingLot QM=new ParkingLot();


	/**
	 * Borrow scooter method.
	 * @param buttonChecker The station which the user want to borrow a scooter from.
	 * @param IDNum The user's QM number.
	 */
    public void borrowBike(int buttonChecker,String IDNum) {
        try {
        		QM=pio.parkingLotInput();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        
        /*
        if ((buttonChecker==1)&&(QM.getBikeInA() <= 0)) {
            return 0;
        } 
        else if ((buttonChecker==2)&&(QM.getBikeInB() <= 0)) {
        		return 0;
        }
        else if ((buttonChecker==3)&&(QM.getBikeInC() <= 0)) {
        		return  0;
        }
        */
        	if(buttonChecker==1) {
            QM.takeBikeFromA();
         }
            //getStudentDataFromChart();
        else if(buttonChecker==2) {
        		QM.takeBikeFromB();
        	}
        	else if(buttonChecker==3) {
        		QM.takeBikeFromC();
        	}
        	pio.parkingLotOutput(QM);
        setUserTime(IDNum);
        }
        
}
