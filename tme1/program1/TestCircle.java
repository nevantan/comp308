import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCircle {
	@Test
	public void defaultConstructor() {
		Circle circle = new Circle();
		assertEquals(0.0, circle.x);
		assertEquals(0.0, circle.y);
		assertEquals(5.0, circle.radius);
	}
}