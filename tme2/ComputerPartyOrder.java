/*
 * File: ComputerPartyOrder.java
 * Author: Nevan Tan <nevan@tanclan.ca>
 * StudentID: 3099925
 * Date: 2018-02-19
 * ComputerOrder is a generic container for storing specifically implementations
 * of the IComputer interface - ComputerPart, Peripheral, Service
 * 
 * Requirements:
 * - Subclass of GenericOrder that takes an arbitraty number of different objects
 * - Object classes limited to ComputerPart, Peripheral, Service
 * 
 * Compile:
 *  javac ComputerOrder.java
 * 
 * Run:
 *  N/A (No entry point)
 */

import java.util.ArrayList;

// Generic classes are bounded to classes that implement the IComputer interface
public class ComputerPartyOrder<T extends IComputerParty> extends GenericOrder<T> {
  public ComputerPartyOrder() {}
  
  public ComputerPartyOrder(ArrayList<T> products) {
    super(products);
  }
}