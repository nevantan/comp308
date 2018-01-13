// File: ShippingLabelTest.java
// Author: Nevan Tan <nevan@tanclan.ca>
// StudentID: 3099925
// Date: 2018-01-12
// ShippingLabelTest is a JUnit 4 test class for ShippingLabel.java.

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShippingLabelTest {
	@Test
	public void defaultConstructor() {
		ShippingLabel label = new ShippingLabel();
		assertTrue(label instanceof ShippingLabel);
	}
	
	@Test
	public void mainConstructor() {
		MailingAddress from = new MailingAddress();
		
		FullName recipient = new FullName("Mr.", "John", "Theodore", "Doe");
		MailingAddress to = new MailingAddress(recipient, "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
		
		ShippingLabel label = new ShippingLabel(from, to);
		assertTrue(label instanceof ShippingLabel);
	}
	
	@Test
	public void labelToString() {
		MailingAddress from = new MailingAddress();
		
		FullName recipient = new FullName("Mr.", "John", "Theodore", "Doe");
		MailingAddress to = new MailingAddress(recipient, "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
		
		ShippingLabel label = new ShippingLabel(from, to);
		assertEquals("Mr. John Theodore Doe\n1 Sussex Dr.\nOttawa, Ontario  K1A 0A1\nCANADA", label.toString());
	}
}