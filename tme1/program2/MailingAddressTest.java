import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MailingAddressTest {
	@Test
	public void defaultConstructor() {
		MailingAddress address = new MailingAddress();
		assertTrue(address instanceof MailingAddress);
	}
	
	@Test
	public void mainConstructor() {
		FullName name = new FullName("Mr.", "John", "Theodore", "Doe");
		MailingAddress address = new MailingAddress(name, "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
		assertTrue(address instanceof MailingAddress);
	}
	
	@Test
	public void addressToString() {
		FullName name = new FullName("Mr.", "John", "Theodore", "Doe");
		MailingAddress address = new MailingAddress(name, "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
		assertEquals("Mr. John Theodore Doe\n1 Sussex Dr.\nOttawa, Ontario  K1A 0A1\nCANADA", address.toString());
	}
}