// File: CircleTest.java
// Author: Nevan Tan <nevan@tanclan.ca>
// StudentID: 3099925
// Date: 2018-01-12
// CircleTest is a JUnit 4 test class for Circle.java. It includes unit test
// methods for each method defined in Circle.
// 
// Compile:
//  javac -cp .:junit-4.12.jar CircleTest.java
// 
// Run:
//  java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore CircleTest

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CircleTest {
	@Test
	public void defaultConstructor() {
		Circle circle = new Circle();
		assertTrue(circle instanceof Circle);
	}
	
	@Test
	public void mainConstructor() {
		Circle circle = new Circle(10, 10, 15);
		assertTrue(circle instanceof Circle);
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
	public void correctlySetRadius() throws NoSuchFieldException, IllegalAccessException {
		Circle circle = new Circle(0, 0, 5);
		circle.setRadius(10);
		
		final Field field = circle.getClass().getDeclaredField("radius");
		field.setAccessible(true);
		assertEquals(10, field.getDouble(circle), 0);
	}
	
	@Test
	public void correctlyLimitRadius() throws NoSuchFieldException, IllegalAccessException {
		Circle circle = new Circle(0, 0, 5);
		circle.setRadius(55);
		
		final Field field = circle.getClass().getDeclaredField("radius");
		field.setAccessible(true);
		assertEquals(50, field.getDouble(circle), 0);
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
	public void testMove() throws NoSuchFieldException, IllegalAccessException {
		Circle circle = new Circle(0, 0, 5);
		circle.move(10, 10);
		
		final Field fieldX = circle.getClass().getDeclaredField("x");
		fieldX.setAccessible(true);
		final Field fieldY = circle.getClass().getDeclaredField("y");
		fieldY.setAccessible(true);
		
		assertEquals(10, fieldX.getDouble(circle), 0);
		assertEquals(10, fieldY.getDouble(circle), 0);
		
		circle.move(-2, -2);
		assertEquals(8, fieldX.getDouble(circle), 0);
		assertEquals(8, fieldY.getDouble(circle), 0);
	}
}