package work;
/**
* JuniorAccount, an entity class that extends BankAccount, only the user who are less than 16 can open an junior account.
*Demo--<b>BankSystem</b>
*
*@author shenlinger
*
*@version  2019/05/11
*/

public class JuniorAccount extends BankAccount{

	public JuniorAccount(int accNo, String accName,int PIN) {
		super(accNo, accName,PIN);
		this.accType="Junior";
		
	}
	public JuniorAccount() {
		super();
	}
	
}

