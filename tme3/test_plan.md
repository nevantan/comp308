# Test Plan

## Greenhouse Fans

### Turn Fans On

**Input:** `gc.new FansOn(2000)`

**Output:** `Greenhouse fans are on`

### Turn Fans Off

**Input:** `gc.new FansOff(2000)`

**Output:** `Greenhouse fans are off`

## Greenhouse Bell

### Negative Rings

**Input:** `gc.new Bell(1000, -1, 0)`

**Output:**

`Bing!`

### Zero Rings

**Input:** `gc.new Bell(1000, 0, 0)`

**Output:**

`Bing!`

**Note:** The bell must ring at least once, if zero rings are desired, the event shouldn't be added in the first place.

### One Ring

**Input:** `gc.new Bell(1000, 1, 0)`

**Output:**

`Bing!`

### Three Rings

**Input:** `gc.new Bell(1000, 3, 0)`

**Output:**

```
Bing!
Bing!
Bing!
```