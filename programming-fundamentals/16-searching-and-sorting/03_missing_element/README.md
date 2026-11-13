# MissingElement

Reads the numbers 1 to n in any order with one left out and finds the missing one. The numbers are sorted with an insertion sort, then a binary search finds the first value that isn't in its expected place.

## Run

```bash
javac MissingElement.java
java MissingElement
```

## Example runs

```
Please input n: 8
Please input the numbers 1 to 8 in any order, leaving one out: 5 2 8 1 7 3 4
Sorted: [1, 2, 3, 4, 5, 7, 8]
Missing number: 6
```

```
Please input n: 5
Please input the numbers 1 to 5 in any order, leaving one out: 3 1 4 2
Sorted: [1, 2, 3, 4]
Missing number: 5
```
