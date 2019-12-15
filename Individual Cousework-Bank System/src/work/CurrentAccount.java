package work;
/**
* CurrentAccount, an entity class that extends BankAccount, the user can withdraw money that exceeds an overdraft limit.
*Demo--<b>BankSystem</b>
*
*@author shenlinger
*
*@version  2019/05/11
*/
public class CurrentAccount extends BankAccount{
	public CurrentAccount(int accNo, String accName,int PIN) {
		super(accNo, accName,PIN);
		this.accType="Current";
	}
	public CurrentAccount() {
		super();
	}
	
	@Override
	public int withdraw(double amount) {
		if (isActive == true) {				// If account is suspended, withdrawal process cannot be finished.
			return 1;
		} else if (amount > balance+limit) {		// check if the user have enough money.
			return 3;
		} else {							// Withdrawing.
			this.balance = this.balance - amount;
			return 0;
		}
	}
}
