// File: FullName.java
// Author: Nevan Tan <nevan@tanclan.ca>
// StudentID: 3099925
// Date: 2018-01-12
// Utility class to store a full name including title, first
// middle, and last names. Provides a method to retreive a
// properly formatted name.

public class FullName {
	private String title, first, middle, last;
	
	public FullName() {
		this.title = "";
		this.first = "";
		this.middle = "";
		this.last = "";
	}
	
	public FullName(String title, String first, String middle, String last) {
		this.title = title;
		this.first = first;
		this.middle = middle;
		this.last = last;
	}
	
	public String toString() {
		String full = "";
		if(this.title.length() > 0) full += this.title;
		if(this.first.length() > 0) full += " " + this.first;
		if(this.middle.length() > 0) full += " " + this.middle;
		if(this.last.length() > 0) full += " " + this.last;
		return full.trim();
	}
}