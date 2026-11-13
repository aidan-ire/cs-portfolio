# BasicSquare Class

A `BasicSquare` class with a private side length, validation in the constructor and setter, and methods for area, perimeter and diagonal. `isBiggerThan` compares the square with another one.

## Files

- `BasicSquare.java` — the class
- `TestBasicSquare.java` — creates and compares squares

## Run

```bash
javac *.java
java TestBasicSquare
```

## Example run

```
Square with side 4.00
  area 16.00, perimeter 16.00, diagonal 5.66
Square with side 2.50
  area 6.25, perimeter 10.00, diagonal 3.54
First square bigger than the second: true
Doubling the second square's side
Square with side 5.00
  area 25.00, perimeter 20.00, diagonal 7.07
First square bigger than the second: false
new BasicSquare(-1) rejected: side can't be negative
```
