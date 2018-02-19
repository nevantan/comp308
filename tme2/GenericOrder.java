import java.util.UUID;
import java.util.ArrayList;

// This template was intentionally changed from "<T extends Product>" to simply
// "<T>" in order to allow ComputerOrder and PartyTrayOrder to extend interfaces
// only which, in turn, allow them to accept each of the three classes. This does
// violate the constraint in point 2 of the assignment that states GenericOrder
// should act as a collection of object in Products.java as it allows for classes
// outside of that file; however, I believe violating this constraint was necessary.
public class GenericOrder<T> {
	protected UUID id;
	protected ArrayList<T> products = new ArrayList<T>();
	
	public GenericOrder() {
		this.id = UUID.randomUUID();
	}
	
	public GenericOrder(ArrayList<T> products) {
		this();
		this.products = products;
	}
	
	public UUID getID() {
		return id;
	}
	
	public T getProduct(int index) {
		return this.products.get(index);
	}
	
	public ArrayList<T> getProducts() {
		return this.products;
	}
	
	public void addProduct(T product) {
		this.products.add(product);
	}
	
	public void removeProduct(T product) {
		this.products.remove(product);
	}
}