public class ShippingLabel {
	private MailingAddress from, to;
	
	public ShippingLabel() {
		this.from = new MailingAddress();
		this.to = new MailingAddress();
	}
	
	public ShippingLabel(MailingAddress from, MailingAddress to) {
		this.from = from;
		this.to = to;
	}
	
	public String toString() {
		return this.to.toString();
	}
}