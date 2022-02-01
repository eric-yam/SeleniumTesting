package JAN_2022_01_31;

import java.util.List;

public class DataTable {

	// Attributes
	private int nor; // Number of rows
	private int noc; // Number of columns
	private List<Employee> employees;
	private List<String> tableHeaders;

	// Constructor
	public DataTable(int nor, int noc, List<Employee> e, List<String> th) {
		this.nor = nor;
		this.noc = noc; 
		this.employees = e;
		this.tableHeaders = th; 
	}

	// Mutator methods
	public void setNumberOfRows(int nor) {
		this.nor = nor;
	}

	public void setNumberOfColumns(int noc) {
		this.noc = noc;
	}

	public void setEmployeeList(List<Employee> e) {
		this.employees = e;
	}

	public void setTableHeaders(List<String> th) {
		this.tableHeaders = th;
	}

	// Accessor methods
	public int getNumberOfRows() {
		return this.nor;
	}

	public int getNumberOfColumns() {
		return this.noc;
	}

	public List<Employee> getEmployeeList() {
		return this.employees;
	}

	public List<String> getTableHeaders() {
		return this.tableHeaders;
	}

	/*
	 * Check that all headers in table 1 and table 2 matches in order and in data.
	 * Utilizing a boolean flag and a conjunction statement, accumulate the boolean
	 * result at each iteration to ensure all headers match in order and data at
	 * iteration i for both tables.
	 */
	public boolean sameHeaderOrder(List<String> otherTableHeader) {

		boolean isEqual = true;

		for (int i = 0; i < this.noc; i++) {
			isEqual = isEqual && (this.tableHeaders.get(i).equals(otherTableHeader.get(i)));
		}

		return isEqual;
	}

	/*
	 * Checks that all data rows that exist within table 1 also exist within table
	 * 2. Utilizes a boolean flag, disjunction and conjunction statement. A data row
	 * exists in both tables if the data rows at iteration i in both tables are
	 * content equal.
	 */
	public boolean employeeExist(DataTable other) {

		boolean isExist = true;

		for (int i = 0; i < this.nor; i++) {

			boolean isEqual = false;

			for (int j = 0; j < this.nor; j++) {

				isEqual = isEqual || this.employees.get(i).equals(other.employees.get(j));
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

			DataTable other = (DataTable) obj;
			boolean isEqual = false;

			// Both tables must have the same number of rows and columns
			// Both tables must have the same order of headers
			// The entire row of data except actions must be found in the other table
			// regardless of sort order

			if (this.nor == other.nor && this.noc == other.noc && this.sameHeaderOrder(other.tableHeaders)
					&& this.employeeExist(other) && other.employeeExist(this)) {

				isEqual = true;

			}

			return isEqual;
		}

	}

}
