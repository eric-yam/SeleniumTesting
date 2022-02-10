package FEB_2022_02_09;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import JAN_2022_01_31.Employee;

public class Table implements GenericTable {

	// Attributes
	private WebDriver driver;
	private String tableID;

	// Constructor
	public Table(String tableID, WebDriver d) {
		this.tableID = tableID;
		this.withDriver(d);
	}

	// Accessor methods
	@Override
	public GenericTable withDriver(WebDriver driver) {

		this.driver = driver;
		return this;
	}

	@Override
	public GenericTable withExtraConfig() {

		return this;
	}

	// Returns the columns/header labels of the table
	@Override
	public List<String> getHeaders() {
		List<String> headerNames = new ArrayList<String>();
		List<WebElement> colNames = driver.findElements(By.xpath("//*[@id=\"" + this.tableID + "\"]/thead/tr/th"));
		int numCol = colNames.size();

		for (int i = 1; i < numCol; i++) {
			String s = driver.findElement(By.xpath("//*[@id=\"" + this.tableID + "\"]/thead/tr/th[" + i + "]"))
					.getText();
			headerNames.add(s);
		}

		return headerNames;
	}

	// Returns the columns from table
	@Override
	public TableData getHeadersRow() {
		TableData headerRow = new TableData();

		headerRow.withLastName(this.getHeaders().get(0));
		headerRow.withFirstName(this.getHeaders().get(1));
		headerRow.withEmail(this.getHeaders().get(2));
		headerRow.withDue(this.getHeaders().get(3));
		headerRow.withWebsite(this.getHeaders().get(4));

		return headerRow;
	}

	// Returns a data row at a particular index
	@Override
	public TableData getRow(int index) {
		TableData row = new TableData();
		List<TableData> dataRows = this.getRows();

		row = dataRows.get(index);

		return row;
	}

	// Returns all the rows from table
	@Override
	public List<TableData> getRows() {

		List<TableData> dataRows = new ArrayList<TableData>();
		List<WebElement> rowElements = driver.findElements(By.xpath("//*[@id=\"" + this.tableID + "\"]/tbody/tr"));
		int numRows = rowElements.size();

		for (int i = 1; i <= numRows; i++) {

			WebElement tableRow = driver.findElement(By.xpath("//*[@id=\"" + this.tableID + "\"]/tbody/tr[" + i + "]"));

			TableData t = new TableData(tableRow);

			dataRows.add(t);
		}

		return dataRows;
	}

	public boolean sameHeaderOrder(Table other) {

		boolean isSame = true;
		List<String> table1 = this.getHeaders();
		List<String> table2 = other.getHeaders();
		int numCol = this.getHeaders().size();

		for (int i = 0; i < numCol; i++) {
			isSame = isSame && (table1.get(i).equals(table2.get(i)));
		}

		return isSame;
	}

	public boolean tableRowExist(Table other) {

		boolean isExist = true;
		int numRows = this.getRows().size();
		List<TableData> table1 = this.getRows();
		List<TableData> table2 = other.getRows();

		for (int i = 0; i < numRows; i++) {
			boolean isEqual = false;
			for (int j = 0; j < numRows; j++) {

				isEqual = isEqual || table1.get(i).equals(table2.get(j));
			}

			isExist = isExist && isEqual;
		}

		return isExist;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		} else {

			Table other = (Table) obj;
			

			// Both tables must have the same number of rows and columns
			// Both tables must have the same order of headers
			// The entire row of data except actions must be found in the other table
			// regardless of sort order

			return this.getRows().size() == other.getRows().size()
					&& this.getHeaders().size() == other.getHeaders().size() && this.sameHeaderOrder(other)
					&& this.tableRowExist(other) && other.tableRowExist(this);
		}

	}

}
