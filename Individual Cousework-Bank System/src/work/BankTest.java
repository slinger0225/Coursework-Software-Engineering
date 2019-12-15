package work;
/**
* JUnit test cased that used to test the performance.
*Demo--<b>BankSystem</b>
*
*@author shenlinger
*
*@version  2019/05/11
*/
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
public class BankTest {

	@Test
	public void test() {
		CurrentAccount demo=new CurrentAccount(2016213272,"shenlinger",167888);
		demo.setLimit(50);
		demo.setBalance(100);
		System.out.println(demo);
		int value=demo.withdraw(160);
		assertEquals(value,3);
		
		Date now=new Date();
		SaverAccount demo2=new SaverAccount(2016213221,"shenlinger",167888);
		System.out.println(demo2);
		demo2.setNoticePeriod(2);
		demo2.setBalance(100);
		System.out.println(demo2);

		int value1=demo2.withdraw(20);
		assertEquals(value1,2);
		demo2.setStartTime(now.getTime()-(1000 * 60 * 60 * 24)*3);
		int value2=demo2.withdraw(20);
		assertEquals(value2,0);
	}

}
