# Count Digits

Counts the digits in a whole number recursively, stripping one digit per call. 0 counts as one digit, and a minus sign is ignored. It reads a `long`, so numbers up to 19 digits work.

## Run

```bash
javac CountDigits.java
java CountDigits
```

## Example runs

```
Please input a number: 905
905 has 3 digits
```

```
Please input a number: 0
0 has 1 digit
```

```
Please input a number: -1234567890123
-1234567890123 has 13 digits
```
