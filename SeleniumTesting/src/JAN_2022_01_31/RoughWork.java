package JAN_2022_01_31;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RoughWork {
	
	public static void main(String[] args) {
		
		//Rough Work 
		
		//Initialization
		WebDriver driver;	
	
		System.setProperty("webdriver.chrome.driver", "D:\\Eric\\Programs\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/tables");
		
		
		//Step 1 : Get the number of rows
		//Step 2 : Get the number of columns
		//Step 3 : Iterate over the table and retrieve the data from each column
		//Step 4 : Save data into an Employee object
		//Step 5 : Implement the Employee object
		//Step 6: Implement the equals method
		//Step 7 : Document though process
		
		//table 1 rows xpath : //*[@id="table1"]/tbody/tr (total rows)
		//table 1 columns xpath : //*[@id="table1"]/tbody/tr[1]/td (total columns)
		
		
		//Table 1:
		//Get Number of rows and columns
		List <WebElement> tableRows = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr"));
		int t1NumRows = tableRows.size();
		
		System.out.println("Table 1 Number of Rows : "  + t1NumRows);
		
		List<WebElement> tableColumns = driver.findElements(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td"));
		int t1NumColumns =  tableColumns.size();
		
		
		System.out.println("Table 1 Number of Columns : " + t1NumColumns);
		
		//==============================================================================================================================================================================================
		
		
		//Table 2: 
		//Get number of rows and columns
		
			
		//table 2 rows xpath : //*[@id="table2"]/tbody/tr
		//table 2 columns xpath : //*[@id="table2"]/tbody/tr[1]/td
		
		tableRows = driver.findElements(By.xpath("//*[@id=\"table2\"]/tbody/tr"));
		int t2NumRows = tableRows.size();
		System.out.println("Table 2 Number of Rows : " + t2NumRows);
		
		
		tableColumns = driver.findElements(By.xpath("//*[@id=\"table2\"]/tbody/tr[1]/td"));
		int t2NumColumns = tableColumns.size();
		System.out.println("Table 2 Number of Columns : " + t2NumColumns);
		
		
		
		
		//==============================================================================================================================================================================================
		System.out.println();
		
		//Table 1
		//Iterate through table 1, and retrieve data to build Employee Object 
		
		//Skip the first row (first row is the header names), skip the last column (last col are the actions)
		for (int i = 1; i <= t1NumRows; i++) {
//			System.out.println(driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i + "]" )).getText());
			
			String firstName = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i +  "]/td[1]")).getText();
			String lastName = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i +  "]/td[2]")).getText();
			String email = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i +  "]/td[3]")).getText();
			String amountDue = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i +  "]/td[4]")).getText();
			String website = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i +  "]/td[5]")).getText();
			
			Employee e1 =  new Employee(firstName, lastName, email, amountDue, website);
			
			System.out.println(e1.toString());
			
		}
		
		
		//==============================================================================================================================================================================================
		System.out.println();
		
		//Store the data
		
		List<Employee> table1 = new ArrayList<Employee>();
		List<Employee> table2 = new ArrayList <Employee>();
		
		for (int i = 1; i <= t1NumRows; i++) {
			
			String firstName = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i +  "]/td[1]")).getText();
			String lastName = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i +  "]/td[2]")).getText();
			String email = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i +  "]/td[3]")).getText();
			String amountDue = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i +  "]/td[4]")).getText();
			String website = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i +  "]/td[5]")).getText();
			
			Employee e =  new Employee(firstName, lastName, email, amountDue, website);
			
			table1.add(e);
		}
		
		
		for (int i = 1; i <= t2NumRows; i++) {
			String firstName = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[" + i +  "]/td[1]")).getText();
			String lastName = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[" + i +  "]/td[2]")).getText();
			String email = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[" + i +  "]/td[3]")).getText();
			String amountDue = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[" + i +  "]/td[4]")).getText();
			String website = driver.findElement(By.xpath("//*[@id=\"table2\"]/tbody/tr[" + i +  "]/td[5]")).getText();
			
			Employee e =  new Employee(firstName, lastName, email, amountDue, website);
			
			table2.add(e);
		}
		
		
		System.out.println(table1.get(0).equals(table2.get(0))); //true (compare contents)
		System.out.println(table1.get(0).equals(table2.get(1))); //false (compare contents)
		System.out.println(table1.get(0).equals(table2.get(2))); //false (compare contents)
		System.out.println(table1.get(0).equals(null)); //false (obj is null)
		System.out.println(table1.get(0).equals(table1.get(0))); // true (obj is pointing to the same object as table1.get(0))
//		
//		System.out.println(table1.get(0).toString());
//		System.out.println(table1.get(1).toString());
//		System.out.println(table1.get(2).toString());
//		System.out.println(table1.get(3).toString());
//		
//		System.out.println(table2.get(0).toString());
//		System.out.println(table2.get(1).toString());
//		System.out.println(table2.get(2).toString());
//		System.out.println(table2.get(3).toString());
		
		
		//==============================================================================================================================================================================================
		System.out.println();
		//xpath for header names : //*[@id="table1"]/thead/tr/th[1]
		List<WebElement> table1Headers = driver.findElements(By.xpath("//*[@id=\"table1\"]/thead/tr/th"));
		int t1NumberOfHeaders = table1Headers.size();
		System.out.println("Table 1 Number of headers : " + t1NumberOfHeaders);
		
		
		List<WebElement> table2Headers = driver.findElements(By.xpath("//*[@id=\"table2\"]/thead/tr/th"));
		int t2NumberOfHeaders = table2Headers.size();
		System.out.println("Table 2 Number of headers : " + t2NumberOfHeaders);
		
		
		ArrayList<String> t1Headers = new ArrayList<String>();
		ArrayList<String> t2Headers = new ArrayList<String>();
		
		for (int i = 1; i <= t1NumberOfHeaders;  i++) {
			t1Headers.add(driver.findElement(By.xpath("//*[@id=\"table1\"]/thead/tr/th[" + i + "]")).getText());
		}
		
		for (int i = 1; i <= t2NumberOfHeaders;  i++) {
			t2Headers.add(driver.findElement(By.xpath("//*[@id=\"table2\"]/thead/tr/th[" + i + "]")).getText());
		}
		
		System.out.println(t1Headers);
		System.out.println(t2Headers);
		
		
		
		
		
		//==============================================================================================================================================================================================
		System.out.println();
		//Create table data class 
		
//		table1.get(0).setEmail("Eric4yam@gmail.com");
		
		DataTable dTable1 = new DataTable(t1NumRows, t1NumColumns, table1, t1Headers);
		DataTable dTable2 = new DataTable(t2NumRows, t2NumColumns, table2, t2Headers);


		System.out.println(dTable1.equals(dTable2));
		
		
		System.out.println(dTable1.sameHeaderOrder(t2Headers)); // true 
		System.out.println(dTable1.employeeExist(dTable2));
		

	}

}
