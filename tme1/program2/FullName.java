// File: FullName.java
// Author: Nevan Tan <nevan@tanclan.ca>
// StudentID: 3099925
// Date: 2018-01-12
// Utility class to store a full name including title, first
// middle, and last names. Provides a method to retreive a
// properly formatted name.

public class FullName {
	// title - Title to address the individual by (e.g. Mr., Mrs., Ms., etc.)
	// first - The individual's first name
	// middle - The individual's middle name
	// last - The individual's last name
	private String title, first, middle, last;
	
	// Default contructor
	public FullName() {
		this.title = "";
		this.first = "";
		this.middle = "";
		this.last = "";
	}
	
	// Primary constructor to populate all fields
	public FullName(String title, String first, String middle, String last) {
		this.title = title;
		this.first = first;
		this.middle = middle;
		this.last = last;
	}
	
	// Override toString method - returns the full name as a formatted string
	public String toString() {
		String full = "";
		if(this.title.length() > 0) full += this.title;
		if(this.first.length() > 0) full += " " + this.first;
		if(this.middle.length() > 0) full += " " + this.middle;
		if(this.last.length() > 0) full += " " + this.last;
		return full.trim();
	}
}