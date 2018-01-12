import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCircle {
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
}