# Program 2 Test Plan

## Files

- LabelMaker.java
- FullName.java
- MailingAddress.java
- ShippingLabel.java

## Automated Testing

### FullNameTest.java

JUnit 4 test class that will run the following tests:
- defaultConstructor
- mainConstructor
- fullToString
- noTitleToString
- noMiddleToString

### MailingAddressTest.java

JUnit 4 test class that will run the following tests:
- defaultConstructor
- mainConstructor
- addressToString

### ShippingLabelTest.java

JUnit 4 test class that will run the following tests:
- defaultConstructor
- mainConstructor
- labelToString

## Manual Testing

### FullName Test

#### Default Constructor

**Input:**

```
new FullName()
```

**Expected Result:**

FullName object with empty strings for `title`, `first`, `middle`, and `last`.

#### Main Constructor

**Input:**

```
new FullName("Mr.", "John", "Theodore", "Doe");
```

**Expected Result:**

FullName object where:
- `title == "Mr."`
- `first == "John"`
- `middle == "Theodore"`
- `last == "Doe"`

#### Incomplete Constructor

**Input:**

```
new FullName("Mr.", "John", "Doe");
```

**Expected Result:**

Error thrown at compilation (no matching constructor). This is a test that omits the middle
name in an incorrect way. The proper way to create a FullName object without a middle name
would be to provide an empty string as the middle name.

#### Full Name to String

**Input:**

```
FullName name = new FullName("Mr.", "John", "Theodore", "Doe");
name.toString();
```

**Expected Result:**

"Mr. John Theodore Doe"

#### No Title to String

**Input:**

```
FullName name = new FullName("", "John", "Theodore", "Doe");
name.toString();
```

**Expected Result:**

"John Theodore Doe"

#### No Middle Name to String

**Input:**

```
FullName name = new FullName("Mr.", "John", "", "Doe");
name.toString();
```

**Expected Result:**

"Mr. John Doe"

### MailingAddress Test

#### Default Constructor

**Input:**

```
new MailingAddress()
```

**Expected Result:**

A MailingAddress object where `name` is a default FullName object and all other fields
are empty strings.

#### Main Constructor

**Input:**

```
new MailingAddress(new FullName(), "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
```

**Expected Result:**

A MailingAddress object where:
- `name == new FullName()`
- `street == "1 Sussex Dr."`
- `city == "Ottawa"`
- `province == "Ontario"`
- `postal == "K1A 0A1"`

#### MailingAddress to String

**Input:**

```
MailingAddress address = new MailingAddress(new FullName(), "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
address.toString();
```

**Expected Result:**

Mr. John Theodore Doe
1 Sussex Dr.
Ottawa, Ontario  K1A 0A1
CANADA

NOTE: There are two spaces between the province and the postal code.

### Shipping Label Test

#### Default Constructor

**Input:**

```
new ShippingLabel()
```

**Expected Result:**

A ShippingLabel object with both fields set to default MailingAddress objects.

#### Main Constructor

**Input:**

```
MailingAddress address = new MailingAddress(new FullName(), "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
new ShippingLabel(new MailingAddress(), address);
```

**Expected Result:**

A ShippingLabel object with the `from` address set to a default MailingAddress and the `to`
address matching the `address` object.

#### Label to String

**Input:**

```
MailingAddress address = new MailingAddress(new FullName(), "1 Sussex Dr.", "Ottawa", "Ontario", "K1A 0A1");
ShippingLabel label = new ShippingLabel(new MailingAddress(), address);
label.toString();
```

**Expected Result:**

Mr. John Theodore Doe
1 Sussex Dr.
Ottawa, Ontario  K1A 0A1
CANADA

NOTE: This is the exact output of `address.toString()`.