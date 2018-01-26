/* 
 * File: Circle.java
 * Author: Nevan Tan <nevan@tanclan.ca>
 * StudentID: 3099925
 * Date: 2018-01-12
 * Circle is a utility class that stores the circle's coordinates and radius
 * along with various methods to calculate values and modify stored values.
 * 
 * Problem/Requirements:
 * 	Write a class called Circle which has the following methods:
 * 	- public Circle() - supplies default values for coordinates and radius
 * 	- public Circle(double x, double y, double radius)
 * 	- public double circumference() - returns the circumference of the circle
 * 	- public double area() - returns the area of the circle
 * 	- public void setRadius(double r) - sets the radius to min(MAX_RADIUS, r)
 * 	- public void printAttributes() - prints coords, radius, circumference, and area
 * 	- public boolean isInside(double x, double y) - returns true if provided point is within the circle
 * 	- public void move(int x, int y) - moves the origin by the specified amount
 * 
 * Compile:
 * 	javac Circle.java
 * 
 * Run:
 * 	N/A (No entry point)
 */ 

public class Circle {
	// x - The x-coordinate of the center point of the circle
	// y - The y-coordinate of the center point of the circle
	// radius - The radius of the circle
	// MAX_RADIUS - The maximum radious allowed for the circle
	private double x, y, radius;
	private double MAX_RADIUS = 50;
	
	// Default constructor, sets reasonable defaults
	public Circle() {
		this.x = 0;
		this.y = 0;
		this.setRadius(5);
	}
	
	// Secondary constructor to set values on initialization
	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.setRadius(radius);
	}
	
	// Returns the circumference of the circle
	public double circumference() {
		return 2.0 * Math.PI * this.radius;
	}
	
	// Returns the area of the circle
	public double area() {
		return Math.PI * Math.pow(this.radius, 2);
	}
	
	// Sets the radius of the circle to the provided value, limited to MAX_RADIUS
	public void setRadius(double r) {
		this.radius = r > this.MAX_RADIUS ? this.MAX_RADIUS : r;
	}
	
	// Prints the attributes of the circle to the standard output
	public void printAttributes() {
		System.out.printf("Coords: (%.0f, %.0f)\n", this.x, this.y);
		System.out.printf("Radius: %.2f\n", this.radius);
		System.out.printf("Circumference: %.2f\n", this.circumference());
		System.out.printf("Area: %.2f\n", this.area());
	}
	
	// Determines whether a given point is within the area of the circle
	public boolean isInside(double x, double y) {
		// Math.hypot is essentially the distance equation - given a and b, it returns c
		// A point is within a circle is its distance from the center of the circle is
		// less than the radius.
		return Math.hypot(this.x - x, this.y - y) <= this.radius;
	}
	
	// Moves the center of the circle the given distance in both x and y directions
	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}
}