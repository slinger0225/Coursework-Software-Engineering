

import java.io.IOException;
import java.util.Calendar;
/**
 * This part realises the "Return scooter"function.
 *
 * @author Group 44
 * @version 3.0
 */
public class ReturnBike extends CheckFine{
	private ParkinglotIO pio=new ParkinglotIO();
	public ParkingLot QM=new ParkingLot();
    //向A地还车，并储存还车信息，成功返回1，A地无空位返回0

    /**
     * Return scooter method.
     * @param buttonChecker the station a user want to return his or her scooter to.
     * @param IDNum The QM number of a user.
     * @return return false if the user overused the scooter and need to pay the fine, true if not.
     */
    public boolean returnBike(int buttonChecker,String IDNum){
    	 try {
     		QM=pio.parkingLotInput();
     } catch (ClassNotFoundException | IOException e) {
         	e.printStackTrace();
     }
    	 /*
        if (User_login.buttonChecker1.getButtonCheck()==1&&ParkinglotIO.QM1.getBikeInA() == 8) {
            return 0;
        } 
        else if (User_login.buttonChecker1.getButtonCheck()==2&&ParkinglotIO.QM1.getBikeInB() == 8) {
        		return 0;
        }
        else if (User_login.buttonChecker1.getButtonCheck()==3&&ParkinglotIO.QM1.getBikeInC() == 8) {
        		return  0;
        }else {
       */
    	 if(buttonChecker==1) {
            QM.returnBikeToA();
    	 }
     else if(buttonChecker==2) {
            QM.returnBikeToB();
     }
     else if(buttonChecker==3) {
            	QM.returnBikeToC();
     }
    	 pio.parkingLotOutput(QM);
    	 boolean val=checkFine(IDNum);//If the user  need to pay finement, then return false.
    	 return val;
    }
 }
