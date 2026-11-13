# Which Expression

Reads three whole numbers `a`, `b` and `c`, works out `a + b * c`, `a * b + c` and `(a + b) * c`, and says which is largest. `readInt` uses `hasNextInt` to throw away anything that isn't a whole number and ask again.

## Run

```bash
javac WhichExpression.java
java WhichExpression
```

## Example runs

```
Please input a: 2
Please input b: three
That is not a whole number. Please input b: 3
Please input c: 4
a + b * c   = 14
a * b + c   = 10
(a + b) * c = 20
(a + b) * c gives the largest result
```

```
Please input a: 5
Please input b: 1
Please input c: -2
a + b * c   = 3
a * b + c   = 3
(a + b) * c = -12
a + b * c gives the largest result
```
