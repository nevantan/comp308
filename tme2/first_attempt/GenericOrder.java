import java.util.UUID;
import java.util.Arrays;
import java.util.ArrayList;

public abstract class GenericOrder<T> {
	private UUID uid;
	private ArrayList<T> items;
	
	public GenericOrder() {
		this.uid = UUID.randomUUID();
		this.items = new ArrayList<>();
		
		this.print();
	}
	
	public GenericOrder(T[] items) {
		this.uid = UUID.randomUUID();
		this.items = new ArrayList<>(Arrays.asList(items));
		
		this.print();
	}
	
	public void add(T item) {
		this.items.add(item);
	}
	
	public void print() {
		System.out.println(this.items);
	}
	
	public ArrayList<OrderItem<UUID, T>> process() {
		ArrayList<OrderItem<UUID, T>> order_items = new ArrayList<OrderItem<UUID, T>>();
		
		for(int i = 0; i < items.size(); i++) {
			order_items.add(new OrderItem<UUID, T>(this.uid, this.items.get(i)));
		}
		
		return order_items;
	}
}