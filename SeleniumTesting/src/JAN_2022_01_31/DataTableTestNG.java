package JAN_2022_01_31;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataTableTestNG {

	private WebDriver driver;

	private DataTable example1;
	private DataTable example2;

	private int t1NumRows;
	private int t1NumColumns;
	private int t2NumRows;
	private int t2NumColumns;
	private List<Employee> empTable1;
	private List<Employee> empTable2;
	private List<String> t1Headers;
	private List<String> t2Headers;

	@Test
	public void Test_01() { // Test DataTable equals() method

		/*
		 * Expected assertion SUCCESS because table 1 and table 2 have the same number
		 * of rows and columns, headers are in the same order and each row of data can
		 * be found within the other table
		 */
		assertEquals(example1, example2);
	}

	@Test
	public void Test_02() { // Test getNumberOfColumns() method

		/*
		 * Expected assertion SUCCESS because table 1 and table 2 have the same number
		 * of rows
		 */
		example1 = new DataTable(t1NumRows, t1NumColumns, empTable1, t1Headers);
		example2 = new DataTable(t2NumRows, t2NumColumns, empTable2, t2Headers);

		assertTrue(example1.getNumberOfColumns() == example2.getNumberOfColumns());

	}

	@Test
	public void Test_03() { // Test sameHeaderOrder() method

		/*
		 * Expected assertion SUCCESS because table 1 and table 2 have the same header
		 * order for the same data.
		 */

		example1 = new DataTable(t1NumRows, t1NumColumns, empTable1, t1Headers);
		example2 = new DataTable(t2NumRows, t2NumColumns, empTable2, t2Headers);

		assertTrue(example1.sameHeaderOrder(example2.getTableHeaders()));
		assertTrue(example2.sameHeaderOrder(example1.getTableHeaders()));

	}

	@Test
	public void Test_04() { // Test getNumberOfRows() method

		/*
		 * Expected assertion SUCCESS because table 1 and table 2 have the same number
		 * of rows
		 */

		example1 = new DataTable(t1NumRows, t1NumColumns, empTable1, t1Headers);
		example2 = new DataTable(t2NumRows, t2NumColumns, empTable2, t2Headers);

		assertTrue(example1.getNumberOfRows() == example2.getNumberOfRows());

	}

	@Test
	public void Test_05() { // Test employeeExist() method

		/*
		 * Expected assertion SUCCESS because each data row within table 1 can be found
		 * within table 2 regardless of sort order.
		 */
		example1 = new DataTable(t1NumRows, t1NumColumns, empTable1, t1Headers);
		example2 = new DataTable(t2NumRows, t2NumColumns, empTable2, t2Headers);

		assertTrue(example1.employeeExist(example2));
		assertTrue(example2.employeeExist(example1));

	}

	@Test
	public void Test_06() { // Test getNumberOfColumns() method

		/*
		 * Expected assertion FAIL because table 1 and table 2 have different number of
		 * columns
		 * 
		 */

		example1 = new DataTable(t1NumRows, 7, empTable1, t1Headers);
		example2 = new DataTable(t2NumRows, t2NumColumns, empTable2, t2Headers);

		assertFalse(example1.getNumberOfColumns() == example2.getNumberOfColumns());

		example1 = new DataTable(t1NumRows, t1NumColumns, empTable1, t1Headers);
		example2 = new DataTable(t2NumRows, 7, empTable2, t2Headers);

		assertFalse(example1.getNumberOfColumns() == example2.getNumberOfColumns());
	}

	@Test
	public void Test_07() { // Test sameHeaderOrder() method

		/*
		 * Expected assertion FAIL because table 1 and table 2 have different header
		 * order
		 */
		List<String> tempHeaders = new ArrayList<String>();

		for (int i = 0; i < example1.getNumberOfColumns(); i++) {
			tempHeaders.add(example1.getTableHeaders().get(i));
		}

		String temp = tempHeaders.get(0);
		tempHeaders.set(0, tempHeaders.get(2));
		tempHeaders.set(2, temp);

		example1 = new DataTable(t1NumRows, t1NumColumns, empTable1, tempHeaders);
		example2 = new DataTable(t2NumRows, t2NumColumns, empTable2, t2Headers);

		assertFalse(example1.sameHeaderOrder(example2.getTableHeaders()));
		assertFalse(example2.sameHeaderOrder(example1.getTableHeaders()));
	}

	@Test
	public void Test_08() { // Test getNumberOfRows() method

		/*
		 * Expected assertion FAIL because table 1 and table 2 have different number of
		 * rows
		 */

		example1 = new DataTable(7, t1NumColumns, empTable1, t1Headers);
		example2 = new DataTable(t2NumRows, t2NumColumns, empTable2, t2Headers);

		assertFalse(example1.getNumberOfRows() == example2.getNumberOfRows());

		example1 = new DataTable(t1NumRows, t1NumColumns, empTable1, t1Headers);
		example2 = new DataTable(7, t2NumColumns, empTable2, t2Headers);

		assertFalse(example1.getNumberOfRows() == example2.getNumberOfRows());

	}

	@Test
	public void Test_09() { // Test employeeExist() method
		/*
		 * Expected assertion SUCCESS because each data row that exists within table 1
		 * also exists within table 2 regardless of sort order.
		 */
		List<Employee> tempEmpTable = new ArrayList<Employee>();

		for (int i = 0; i < example1.getNumberOfRows(); i++) {
			tempEmpTable.add(example1.getEmployeeList().get(i));
		}

		Employee temp = tempEmpTable.get(0);
		Employee temp2 = tempEmpTable.get(1);

		tempEmpTable.set(0, tempEmpTable.get(3));
		tempEmpTable.set(3, temp);
		tempEmpTable.set(1, tempEmpTable.get(2));
		tempEmpTable.set(2, temp2);

		example1 = new DataTable(t1NumRows, t1NumColumns, tempEmpTable, t1Headers);
		example2 = new DataTable(t2NumRows, t2NumColumns, empTable2, t2Headers);

		assertTrue(example1.employeeExist(example2));
		assertTrue(example2.employeeExist(example1));

		assertTrue(example1.equals(example2));

	}

	@Test
	public void Test_10() { // Test employeeExist() method and DataTable equals() method
		/*
		 * Expected assertion FAIL because there exists a row of data within table 1
		 * that does not exist within table 2. In this case, there is a duplicate data
		 * row within table 1, therefore there exists a data row within table 2 that
		 * does not exist within table 1.
		 */

		List<Employee> tempEmpTable = new ArrayList<Employee>();

		for (int i = 0; i < example1.getNumberOfRows(); i++) {
			tempEmpTable.add(example1.getEmployeeList().get(i));
		}

		Employee temp = tempEmpTable.get(0);

		tempEmpTable.set(0, tempEmpTable.get(3));
		tempEmpTable.set(3, temp);
		tempEmpTable.set(1, tempEmpTable.get(2));

		example1 = new DataTable(t1NumRows, t1NumColumns, tempEmpTable, t1Headers);
		example2 = new DataTable(t2NumRows, t2NumColumns, empTable2, t2Headers);

		assertTrue(example1.employeeExist(example2));
		assertFalse(example2.employeeExist(example1));

		assertFalse(example1.equals(example2));

	}

	@Test
	public void Test_11() { // Test employeeExist() method and DataBase equals() method

		/*
		 * Expected assertion FAIL because there exists a row of data within table 1
		 * that does not exist within table 2. In this case, there is a data row with
		 * different data within table 1 and does not exist within table 2.
		 */

		List<Employee> tempEmpTable = new ArrayList<Employee>();

		for (int i = 0; i < example1.getNumberOfRows(); i++) {
			tempEmpTable.add(example1.getEmployeeList().get(i));
		}

		tempEmpTable.get(0).setFirstName("Michael");
		tempEmpTable.get(0).setLastName("Yeung");

		example1 = new DataTable(t1NumRows, t1NumColumns, tempEmpTable, t1Headers);
		example2 = new DataTable(t2NumRows, t2NumColumns, empTable2, t2Headers);

		assertFalse(example1.employeeExist(example2));
		assertFalse(example2.employeeExist(example1));

		assertFalse(example1.equals(example2));

	}

	@BeforeTest
	private void initializeDriver() {

		// Initializes WebDriver
		System.setProperty("webdriver.chrome.driver", "D:\\Eric\\Programs\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/tables");

		initializeTables();
	}

	@AfterTest
	public void finishTests() {
		driver.close();
	}

	private void initializeTables() {

		// Get number of rows and columns of tables
		List<WebElement> tableRows = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr"));
		t1NumRows = tableRows.size();

		List<WebElement> tableColumns = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td"));
		t1NumColumns = tableColumns.size();

		tableRows = driver.findElements(By.xpath("//*[@id=\"table2\"]/tbody/tr"));
		t2NumRows = tableRows.size();

		tableColumns = driver.findElements(By.xpath("//*[@id=\"table2\"]/tbody/tr[1]/td"));
		t2NumColumns = tableColumns.size();

		// Iterate through table 1 and table 2 and retrieve data
		empTable1 = new ArrayList<Employee>();
		empTable2 = new ArrayList<Employee>();

		for (int i = 1; i <= t1NumRows; i++) {

			String firstName = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i + "]/td[1]")).getText();
			String lastName = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i + "]/td[2]")).getText();
			String email = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i + "]/td[3]")).getText();
			String amountDue = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i + "]/td[4]")).getText();
			String website = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i + "]/td[5]")).getText();

			Employee e = new Employee(firstName, lastName, email, amountDue, website);

			empTable1.add(e);
		}

		for (int i = 1; i <= t2NumRows; i++) {
			String firstName = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[" + i + "]/td[1]")).getText();
			String lastName = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[" + i + "]/td[2]")).getText();
			String email = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[" + i + "]/td[3]")).getText();
			String amountDue = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[" + i + "]/td[4]")).getText();
			String website = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[" + i + "]/td[5]")).getText();

			Employee e = new Employee(firstName, lastName, email, amountDue, website);

			empTable2.add(e);
		}

		// Get order and number of headers for table 1 and table 2
		List<WebElement> table1Headers = driver.findElements(By.xpath("//*[@id=\"table1\"]/thead/tr/th"));
		int t1NumberOfHeaders = table1Headers.size();

		List<WebElement> table2Headers = driver.findElements(By.xpath("//*[@id=\"table2\"]/thead/tr/th"));
		int t2NumberOfHeaders = table2Headers.size();

		t1Headers = new ArrayList<String>();
		t2Headers = new ArrayList<String>();

		for (int i = 1; i <= t1NumberOfHeaders; i++) {
			t1Headers.add(driver.findElement(By.xpath("//*[@id=\"table1\"]/thead/tr/th[" + i + "]")).getText());
		}

		for (int i = 1; i <= t2NumberOfHeaders; i++) {
			t2Headers.add(driver.findElement(By.xpath("//*[@id=\"table2\"]/thead/tr/th[" + i + "]")).getText());
		}

	}
}
