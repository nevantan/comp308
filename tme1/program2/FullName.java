// File: FullName.java
// Author: Nevan Tan <nevan@tanclan.ca>
// StudentID: 3099925
// Date: 2018-01-12
// [Description]

public class FullName {
	public String title, first, middle, last;
	
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
}