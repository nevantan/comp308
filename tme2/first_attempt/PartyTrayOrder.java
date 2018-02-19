public class PartyTrayOrder<T extends Product> extends GenericOrder<T> {
	public PartyTrayOrder() {
		super();
	}
	
	public PartyTrayOrder(T[] items) {
		super(items);
	}
}