
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * This class contains basic input and output method for parkingLot.
 * The data of parkingLot is saved in a txt file named Database.
 *
 * @author Group 44
 * @version 2.0
 */
public class ParkinglotIO {

    public void parkingLotOutput(ParkingLot QM) {
        

        try {
            FileOutputStream fos = new FileOutputStream(new File("Database"));
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(QM);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ParkingLot parkingLotInput() throws IOException, ClassNotFoundException {
    		ParkingLot QM=new ParkingLot();
        try {
            File file = new File("Database");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream iis = new ObjectInputStream(fis);

            QM = (ParkingLot) iis.readObject();
            iis.close();
            //System.out.println(QM.getBikeInA());
            //System.out.println(QM.getBikeInB());
            //System.out.println(QM.getBikeOnTheWay());
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return QM;
    }


}
