public class Bicycle extends Cycle {
	public Bicycle() {
		super();
		this.type = "Bicycle";
	}
	
	@Override
	protected int wheels() {
		return 2;
	}
}