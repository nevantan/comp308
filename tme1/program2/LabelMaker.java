// File: LabelMaker.java
// Author: Nevan Tan <nevan@tanclan.ca>
// StudentID: 3099925
// Date: 2018-01-12
// Entry point with some test code for the program

class LabelMaker {
	public static void main(String[] args) {
		// Create origin address
		MailingAddress from = new MailingAddress();
		
		// Setup recipient's FullName object
		FullName recipient = new FullName("Mr.", "John", "Theodore", "Doe");
		// Setup destination mailing address
		MailingAddress to = new MailingAddress(recipient, "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
		
		// Create shipping label and print it to standard output
		ShippingLabel label = new ShippingLabel(from, to);
		System.out.println(label);
	}
}