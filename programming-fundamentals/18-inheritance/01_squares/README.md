# Squares

`Square` extends `Rectangle`, passing its one side to the `Rectangle` constructor as both width and height. It inherits area and perimeter, and adds `getDiagonal`. The test keeps rectangles and squares in one `Rectangle[]`, and uses `instanceof` and a cast to reach the square-only method.

## Files

- `Rectangle.java` — the superclass
- `Square.java` — the subclass
- `TestSquares.java` — a mixed array of shapes

## Run

```bash
javac *.java
java TestSquares
```

## Example run

```
Rectangle 3.0 x 5.0: area 15.00, perimeter 16.00
Square with side 4.0: area 16.00, perimeter 16.00
  it is a Square, diagonal 5.66
Rectangle 2.5 x 2.5: area 6.25, perimeter 10.00
Square with side 1.5: area 2.25, perimeter 6.00
  it is a Square, diagonal 2.12
```
