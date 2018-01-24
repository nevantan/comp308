public abstract class Cycle {
	protected String type;
	
	public Cycle() {
		this.type = "Cycle";
	}
	
	protected void ride() {
		System.out.println("Riding a " + this.type + " which has " + this.wheels() + " wheel(s).");
	}
	
	protected abstract int wheels();
}