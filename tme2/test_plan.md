# Test Plan

## Generic Order

### Create Generic Orders

**Input:**
new GenericOrder<Motherboard>();

**Expected Result:**
An instance of GenericOrder<Motherboard> with UUID set.

**Input:**
new GenericOrder<Integer>();

**Expected Result:**
An instance of GenericOrder<Integer> with UUID set.

**Input:**
new GenericOrder<Motherboard>(products); // Where products is ArrayList<Motherboard>

**Expected Result:** An instance of GenericOrder<Motherboard> with UUID and product array set.

### Getters/Setters
No test plan for getters and setters as they really only use fundamental Java language features.

## Computer Order

### Create Computer Orders

**Input:**
new ComputerOrder<IComputer>();

**Expected Result:**
An instance of ComputerOrder<IComputer> with UUID set.

**Input:**
new ComputerOrder<IParty>();

**Expected Result:**
Compiler error.

### Add Valid Item to Order

**Input:**
ComputerOrder<IComputer> order = new ComputerOrder<>();
order.addProduct(new Motherboard());
order.addProduct(new RAM());
order.addProduct(new Printer());
order.addProduct(new DeliveryService());

**Expected Result:**
ComputerOrder with a Motherboard, RAM, Printer, and DeliveryService included.

### Add Invalid Item to Order

**Input:**
ComputerOrder<IComputer> order = new ComputerOrder<>();
order.addProduct(new Apple());
order.addProduct(new Cheddar());

**Expected Result:**
Both of the addProduct() calls will cause a compiler error.

## Party Tray Order

### Create Party Tray Orders

**Input:**
new PartyTrayOrder<IParty>();

**Expected Result:**
An instance of PartyTrayOrder<IParty> with UUID set.

**Input:**
new PartyTrayOrder<IComputer>();

**Expected Result:**
Compiler error.

### Add Valid Item to Order

**Input:**
PartyTrayOrder<IParty> order = new PartyTrayOrder<>();
order.addProduct(new Apple());
order.addProduct(new Cheddar());
order.addProduct(new AssemblyService());

**Expected Result:**
PartyTrayOrder with an Apple, Cheddar, and AssemblyService included.

### Add Invalid Item to Order

**Input:**
PartyTrayOrder<IParty> order = new PartyTrayOrder<>();
order.addProduct(new Motherboard());
order.addProduct(new Printer());

**Expected Result:**
Both of the addProduct() calls with cause a compiler error.

