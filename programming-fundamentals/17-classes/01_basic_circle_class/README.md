# BasicCircle Class

A `BasicCircle` class with a private radius, two constructors, a getter and a setter that rejects negative values, and methods for diameter, area and circumference. `TestBasicCircle` creates two circles, changes one, and shows a negative radius being rejected.

## Files

- `BasicCircle.java` — the class
- `TestBasicCircle.java` — creates circles and prints their measurements

## Run

```bash
javac *.java
java TestBasicCircle
```

## Example run

```
Circle with radius 1.00
  diameter 2.00, area 3.14, circumference 6.28
Circle with radius 3.00
  diameter 6.00, area 28.27, circumference 18.85
Changing the radius of the second circle to 0.5
Circle with radius 0.50
  diameter 1.00, area 0.79, circumference 3.14
setRadius(-2) rejected: radius can't be negative
Circle with radius 0.50
  diameter 1.00, area 0.79, circumference 3.14
```
