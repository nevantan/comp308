/*
 * File: GenericOrder.java
 * Author: Nevan Tan <nevan@tanclan.ca>
 * StudentID: 3099925
 * Date: 2018-02-19
 * GenericOrder is a generic container for storing any class of object
 * 
 * Requirements:
 * - Accepts an arbitrary number of objects in Products.java
 * - Gives each instance a unique identifier
 * 
 * Compile:
 * 	javac GenericOrder.java
 * 
 * Run:
 * 	N/A (No entry point)
 */

import java.util.UUID;
import java.util.ArrayList;

// This template was intentionally changed from "<T extends Product>" to simply
// "<T>" in order to allow ComputerOrder and PartyTrayOrder to extend interfaces
// only which, in turn, allow them to accept each of the three classes. This does
// violate the constraint in point 2 of the assignment that states GenericOrder
// should act as a collection of object in Products.java as it allows for classes
// outside of that file; however, I believe violating this constraint was necessary.
public class GenericOrder<T> {
	// id - Universally Unique Identifier
	// products - Internal storage of products that are part of this order
	protected UUID id;
	protected ArrayList<T> products = new ArrayList<T>();
	
	// Default constructor sets UUID
	public GenericOrder() {
		this.id = UUID.randomUUID();
	}
	
	// Calls default constructor to set UUID, adds initial products
	public GenericOrder(ArrayList<T> products) {
		this();
		this.products = products;
	}
	
	// Getter for the ID
	public UUID getID() {
		return id;
	}
	
	// Getter for single product, given index
	public T getProduct(int index) {
		return this.products.get(index);
	}
	
	// Getter for products ArrayList (to be iterated outside this class)
	public ArrayList<T> getProducts() {
		return this.products;
	}
	
	// Add a single product
	public void addProduct(T product) {
		this.products.add(product);
	}
	
	// Remove a single product
	public void removeProduct(T product) {
		this.products.remove(product);
	}
}