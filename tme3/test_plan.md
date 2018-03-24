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

## Restart Event

### Thermostat Night Settings Line

**Input:** `Event=ThermostatNight,time=0`

**Output:** `Thermostat on night setting`

### Bell Settings Line - Default

**Input:** `Event=Bell,time=0`

**Output:** `Bing!`

### Bell Settings Line - Rings Specified

**Input:** `Event=Bell,time=0,rings=5`

**Output:**

```
Bing!
Bing!
Bing!
Bing!
Bing!
```

### Repeat Bell Interleaved

**Input:**

```
Event=ThermostatNight,time=0
Event=Bell,time=2000,rings=5
Event=WaterOn,time=3000
Event=WaterOff,time=5000
Event=Terminate,time=7000
```

**Output:**

```
Thermostat on night settings
Bing!
Greenhouse water is on
Bing!
Greenhouse water is off
Bing!
```

**Note:** The Terminate event prevents the bell from ringing the last two times.