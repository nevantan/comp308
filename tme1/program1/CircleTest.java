import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CircleTest {
	@Test
	public void defaultConstructor() {
		Circle circle = new Circle();
		assertEquals(0, circle.x, 0);
		assertEquals(0, circle.y, 0);
		assertEquals(5, circle.radius, 0);
	}
	
	@Test
	public void mainConstructor() {
		Circle circle = new Circle(10, 10, 15);
		assertEquals(10, circle.x, 0);
		assertEquals(10, circle.y, 0);
		assertEquals(15, circle.radius, 0);
	}
	
	@Test
	public void correctCircumference() {
		Circle circle = new Circle(0, 0, 5);
		assertEquals(31.42, circle.circumference(), 0.01);
	}
	
	@Test
	public void correctArea() {
		Circle circle = new Circle(0, 0, 5);
		assertEquals(78.54, circle.area(), 0.01);
	}
	
	@Test
	public void correctlySetRadius() {
		Circle circle = new Circle(0, 0, 5);
		circle.setRadius(10);
		assertEquals(10, circle.radius, 0);
	}
	
	@Test
	public void correctlyPrintAttributes() {
		ByteArrayOutputStream sysOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sysOut));
		
		Circle circle = new Circle(0, 0, 5);
		circle.printAttributes();
		assertEquals("Coords: (0, 0)\nRadius: 5.00\nCircumference: 31.42\nArea: 78.54\n", sysOut.toString());
	}
	
	@Test
	public void testIsInside() {
		Circle circle = new Circle(0, 0, 5);
		assertEquals(true, circle.isInside(2, 2));
	}
	
	@Test
	public void testIsNotInside() {
		Circle circle = new Circle(0, 0, 5);
		assertEquals(false, circle.isInside(10, 10));
	}
	
	@Test
	public void testMove() {
		Circle circle = new Circle(0, 0, 5);
		circle.move(10, 10);
		assertEquals(10, circle.x, 0);
		assertEquals(10, circle.y, 0);
		
		circle.move(-2, -2);
		assertEquals(8, circle.x, 0);
		assertEquals(8, circle.y, 0);
	}
}