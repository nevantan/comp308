// File: FullNameTest.java
// Author: Nevan Tan <nevan@tanclan.ca>
// StudentID: 3099925
// Date: 2018-01-12
// FullNameTest is a unit test class for FullName.java.

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FullNameTest {
	@Test
	public void defaultConstructor() {
		FullName name = new FullName();
		assertEquals("", name.title);
		assertEquals("", name.first);
		assertEquals("", name.middle);
		assertEquals("", name.last);
	}
	
	@Test
	public void mainConstructor() {
		FullName name = new FullName("Mr.", "John", "Theodore", "Doe");
		assertEquals("Mr.", name.title);
		assertEquals("John", name.first);
		assertEquals("Theodore", name.middle);
		assertEquals("Doe", name.last);
	}
	
	@Test
	public void fullToString() {
		FullName name = new FullName("Mr.", "John", "Theodore", "Doe");
		assertEquals("Mr. John Theodore Doe", name.toString());
	}
	
	@Test
	public void noTitleToString() {
		FullName name = new FullName("", "John", "Theodore", "Doe");
		assertEquals("John Theodore Doe", name.toString());
	}
	
	@Test
	public void noMiddleToString() {
		FullName name = new FullName("Mr.", "John", "", "Doe");
		assertEquals("Mr. John Doe", name.toString());
	}
}