# Comma Numbers

Reads a whole number and prints it with a comma between each group of three digits, such as `1234567` → `1,234,567`. The commas are added by working through the digits from the right, without `String.format`. Negative numbers keep their sign.

## Run

```bash
javac CommaNumbers.java
java CommaNumbers
```

## Example runs

```
Please input a whole number: 1234567
With commas: 1,234,567
```

```
Please input a whole number: -9876543210
With commas: -9,876,543,210
```

```
Please input a whole number: 999
With commas: 999
```
