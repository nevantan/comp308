/*
 * File: Tuple.java
 * Author: Nevan Tan <nevan@tanclan.ca>
 * StudentID: 3099925
 * Date: 2018-02-19
 * Basic Tuple implementation, takes two objects of arbitrary types and stores them as x and y
 * 
 * Compile:
 * 	javac Tuple.java
 * 
 * Run:
 * 	N/A (No entry point)
 */

public class Tuple<X, Y> {
	public X x;
	public Y y;
	public Tuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}
}