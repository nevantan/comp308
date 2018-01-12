public class MailingAddress {
	public FullName name;
	public String street, city, province, postal;
	
	public MailingAddress() {
		this.name = new FullName();
		this.street = "";
		this.city = "";
		this.province = "";
		this.postal = "";
	}
	
	public MailingAddress(FullName name, String street, String city, String province, String postal) {
		this.name = name;
		this.street = street;
		this.city = city;
		this.province = province;
		this.postal = postal;
	}
	
	public String toString() {
		String full = "";
		full += this.name + "\n";
		full += this.street + "\n";
		full += this.city + ", " + this.province + "  " + this.postal + "\n";
		full += "CANADA";
		return full;
	}
}