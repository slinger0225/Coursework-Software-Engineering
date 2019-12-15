package work;
/**
* BankAccount, an entity class extended by JuniorAccouny, SaverAccount, CurrentAccount. Someone can create, close and suspend account, withdraw and deposit money.
*Demo--<b>BankSystem</b>
*
*@author shenlinger
*
*@version  2019/05/11
*
*/
public class BankAccount {
	protected String accType;
	protected int accNo;
	protected int pIN;
	protected String accName;
	protected String address;
	protected String birthday;
	protected double balance;
	protected boolean isActive;
	protected double uncleared;
	protected int limit;
	protected long startTime;
	protected int noticePeriod;

	public BankAccount(int accNo, String accName,int pIN) {
		this.accNo = accNo;
		this.accName = accName;
		this.balance = 0.0;
		this.isActive = false;
		this.pIN=pIN;
		//this.address=address;
		//this.birthday=birthday;
		
	}

	public BankAccount() {
		
	}
	/**
	 * Getter and Setter
	 */
	public void setAccNo(int accNo) {
		this.accNo=accNo;
	}

	public int getAccNo() {
		return this.accNo;
	}
	
	public String getAccType() {
		return this.accType;
	}
	public void setAccType(String accType) {
		this.accType=accType;
	}

	public int getpIN() {
		return this.pIN;
	}

	public void setpIN(int pIN) {
		this.pIN = pIN;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccName() {
		return this.accName;
	}


	public double getBalance() {
		return this.balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}
	
	public int getLimit() {
		return this.limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(long startTime) {
			this.startTime = startTime;
	}
	public void setNoticePeriod(int period) {
		this.noticePeriod=period;
	}
	public int getNoticePeriod() {
		return this.noticePeriod;
	}
	public void setUncleared(double uncleared) {
		this.uncleared=uncleared;
	}

	public double getUncleared() {
		return this.uncleared;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * Deposit money.
	 * 
	 * 
	 */
	public int deposit(double amount, boolean isCash) {
		if (isActive == true) {				// if the account is suspended, deposit process could not be finished.
			return 1;
		} 
		//check if the deposit type is check or cash
		else {
			if (isCash == true) {			
				this.balance = this.balance + amount;
				return 0;
			} else {	
				this.uncleared=amount;
				return 2;
			}
		}
	}

	/**
	 * Withdraw money.
	 * 
	 *
	 * 
	 */
	public int withdraw(double amount) {
		if (isActive == true) {				// If account is suspended, withdrawal process cannot be finished.
			return 1;
		} else if (amount > balance) {		// check if the user have enough money.
			return 2;
		} else {							// Withdrawing.
			this.balance = this.balance - amount;
			return 0;
		}
	}
	/**
	 * Clear unissued check, and add it to the balance.
	 */
	public void clearCheck() {
		this.balance = this.balance - this.uncleared;
	}
	
	
	/** 
	 * To print out the bank account's information. 
	 */
	public String toString() {
		return "Account Type:"+accType+"\n"+"Account number: " + accNo + "\n"+"pIN:"+pIN+"\n" + "Account name: " + accName
				+ "\n"  + "Balance: " + balance + "\n" + "isActive: "
				+ isActive + "\n" + "Uncleared: "+ uncleared + "\n" + "Overdraft limit: "+ limit + "\n" + "Start Time: "
				+ startTime + "\n" + "Notice Period: "+ noticePeriod + "\n";
	}
}

