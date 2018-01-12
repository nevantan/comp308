import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MailingAddressTest {
	@Test
	public void defaultConstructor() {
		MailingAddress address = new MailingAddress();
		assertEquals("", address.name.toString());
		assertEquals("", address.street);
		assertEquals("", address.city);
		assertEquals("", address.province);
		assertEquals("", address.postal);
	}
	
	@Test
	public void mainConstructor() {
		FullName name = new FullName("Mr.", "John", "Theodore", "Doe");
		MailingAddress address = new MailingAddress(name, "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
		assertEquals("Mr. John Theodore Doe", address.name.toString());
		assertEquals("1 Sussex Dr.", address.street);
		assertEquals("Ottawa", address.city);
		assertEquals("Ontario", address.province);
		assertEquals("K1A 0A1", address.postal);
	}
	
	@Test
	public void addressToString() {
		FullName name = new FullName("Mr.", "John", "Theodore", "Doe");
		MailingAddress address = new MailingAddress(name, "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
		assertEquals("Mr. John Theodore Doe\n1 Sussex Dr.\nOttawa, Ontario  K1A 0A1\nCANADA", address.toString());
	}
}