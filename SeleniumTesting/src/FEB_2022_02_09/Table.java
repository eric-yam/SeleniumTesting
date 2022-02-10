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
	public Table(String tableID) {
		this.tableID = tableID;
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

		// error check, what if number of headers is not 4

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

		List<String> tempData;
		List<TableData> dataRows = new ArrayList<TableData>();
		List<WebElement> rowElements = driver.findElements(By.xpath("//*[@id=\"" + this.tableID + "\"]/tbody/tr"));
		int numRows = rowElements.size();
//		List<WebElement> colElements = driver.findElements(By.xpath("//*[@id=\"table"+ this.tableID+"\"]/tbody/tr[1]/td"));
//		int numCol = colElements.size();

//		for (int i = 1; i <= numRows; i++) {
//			tempData = new ArrayList<String>();
//			for (int j = 1; j <= numCol; j++) {
//				tempData.add(driver.findElement(By.xpath("")))
//			}
//		} 

		for (int i = 1; i <= numRows; i++) {

			WebElement tableRow = driver.findElement(By.xpath("//*[@id=\"" + this.tableID + "\"]/tbody/tr[" + i + "]"));

			TableData t = new TableData(tableRow);

			dataRows.add(t);
		}

		// error check, what if table doesn't have the same number of columns, null
		// pointer exception may occur

		return dataRows;
	}

	public boolean sameHeaderOrder(List<String> otherTableHeaders) {

		boolean isSame = true;

		for (int i = 0; i < this.getHeaders().size(); i++) {
			isSame = isSame && (this.getHeaders().get(i).equals(otherTableHeaders.get(i)));
		}

		return isSame;
	}

	public boolean tableRowExist(Table other) {

		boolean isExist = true;

		for (int i = 0; i < this.getRows().size(); i++) {
			boolean isEqual = false;
			for (int j = 0; j < this.getRows().size(); j++) {

				isEqual = isEqual || this.getRow(i).equals(other.getRow(j));
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
				
			return this.getRows().size() == other.getRows().size() && this.getHeaders().size() == other.getHeaders().size()
					&& this.sameHeaderOrder(other.getHeaders()) && this.tableRowExist(other)
					&& other.tableRowExist(this);
		}

	}

}
