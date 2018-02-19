public class OrderClient {
	public static void main(String[] argv) {
		OrderProcessor processor = new OrderProcessor();
		
		Motherboard[] motherboards = {new Motherboard("Asus", 500)};
		ComputerOrder<Motherboard> order = new ComputerOrder<Motherboard>(motherboards);
		
	}
}