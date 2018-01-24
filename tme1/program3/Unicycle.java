public class Unicycle extends Cycle {
	public Unicycle() {
		super();
		this.type = "Unicycle";
	}
	
	@Override
	protected int wheels() {
		return 1;
	}
}