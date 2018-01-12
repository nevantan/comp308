class LabelMaker {
	public static void main(String[] args) {
		MailingAddress from = new MailingAddress();
		
		FullName recipient = new FullName("Mr.", "John", "Theodore", "Doe");
		MailingAddress to = new MailingAddress(recipient, "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
		
		ShippingLabel label = new ShippingLabel(from, to);
		System.out.println(label);
	}
}