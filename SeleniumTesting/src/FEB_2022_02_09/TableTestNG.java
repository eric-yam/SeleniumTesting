package FEB_2022_02_09;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

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
	public void Has_Same_Rows() {
		// Same number of rows
		Table t1 = new Table("table1", driver);
		Table t2 = new Table("table2", driver);

		assertEquals(t1.getRows().size(), t2.getRows().size());

	}

	@Test
	public void Has_Same_Columns() {
		// Same number of columns
		Table t1 = new Table("table1", driver);
		Table t2 = new Table("table2", driver);
		assertEquals(t1.getHeaders().size(), t2.getHeaders().size());
	}

	@Test
	public void Same_Header_Data_Order() {
		// Same header row order
		Table t1 = new Table("table1", driver);
		Table t2 = new Table("table2", driver);

		List<String> t2Headers = t2.getHeaders();

		assertTrue(t1.sameHeaderOrder(t2Headers));
	}

	@Test
	public void Row_Exist() {
		// Each row exists within the other table regardless of sort order
		Table t1 = new Table("table1", driver);
		Table t2 = new Table("table2", driver);

		List<TableData> t1DataRow = t1.getRows();
		List<TableData> t2DataRow = t2.getRows();

		assertTrue(t1.tableRowExist(t2DataRow));
		assertTrue(t2.tableRowExist(t1DataRow));
	}

	@Test
	public void Table_Equals() {
		// Both tables are equivalent when both tables have the same number of rows,
		// columns, column order and each row can be found within the other table
		// regardless of sort order

		Table t1 = new Table("table1", driver);
		Table t2 = new Table("table2", driver);

		assertEquals(t1, t2);
	}

	@Test
	public void Test_Different_Order() {
		// Test tables of different sort order, but still exists
		Table t1 = new Table("table1", driver);

		List<TableData> exampleRows = t1.getRows();

		TableData temp = exampleRows.get(0);

		exampleRows.set(0, exampleRows.get(1));
		exampleRows.set(1, temp);

		assertTrue(t1.getRows().size() == exampleRows.size());
		assertTrue(t1.tableRowExist(exampleRows));

	}

	@Test
	public void Test_Missing_Data_Row() {
		// Test tables where an entry does not exist
		Table t1 = new Table("table1", driver);

		List<TableData> exampleRows = t1.getRows();
		TableData exampleData = new TableData();
		exampleData.withLastName("Wong");
		exampleData.withFirstName("William");
		exampleData.withEmail("william@hotmail.com");
		exampleData.withDue("$23890746");
		exampleData.withWebsite("william.com");
		exampleRows.set(2, exampleData);

		assertFalse(t1.tableRowExist(exampleRows));

	}

	@Test
	public void Test_Missing_Data_Row_2() {
		// Only one of the tablerows can be found in the other table

		Table t1 = new Table("table1", driver);
		List<TableData> exampleRows = t1.getRows();

		exampleRows.set(0, t1.getRow(0));
		exampleRows.set(1, t1.getRow(0));
		exampleRows.set(2, t1.getRow(0));
		exampleRows.set(3, t1.getRow(0));

		assertFalse(t1.tableRowExist(exampleRows));

	}

	@Test
	public void Test_Header_Order_Different() {
		// Header order is different

		Table t1 = new Table("table1", driver);

		List<String> exampleHeaders = t1.getHeaders();
		exampleHeaders.set(0, "First Name");
		exampleHeaders.set(1, "Last Name");

		assertFalse(t1.sameHeaderOrder(exampleHeaders));

	}

	@Test
	public void Test_Header_Data_Different() {
		// Header data is different

		Table t1 = new Table("table1", driver);

		List<String> exampleHeaders = t1.getHeaders();
		exampleHeaders.set(0, "Company");
		exampleHeaders.set(1, "Games");
		exampleHeaders.set(2, "Course");
		exampleHeaders.set(3, "ID");

		assertFalse(t1.sameHeaderOrder(exampleHeaders));
	}

	@Test
	public void Test_Table_Different_Data() {
		// data of tables are different

		Table t1 = new Table("table1", driver);

		List<String> exampleHeaders = t1.getHeaders();
		exampleHeaders.set(0, "Company");
		exampleHeaders.set(1, "Games");
		exampleHeaders.set(2, "Course");
		exampleHeaders.set(3, "ID");

		List<TableData> exampleRows = t1.getRows();

		TableData exampleData = new TableData();
		TableData exampleData2 = new TableData();

		exampleData.withLastName("Wong");
		exampleData.withFirstName("William");
		exampleData.withEmail("william@hotmail.com");
		exampleData.withDue("$23890746");
		exampleData.withWebsite("william.com");
		exampleRows.set(2, exampleData);

		exampleData2.withLastName("Jericho");
		exampleData2.withFirstName("public");
		exampleData2.withEmail("@live.ca");
		exampleData2.withDue("$23890746");
		exampleData2.withWebsite("william.com");
		exampleRows.set(3, exampleData2);

		assertFalse(t1.sameHeaderOrder(exampleHeaders));
		assertFalse(t1.tableRowExist(exampleRows));
	}
}
