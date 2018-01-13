// File: MailingAddress.java
// Author: Nevan Tan <nevan@tanclan.ca>
// StudentID: 3099925
// Date: 2018-01-12
// Utility class to store a mailing address including the recipient's fullname,
// their street address, city, province, and postal code.

public class MailingAddress {
	// name - The full name of the recipient
	// street - The street address of the recipient
	// city - The city for the recipient's mailing address
	// province - The province for the recipient's mailing address
	// postal - The postal code for the recipient's mailing address
	private FullName name;
	private String street, city, province, postal;
	
	// Default constructor
	public MailingAddress() {
		this.name = new FullName();
		this.street = "";
		this.city = "";
		this.province = "";
		this.postal = "";
	}
	
	// Main constructor to set the values of all the fields
	public MailingAddress(FullName name, String street, String city, String province, String postal) {
		this.name = name;
		this.street = street;
		this.city = city;
		this.province = province;
		this.postal = postal;
	}
	
	// Returns the formatted mailing address as a multi-line string
	public String toString() {
		String full = "";
		full += this.name + "\n";
		full += this.street + "\n";
		full += this.city + ", " + this.province + "  " + this.postal + "\n";
		full += "CANADA";
		return full;
	}
}