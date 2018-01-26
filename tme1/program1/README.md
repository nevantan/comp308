# Program 1 Test Plan

## Files
- Circle.java
- CircleTest.java

## Automated Testing
CircleTest.java is a JUnit 4 test class that will run the following tests:
- defaultConstructor
- mainConstructor
- correctCircumference
- correctArea
- correctlySetRadius
- correctlyLimitRadius
- correctlyPrintAttributes
- testIsInside
- testIsNotInside
- testMove

## Manual Testing

NOTE: Instantiation of the Circle class is provided in the input for orientation purposes where the result
of a method is dependent on the x, y, or radius field(s).

### Default Constructor

**Input:**

```
new Circle()
```

**Expected Result:**

Circle at (0, 0) with a radius of 5


### Main Constructor

**Input:**

```
new Circle(10, 10, 15)
```

**Expected Result:**

Circle at (10, 10) with a radius of 15


### Correct Circumference

**Input:**

```
Circle circle = new Circle(0, 0, 5)
circle.circumference()
```

**Expected Result:**

31.42


### Correct Area

**Input:**

```
Circle circle = new Circle(0, 0, 5)
circle.area()
```

**Expected Result:**

78.54


### Correctly Set Radius

**Input:**

```
circle.setRadius(10)
```

**Expected Result:**

```
circle.radius == 10 // True
```


### Correctly Limit Radius

**Input:**

```
circle.setRadius(55)
```

**Expected Result:**

```
circle.radius == 50 // True
```


### Correctly Print Attributes

**Input:**

```
Circle circle = new Circle(0, 0, 5)
circle.printAttributes()
```

**Expected Result:**

Output:

Coords: (0, 0)
Radius: 5.00
Circumference: 31.42
Area: 78.54


### Test Is Inside

**Input:**

```
Circle circle = new Circle(0, 0, 5)
circle.isInside(2, 2)
```

**Expected Result:**

true


### Test Is Not Inside

**Input:**

```
Circle circle = new Circle(0, 0, 5)
circle.isInside(10, 10)
```

**Expected Result:**

false


### Test Move (Positive)

**Input:**

```
Circle circle = new Circle(2, 2, 5)
circle.move(5, 5)
```

**Expected Result:**

```
circle.x == 7 // True
circle.y == 7 // True
```


### Test Move (Negative)

**Input:**

```
Circle circle = new Circle(2, 2, 5)
circle.move(-5, -5)
```

**Expected Result:**

```
circle.x == -3 // True
circle.y == -3 // True
```


### Test Move (Independent)

**Input:**

```
Circle circle = new Circle(5, 5, 5)
circle.move(2, -3)
```

**Expected Result:**

```
circle.x == 7 // True
circle.y == 2 // True
```