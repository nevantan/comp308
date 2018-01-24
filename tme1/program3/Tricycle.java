public class Tricycle extends Cycle {
	public Tricycle() {
		super();
		this.type = "Tricycle";
	}
	
	@Override
	protected int wheels() {
		return 3;
	}
}