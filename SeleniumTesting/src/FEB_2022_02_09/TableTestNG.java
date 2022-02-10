package FEB_2022_02_09;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TableTestNG {

	private WebDriver driver;

	@BeforeTest
	public void initializeDriver() {

		// Initializes WebDriver
		System.setProperty("webdriver.chrome.driver", "D:\\Eric\\Programs\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/tables");
	}

	@AfterTest
	public void finishTests() {
		driver.close();
	}

	@Test
	public void Test_01() {
		// Same number of rows
		Table t1 = new Table("table1", driver);
		Table t2 = new Table("table2", driver);

		assertEquals(t1.getRows().size(), t2.getRows().size());

	}

	@Test
	public void Test_02() {
		// Same number of columns
		Table t1 = new Table("table1", driver);
		Table t2 = new Table("table2", driver);
		assertEquals(t1.getHeaders().size(), t2.getHeaders().size());
	}

	@Test
	public void Test_03() {
		// Same header row order
		Table t1 = new Table("table1", driver);
		Table t2 = new Table("table2", driver);

		assertTrue(t1.sameHeaderOrder(t2));
	}

	@Test
	public void Test_04() {
		// Each row exists within the other table regardless of sort order
		Table t1 = new Table("table1", driver);
		Table t2 = new Table("table2", driver);

		assertTrue(t1.tableRowExist(t2));
		assertTrue(t2.tableRowExist(t1));
	}

	@Test
	public void Test_05() {
		// Both tables are equivalent when both tables have the same number of rows,
		// columns, column order and each row can be found within the other table
		// regardless of sort order
		
		Table t1 = new Table("table1", driver);
		Table t2 = new Table("table2", driver);
		
		assertEquals(t1,t2);
	}
}
