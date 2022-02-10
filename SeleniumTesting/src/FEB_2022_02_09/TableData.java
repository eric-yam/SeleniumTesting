package FEB_2022_02_09;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableData {

	// Attributes
	private String lastName;
	private String firstName;
	private String email;
	private String due;
	private String website;

	
	//Constructor
	public TableData() {}
	
	public TableData(WebElement tableRow) {
		
		List<WebElement> allTDs = tableRow.findElements(By.tagName("td"));
		
		this.lastName = allTDs.get(0).getText();
		this.firstName = allTDs.get(1).getText();
		this.email = allTDs.get(2).getText();
		this.due= allTDs.get(3).getText();
		this.website = allTDs.get(4).getText();
				
	}
	
	// Accessor Methods
	public String getLastName() {
		return lastName;
	}

	public TableData withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public TableData withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public TableData withEmail(String email) {
		this.email = email;
		return this;
	}

	public String getDue() {
		return due;
	}

	public TableData withDue(String due) {
		this.due = due;
		return this;
	}

	public String getWebsite() {
		return website;
	}

	public TableData withWebsite(String website) {
		this.website = website;
		return this;
	}

	@Override
	public String toString() {

		return this.getLastName() + ", " + this.getFirstName() + ", " + this.getEmail() + ", " + this.getDue() + ", "
				+ this.getWebsite();

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		} else {

			TableData other = (TableData) obj;

			return this.getLastName().equals(other.lastName) && this.getFirstName().equals(other.getFirstName())
					&& this.getEmail().equals(other.getEmail()) && this.getDue().equals(other.getDue())
					&& this.getWebsite().equals(other.getWebsite());

		}

	}
}
