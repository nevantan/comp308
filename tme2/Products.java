import java.util.UUID;

enum DriveType {
	SSD, HDD
}

interface IComputer {}
interface IParty {}

/******************************************************************/

abstract class Product {
	protected float price;
	protected UUID order_id;
	
	public float getPrice() {
		return this.price;
	}
	
	public void setID(UUID id) {
		this.order_id = id;
	}
	
	public String toString() {
		return "---\n" + this.getClass().getSimpleName() + "\nOrder #: " + this.order_id + "\nPrice: $" + this.price;
	}
}

/******************************************************************/

class ComputerPart extends Product implements IComputer {
	protected String manufacturer;
	
	public ComputerPart(String mfg, float p) {
		this.manufacturer = mfg;
		this.price = p;
	}
	
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	public String toString() {
		return  super.toString() + "\nManufacturer: " + this.manufacturer;
	}
}

class Motherboard extends ComputerPart {
	public Motherboard(String mfg, float p) {
		super(mfg, p);
	}
}

class Storage extends ComputerPart {
	protected int size;
	
	public Storage(String mfg, int size, float p) {
		super(mfg, p);
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public String toString() {
		return super.toString() + "\nSize: " + this.size;
	}
}

class RAM extends Storage {	
	public RAM(String mfg, int size, float p) {
		super(mfg, size, p);
	}
}

class Drive extends Storage {
	protected DriveType type;
	protected int speed;
	
	public Drive(String mfg, DriveType type, int size, int speed, float p) {
		super(mfg, size, p);
		this.type = type;
		this.speed = speed;
	}
	
	public DriveType getType() {
		return this.type;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public String toString() {
		return super.toString() + "\nType: " + this.type + "\nSpeed: " + this.speed;
	}
}

/******************************************************************/

class Peripheral extends Product implements IComputer {
	protected String model;
	
	public Peripheral(String model, float p) {
		this.price = p;
		this.model = model;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public String toString() {
		return super.toString() + "\nModel: " + this.model;
	}
}

class Printer extends Peripheral {
	public Printer(String model, float p) {
		super(model, p);
	}
}

class Monitor extends Peripheral {
	public Monitor(String model, float p) {
		super(model, p);
	}
}

/******************************************************************/

class Service extends Product implements IComputer, IParty {
	protected String vendor;
	
	public Service(String vendor, float p) {
		this.vendor = vendor;
		this.price = p;
	}
	
	public String getVendor() {
		return this.vendor;
	}
	
	public String toString() {
		return super.toString() + "\nVendor: " + this.vendor;
	}
}

class AssemblyService extends Service {	
	public AssemblyService(String vendor, float p) {
		super(vendor, p);
	}
}

class DeliveryService extends Service {
	public DeliveryService(String vendor, float p) {
		super(vendor, p);
	}
}

/******************************************************************/

class Cheese extends Product implements IParty {
	public Cheese(float p) {
		this.price = p;
	}
	
	public String toString() {
		return super.toString();
	}
}

class Cheddar extends Cheese {
	public Cheddar(float p) {
		super(p);
	}
}

class Mozzarella extends Cheese {
	public Mozzarella(float p) {
		super(p);
	}
}

/******************************************************************/

class Fruit extends Product implements IParty {
	public Fruit(float p) {
		this.price = p;
	}
	
	public String toString() {
		return super.toString();
	}
}

class Apple extends Fruit {
	public Apple(float p) {
		super(p);
	}
}

class Orange extends Fruit {
	public Orange(float p) {
		super(p);
	}
}