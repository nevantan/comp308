public class OrderItem<ID, Item> {
	public ID id;
	public Item item;
	public String type;
	
	public OrderItem(ID id, Item item) {
		this.id = id;
		this.item = item;
		this.type = item.getClass().getSimpleName();
	}
}