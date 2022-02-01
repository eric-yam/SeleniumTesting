package JAN_2022_01_31;

public class Employee {

	// Attributes
	private String firstName;
	private String lastName;
	private String email;
	private String amountDue;
	private String website;

	// Constructor
	public Employee(String fn, String ln, String e, String ad, String w) {

		this.firstName = fn;
		this.lastName = ln;
		this.email = e;
		this.amountDue = ad;
		this.website = w;

	}

	// Mutator methods
	public void setFirstName(String fn) {
		this.firstName = fn;
	}

	public void setLastName(String ln) {
		this.lastName = ln;
	}

	public void setEmail(String e) {
		this.email = e;
	}

	public void setAmountDue(String ad) {
		this.amountDue = ad;
	}

	public void setWebsite(String w) {
		this.website = w;
	}

	// Accessor methods
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getAmountDue() {
		return this.amountDue;
	}

	public String getWebsite() {
		return this.website;
	}

	@Override
	public String toString() {
		String s = "";

		s = this.firstName + " " + this.lastName + " " + this.email + " " + this.amountDue + " " + this.website;

		return s;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		} else if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		} else {

			Employee other = (Employee) obj;

			// An Employee object is content equal if their firstname, lastname, email,
			// amountDue and website match
			return this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName)
					&& this.email.equals(other.email) && this.amountDue.equals(other.amountDue)
					&& this.website.equals(other.website);

		}

	}
}
