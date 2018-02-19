import java.util.ArrayList;

public class OrderClient {
	public static void main(String[] argv) {
		ComputerOrder<IComputer> order = new ComputerOrder<IComputer>();
		order.addProduct(new Motherboard("Asus", 500));
		order.addProduct(new Motherboard("Gigabyte", 500));
		order.addProduct(new RAM("Corasir", 4096, 120));
		order.addProduct(new Drive("Seagate", DriveType.SSD, 256, 5400, 120));
		
		order.addProduct(new Printer("HP 5500", 600));
		order.addProduct(new Monitor("Dell P2411H", 500));
		
		order.addProduct(new AssemblyService("Best Buy", 75));
		
		PartyTrayOrder<IParty> order2 = new PartyTrayOrder<IParty>();
		order2.addProduct(new DeliveryService("FedEx", 20));
		
		order2.addProduct(new Cheddar(5));
		order2.addProduct(new Mozzarella(4));
		
		order2.addProduct(new Apple(1));
		order2.addProduct(new Orange(2));
		
		OrderProcessor processor = new OrderProcessor();
		processor.accept(order);
		processor.accept(order2);
		
		processor.process();
		
		processor.dispatchComputerParts();
		processor.dispatchPeripherals();
		processor.dispatchServices();
		processor.dispatchCheeses();
		processor.dispatchFruits();
	}
}