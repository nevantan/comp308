import java.util.ArrayList;

public class ComputerOrder<T extends IComputer> extends GenericOrder<T> {
	public ComputerOrder() {}
	
	public ComputerOrder(ArrayList<T> products) {
		super(products);
	}
}