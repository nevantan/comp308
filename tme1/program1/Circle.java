public class Circle {
	public double x, y, radius;
	
	public Circle() {
		this.x = 0;
		this.y = 0;
		this.radius = 5;
	}
	
	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public double circumference() {
		return 2.0 * Math.PI * this.radius;
	}
	
	public double area() {
		return Math.PI * Math.pow(this.radius, 2);
	}
	
	public void setRadius(double r) {
		this.radius = r;
	}
	
	public void printAttributes() {
		System.out.printf("Coords: (%.0f, %.0f)\n", this.x, this.y);
		System.out.printf("Radius: %.2f\n", this.radius);
		System.out.printf("Circumference: %.2f\n", this.circumference());
		System.out.printf("Area: %.2f\n", this.area());
	}
}