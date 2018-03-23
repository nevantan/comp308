/*
 * File: PartyTrayOrder.java
 * Author: Nevan Tan <nevan@tanclan.ca>
 * StudentID: 3099925
 * Date: 2018-02-19
 * PartyTrayOrder is a generic container for storing specifically implementations
 * of the IParty interface - Service, Cheese, and Fruit
 * 
 * Requirements:
 * - Subclass of GenericOrder that takes an arbitraty number of different objects
 * - Object classes limited to Service, Cheese, and Fruit
 * 
 * Compile:
 * 	javac PartyTrayOrder.java
 * 
 * Run:
 * 	N/A (No entry point)
 */

import java.util.ArrayList;

// Generic classes are bounded to classes that implement the IParty interface
public class PartyTrayOrder<T extends IParty> extends GenericOrder<T> {
	public PartyTrayOrder() {}
	
	public PartyTrayOrder(ArrayList<T> products) {
		super(products);
	}
}