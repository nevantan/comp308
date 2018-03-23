/*
 * File: OrderClient.java
 * Author: Nevan Tan <nevan@tanclan.ca>
 * StudentID: 3099925
 * Date: 2018-02-19
 * OrderClient is the main entrypoint of the program, setting up test data and running
 * it through the various order and processor classes.
 * 
 * Requirements:
 * - Create a client class to test OrderProcessor
 * 
 * Compile:
 * 	javac OrderClient.java
 * 
 * Run:
 * 	java OrderClient
 */

import java.util.ArrayList;

public class OrderClient {
	public static void main(String[] argv) {
		// Setup an empty ComputerOrder
		ComputerOrder<IComputer> order = new ComputerOrder<IComputer>();
		
		// Add the four subclasses of ComputerParts with arbitrary test data
		order.addProduct(new Motherboard("Asus", 500));
		order.addProduct(new Motherboard("Gigabyte", 500));
		order.addProduct(new RAM("Corasir", 4096, 120));
		order.addProduct(new Drive("Seagate", DriveType.SSD, 256, 5400, 120));
		
		// Add the two subclasses of Peripheral with arbitrary test data
		order.addProduct(new Printer("HP 5500", 600));
		order.addProduct(new Monitor("Dell P2411H", 500));
		
		// Add the two subclasses of Service with arbitrary test data
		order.addProduct(new AssemblyService("Best Buy", 75));
		order.addProduct(new DeliveryService("FedEx", 20));
		
		// Setup an empty PartyTrayOrder
		PartyTrayOrder<IParty> order2 = new PartyTrayOrder<IParty>();
		
		// Add the two subclasses of Service with arbitrary test data
		order2.addProduct(new AssemblyService("Best Buy", 75));
		order2.addProduct(new DeliveryService("FedEx", 20));

		// Add the two subclasses of Cheese with arbitrary test data
		order2.addProduct(new Cheddar(5));
		order2.addProduct(new Mozzarella(4));
		
		// Add the two subclasses of Fruit with arbitrary test data
		order2.addProduct(new Apple(1));
		order2.addProduct(new Orange(2));
		
		// Create a new order processor
		OrderProcessor processor = new OrderProcessor();
		
		// Accept both order setup above
		processor.accept(order);
		processor.accept(order2);
		
		// Process the orders
		processor.process();
		
		// Call each dispatch method
		processor.dispatchComputerParts();
		processor.dispatchPeripherals();
		processor.dispatchServices();
		processor.dispatchCheeses();
		processor.dispatchFruits();
	}
}