import java.util.ArrayList;
import java.util.UUID;

public class OrderProcessor {
	private ArrayList<GenericOrder> orders;
	
	private ArrayList<OrderItem<UUID, ComputerPart>> parts;
	private ArrayList<OrderItem<UUID, Peripheral>> peripherals;
	private ArrayList<OrderItem<UUID, Service>> services;
	private ArrayList<OrderItem<UUID, Cheese>> cheeses;
	private ArrayList<OrderItem<UUID, Fruit>> fruits;
	
	public void accept(GenericOrder order) {
		this.orders.add(order);
	}
	
	public void process() {
		for(int i = 0; i < orders.size(); i++) {
			ArrayList<OrderItem> items = orders.get(i).process();
			
			for(int j = 0; j < items.size(); j++) {
				OrderItem item = items.get(j);
				switch(item.type) {
					case "ComputerPart":
						parts.add(item);
						break;
					case "Peripheral":
						peripherals.add(item);
						break;
					case "Service":
						services.add(item);
						break;
					case "Cheese":
						cheeses.add(item);
						break;
					case "Fruit":
						fruits.add(item);
						break;
				}
			}
		}
	}
	
	public void dispatchComputerParts() {
		for(int i = 0; i < parts.size(); i++) {
			OrderItem<UUID, ComputerPart> part = parts.get(i);

			if(part.item instanceof Motherboard) {
				System.out.println("Motherboard name=" + part.item.getManufacturer() + ", price=" + part.item.getPrice() + ", order id=" + part.id);
			}
		}
	}
	
	public void dispatchPeripherals() {
		
	}
	
	public void dispatchServices() {
		
	}
	
	public void dispatchCheese() {
		
	}
	
	public void dispatchFruit() {
		
	}
}