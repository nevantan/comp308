// File: Circle.java
// Author: Nevan Tan <nevan@tanclan.ca>
// StudentID: 3099925
// Date: 2018-01-12
// Circle is a utility class that stores the circle's coordinates and radius
// along with various methods to calculate values and modify stored values.

public class Circle {
	// x - The x-coordinate of the center point of the circle
	// y - The y-coordinate of the center point of the circle
	// radius - The radius of the circle
	private double x, y, radius;
	
	// Default constructor, sets reasonable defaults
	public Circle() {
		this.x = 0;
		this.y = 0;
		this.radius = 5;
	}
	
	// Secondary constructor to set values on initialization
	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	// Returns the circumference of the circle
	public double circumference() {
		return 2.0 * Math.PI * this.radius;
	}
	
	// Returns the area of the circle
	public double area() {
		return Math.PI * Math.pow(this.radius, 2);
	}
	
	// Sets the radius of the circle to the provided value
	public void setRadius(double r) {
		this.radius = r;
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