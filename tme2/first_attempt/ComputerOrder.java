public class ComputerOrder<T extends Product> extends GenericOrder<T> {
	public ComputerOrder() {
		super();
	}
	
	public ComputerOrder(T[] items) {
		super(items);
	}
}