# SeleniumTesting

This project validates the equality of 2 tables (Example 1 and Example 2).
The setup of this project requires the installation of Selenium version 3 and the TestNG plugin for Eclipse.

The solution assumes that we can capture each data row as a TableData object which contains the following fields, lastName, firstName, email, due, and website. A TableData object
is instantiated by passing in the tableRow WebElement of a particular table as arguments or no arguments at all (overloaded constructor). A TableData object is considered equal to 
another TableData object when they are content equal. In this case, all the fields within a TableData object are equal to another TableData object's fields.

The solution assumes that we can capture each table as a Table object which is a subclass of the GenericTable interface class in which each inherited routine is implemented 
specific to the Table subclass. Instantiation of the Table class requires the arguments of the tableID for the xpath, and the WebDriver. The Table class also adds the additional 
methods: sameHeaderOrder(List<String> t2Headers) and tableRowExist(List<TableData> t2DataRows) which are methods used to help validate equality between 2 Table objects. For 2 
Table objects to be equivalent, both tables must have the same number of rows, columns, same order of table headers with the same data, and each data row within a table must 
exist within the other table regardless of sort order.

In the TableTestNG class, the first 5 test cases are used to test the equality between the 2 tables from the website (Has_Same_Rows(), Has_Same_Columns(), 
Same_Header_Data_Order(), Row_Exist(), and Table_Equals()).

The subsequent test cases are used to test the correctness of the methods used to help validate the equality between Table objects (sameHeaderOrder() and tableRowExist()). Each 
test case creates a specific scenario to ensure the expected output matches the actual output.

