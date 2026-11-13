# Double Size

Reads some numbers into an array and passes it to a `doubleSize` method. The method returns a new array twice as long, with the original values copied to the front and 0s after them. The original array is not changed.

## Run

```bash
javac DoubleSize.java
java DoubleSize
```

## Example runs

```
Please input the number of values: 3
Please input the values: 4 7 9
Original (length 3): [4, 7, 9]
Doubled  (length 6): [4, 7, 9, 0, 0, 0]
```

```
Please input the number of values: 0
The array needs at least one value
```
