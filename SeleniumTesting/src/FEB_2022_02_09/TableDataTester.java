package FEB_2022_02_09;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TableDataTester {
	
	public static void main(String[] args) {
		
		//Initialization
		WebDriver driver;	
		
		System.setProperty("webdriver.chrome.driver", "D:\\Eric\\Programs\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/tables");	
		
		Table t = new Table("table1", driver);
		Table t2 = new Table("table2", driver);
		
				
		System.out.println(t.getRows());
		System.out.println(t.getRow(1));
		System.out.println(t.getHeaders());
		
		System.out.println();
		
		
		System.out.println(t2.getRows());
		System.out.println(t2.getRow(1));
		System.out.println(t2.getHeaders());
		
		
		System.out.println(t.getRow(1).equals(t2.getRow(1))); //true
		System.out.println(t.getRow(1).equals(t2.getRow(2)));//false
		
		System.out.println(t.getHeadersRow());
		System.out.println(t2.getHeadersRow());
		
		
		System.out.println(t.equals(t2)); //true
		
	}

}
