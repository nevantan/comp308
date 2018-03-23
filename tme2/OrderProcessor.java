/*
 * File: OrderProcessor.java
 * Author: Nevan Tan <nevan@tanclan.ca>
 * StudentID: 3099925
 * Date: 2018-02-19
 * OrderProcessor accepts GenericOrder (and subclass) objects and collects them before
 * processing - which sorts the orders into ComputerParts, Peripherals, Services, Cheeses,
 * and Fruits.
 * 
 * Requirements:
 * - Implement an 'accept' method that accepts GenericOrder or any subclass
 * - Implement a 'process' method that sorts GenericOrder objects into the subclasses of Product
 * - Implement 'dispatchXXX' method for each subclass of Product that prints the contents
 * 
 * Compile:
 * 	javac OrderProcessor.java
 * 
 * Run:
 * 	N/A (No entry point)
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.Iterator;

public class OrderProcessor {
	// Internal collection of orders
	protected ArrayList<GenericOrder> orders;
	
	// Processed orders are sorted into these collections
	protected ArrayList<Tuple<UUID, ComputerPart>> parts;
	protected ArrayList<Tuple<UUID, Peripheral>> peripherals;
	protected ArrayList<Tuple<UUID, Service>> services;
	protected ArrayList<Tuple<UUID, Cheese>> cheeses;
	protected ArrayList<Tuple<UUID, Fruit>> fruits;
	
	// Default constructor to initialize all collections
	public OrderProcessor() {
		this.orders = new ArrayList<>();
		
		this.parts = new ArrayList<>();
		this.peripherals = new ArrayList<>();
		this.services = new ArrayList<>();
		this.cheeses = new ArrayList<>();
		this.fruits = new ArrayList<>();
	}
	
	// Adds an order to the internal collection
	public void accept(GenericOrder order) {
		this.orders.add(order);
	}
	
	// Process all stored orders
	public void process() {
		// Loop through internal orders
		for(GenericOrder order : this.orders) {
			// Get products from the current order
			ArrayList<Product> products = order.getProducts();
			
			// Loop through product array
			for(Product product : products) {
				// Sort based off class name and add to proper collection
				switch(product.getClass().getSuperclass().getSimpleName()) {
					case "ComputerPart":
					case "Storage":
						parts.add(new Tuple(order.getID(), (ComputerPart)product));
						break;
					case "Peripheral":
						peripherals.add(new Tuple(order.getID(), (Peripheral)product));
						break;
					case "Service":
						services.add(new Tuple(order.getID(), (Service)product));
						break;
					case "Cheese":
						cheeses.add(new Tuple(order.getID(), (Cheese)product));
						break;
					case "Fruit":
						fruits.add(new Tuple(order.getID(), (Fruit)product));
						break;
				}
			}
		}
	}
	
	// Loop through provided collection of products and print to screen
	protected void dispatch(ArrayList<Product> products) {
		for(Product product : products) {
			System.out.println(product);
		}
	}
	
	// Constructs a list of ComputerPart to send to dispatch()
	public void dispatchComputerParts() {
		ArrayList<Product> products = new ArrayList<>();
		
		// Loop through stored Tuples
		for(Tuple<UUID, ComputerPart> tuple : this.parts) {
			// Set the order ID of the product (so toString works properly)
			tuple.y.setID(tuple.x);
			
			// Add to products collection to be sent to dispatch()
			products.add(tuple.y);
		}
		
		// Print computer parts list
		dispatch(products);
	}
	
	// Constructs a list of Peripheral to send to dispatch()
	public void dispatchPeripherals() {
		ArrayList<Product> products = new ArrayList<>();
		for(Tuple<UUID, Peripheral> tuple : this.peripherals) {
			tuple.y.setID(tuple.x);
			products.add(tuple.y);
		}
		dispatch(products);
	}
	
	// Constructs a list of Service to send to dispatch()
	public void dispatchServices() {
		ArrayList<Product> products = new ArrayList<>();
		for(Tuple<UUID, Service> tuple : this.services) {
			tuple.y.setID(tuple.x);
			products.add(tuple.y);
		}
		dispatch(products);
	}
	
	// Constructs a list of Cheese to send to dispatch()
	public void dispatchCheeses() {
		ArrayList<Product> products = new ArrayList<>();
		for(Tuple<UUID, Cheese> tuple : this.cheeses) {
			tuple.y.setID(tuple.x);
			products.add(tuple.y);
		}
		dispatch(products);
	}
	
	// Constructs a list of Fruit to send to dispatch()
	public void dispatchFruits() {
		ArrayList<Product> products = new ArrayList<>();
		for(Tuple<UUID, Fruit> tuple : this.fruits) {
			tuple.y.setID(tuple.x);
			products.add(tuple.y);
		}
		dispatch(products);
	}
}