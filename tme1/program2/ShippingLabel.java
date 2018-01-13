// File: ShippingLabel.java
// Author: Nevan Tan <nevan@tanclan.ca>
// StudentID: 3099925
// Date: 2018-01-12
// Utility class to store a shipping label with 'from' and 'to' addresses

public class ShippingLabel {
	// from - The origin address for the label
	// to - The destination address for the label
	private MailingAddress from, to;
	
	// Default constructor
	public ShippingLabel() {
		this.from = new MailingAddress();
		this.to = new MailingAddress();
	}
	
	// Main constructor for setting the values immediately
	public ShippingLabel(MailingAddress from, MailingAddress to) {
		this.from = from;
		this.to = to;
	}
	
	// Return the value of the destination address in mailing label format
	public String toString() {
		return this.to.toString();
	}
}