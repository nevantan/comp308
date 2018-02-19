import java.util.ArrayList;

public class PartyTrayOrder<T extends IParty> extends GenericOrder<T> {
	public PartyTrayOrder() {}
	
	public PartyTrayOrder(ArrayList<T> products) {
		super(products);
	}
}