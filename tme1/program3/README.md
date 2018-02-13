# Program 3 Test Plan

## Files
- Cycle.java
- Unicycle.java
- Bicycle.java
- Tricycle.java
- Main.java

## Manual Testing

### Instantiate Cycle

**Input:**

```
new Cycle();
```

**Expected Result:**

Compiler error: Cycle is abstract; cannot be instantiated

### Ride Unicycle

**Input:**

```
Unicycle unicycle = new Unicycle()
unicycle.ride();
```

**Expected Result:**

Logs: "Riding a Unicycle which has 1 wheel(s)."

### Ride Bicycle

**Input:**

```
Bicycle bicycle = new Bicycle();
bicycle.ride();
```

**Expected Result:**

Logs "Riding a Bicycle which has 2 wheel(s)."

### Ride Tricycle

**Input:**

```
Tricycle tricycle = new Tricycle();
tricycle.ride();
```

**Expected Result:**

Logs "Riding a Tricycle which has 2 wheel(s)."