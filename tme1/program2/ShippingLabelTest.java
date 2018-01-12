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