import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.Iterator;

public class OrderProcessor {
	protected ArrayList<GenericOrder> orders;
	
	protected ArrayList<Tuple<UUID, ComputerPart>> parts;
	protected ArrayList<Tuple<UUID, Peripheral>> peripherals;
	protected ArrayList<Tuple<UUID, Service>> services;
	protected ArrayList<Tuple<UUID, Cheese>> cheeses;
	protected ArrayList<Tuple<UUID, Fruit>> fruits;
	
	public OrderProcessor() {
		this.orders = new ArrayList<>();
		
		this.parts = new ArrayList<>();
		this.peripherals = new ArrayList<>();
		this.services = new ArrayList<>();
		this.cheeses = new ArrayList<>();
		this.fruits = new ArrayList<>();
	}
	
	public void accept(GenericOrder order) {
		this.orders.add(order);
	}
	
	public void process() {
		for(GenericOrder order : this.orders) {
			ArrayList<Product> products = order.getProducts();
			for(Product product : products) {
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
	
	protected void dispatch(ArrayList<Product> products) {
		for(Product product : products) {
			System.out.println(product);
		}
	}
	
	public void dispatchComputerParts() {
		ArrayList<Product> products = new ArrayList<>();
		for(Tuple<UUID, ComputerPart> tuple : this.parts) {
			tuple.y.setID(tuple.x);
			products.add(tuple.y);
		}
		dispatch(products);
	}
	
	public void dispatchPeripherals() {
		ArrayList<Product> products = new ArrayList<>();
		for(Tuple<UUID, Peripheral> tuple : this.peripherals) {
			tuple.y.setID(tuple.x);
			products.add(tuple.y);
		}
		dispatch(products);
	}
	
	public void dispatchServices() {
		ArrayList<Product> products = new ArrayList<>();
		for(Tuple<UUID, Service> tuple : this.services) {
			tuple.y.setID(tuple.x);
			products.add(tuple.y);
		}
		dispatch(products);
	}
	
	public void dispatchCheeses() {
		ArrayList<Product> products = new ArrayList<>();
		for(Tuple<UUID, Cheese> tuple : this.cheeses) {
			tuple.y.setID(tuple.x);
			products.add(tuple.y);
		}
		dispatch(products);
	}
	
	public void dispatchFruits() {
		ArrayList<Product> products = new ArrayList<>();
		for(Tuple<UUID, Fruit> tuple : this.fruits) {
			tuple.y.setID(tuple.x);
			products.add(tuple.y);
		}
		dispatch(products);
	}
}