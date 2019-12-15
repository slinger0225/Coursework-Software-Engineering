
import java.io.Serializable;
/**
 * This class contains all the information of stations.
 * @author Group 44
 * @version 1.0
 */
public class ParkingLot implements Serializable{
  private static final long serialVersionUID = 4714718780412460032L;
  private  int parkingNumInA;
  private  int parkingNumInB;
  private  int parkingNumInC;
  private int bikeOnTheWay;

    public ParkingLot(){

    }

    public ParkingLot(int parkingNumInA,int parkingNumInB,int parkingNumInC){
      this.parkingNumInA=parkingNumInA;
      this.parkingNumInB=parkingNumInB;
      this.parkingNumInC=parkingNumInC;
    }

    public int getBikeInA(){
      return this.parkingNumInA;
    }
    public void setBikeInA(int num){
       this.parkingNumInA=num;
    }

    public int getBikeInB(){
      return this.parkingNumInB;
    }
    public void setBikeInB(int num){
        this.parkingNumInB=num;
    }

    public int getBikeInC(){
      return this.parkingNumInC;
    }
    public void setBikeInC(int num){
        this.parkingNumInC=num;
    }

    public void setBikeOnTheWay(int bikeOnTheWay){
      this.bikeOnTheWay=bikeOnTheWay;
    }

    public int getBikeOnTheWay(){
      return this.bikeOnTheWay;
    }

    public void takeBikeFromA(){
      this.parkingNumInA=this.parkingNumInA-1;
      this.bikeOnTheWay=this.bikeOnTheWay+1;
    }

    public void takeBikeFromB(){
      this.parkingNumInB=this.parkingNumInB-1;
      this.bikeOnTheWay=this.bikeOnTheWay+1;
    }

    public void takeBikeFromC(){
      this.parkingNumInC=this.parkingNumInC-1;
      this.bikeOnTheWay=this.bikeOnTheWay+1;
    }

    public void returnBikeToA(){
      this.parkingNumInA=this.parkingNumInA+1;
      this.bikeOnTheWay=this.bikeOnTheWay-1;
    }

    public void returnBikeToB(){
      this.parkingNumInB=this.parkingNumInB+1;
      this.bikeOnTheWay=this.bikeOnTheWay-1;
    }

    public void returnBikeToC(){
      this.parkingNumInC=this.parkingNumInC+1;
      this.bikeOnTheWay=this.bikeOnTheWay-1;
    }
    public String toString() {
		return "A:"+parkingNumInA+"\n"+"B: " + parkingNumInB+ "\n"+"C:"+parkingNumInC+"\n"; 
	}
}
