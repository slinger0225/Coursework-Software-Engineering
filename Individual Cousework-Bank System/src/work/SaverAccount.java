package work;
/**
* SaverAccount, an entity class that extends BankAccount, the user could only withdraw money after a notic period.
*Demo--<b>BankSystem</b>
*
*@author shenlinger
*
*@version  2019/05/11
*/
import java.util.Date;

public class SaverAccount extends BankAccount {

	

	public SaverAccount(int accNo, String accName,int PIN) {
		super(accNo,accName,PIN);
		this.accType="Saver";
		Date createDate=new Date();
		this.startTime=createDate.getTime();
		this.noticePeriod=7;
	}
	public SaverAccount() {
		super();
	}
	

	@Override
	public int withdraw(double amount) {
		Date now=new Date();
		if (isActive == true) {				// If account is suspended, withdrawal process cannot be finished.
			return 1;
		} else if (amount > balance) {		// check if the user have enough money.
			return 2;
		} else if (((now.getTime() - startTime)) / (1000 * 60 * 60 * 24) < this.getNoticePeriod()) {  // calculate the notice  period.
			return (int)(this.getNoticePeriod()-(now.getTime() - startTime) / (1000 * 60 * 60 * 24)); //return the rest of notice time.
		} else {
			balance = balance - amount;
			return 0;
		}
	}

}
