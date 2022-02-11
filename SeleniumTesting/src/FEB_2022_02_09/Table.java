package FEB_2022_02_09;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public boolean sameHeaderOrder(List<String> t2Headers) {

		boolean isSame = true;
		List<String> t1Headers = this.getHeaders();

		for (int i = 0; i < t1Headers.size(); i++) {
			isSame = isSame && (t1Headers.get(i).equals(t2Headers.get(i)));
		}

		return isSame;
	}

	public boolean tableRowExist(List<TableData> t2DataRows) {

		boolean isExist = true;
		List<TableData> t1DataRows = this.getRows();

		for (int i = 0; i < t1DataRows.size(); i++) {
			boolean isEqual = false;
			for (int j = 0; j < t1DataRows.size() && !isEqual; j++) {

				isEqual = isEqual || t1DataRows.get(i).equals(t2DataRows.get(j));
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

			List<String> t1Headers = this.getHeaders();
			int t1Col = t1Headers.size();
			List<String> t2Headers = other.getHeaders();
			int t2Col = t2Headers.size();

			List<TableData> t1DataRow = this.getRows();
			int t1Rows = t1DataRow.size();
			List<TableData> t2DataRow = other.getRows();
			int t2Rows = t2DataRow.size();

			// Both tables must have the same number of rows and columns
			// Both tables must have the same order of headers
			// The entire row of data except actions must be found in the other table
			// regardless of sort order

			return t1Rows == t2Rows && t1Col == t2Col && this.sameHeaderOrder(t2Headers)
					&& this.tableRowExist(t2DataRow) && other.tableRowExist(t1DataRow);

		}

	}

}
