# Vehicles

A `Vehicle` superclass with `Car`, `Motorbike` and `Truck` subclasses. Each one overrides `getType` and `getWheels`, and `Car` and `Truck` extend `describe` with their own details. `describe` is written once in `Vehicle` and picks up each subclass's overrides.

## Files

- `Vehicle.java` — the superclass
- `Car.java` — four wheels and a number of doors
- `Motorbike.java` — two wheels
- `Truck.java` — wheels from its axles and a maximum load
- `TestVehicles.java` — describes a mixed fleet

## Run

```bash
javac *.java
java TestVehicles
```

## Example run

```
2019 Toyota Corolla (Car, 4 wheels), 5 doors, 7 years old
2022 Honda CB500F (Motorbike, 2 wheels), 4 years old
2015 Volvo FH16 (Truck, 12 wheels), carries up to 26.0 tonnes, 11 years old
2010 Generic Cart (Vehicle, 4 wheels), 16 years old
Vehicles: 4, wheels altogether: 22
```
