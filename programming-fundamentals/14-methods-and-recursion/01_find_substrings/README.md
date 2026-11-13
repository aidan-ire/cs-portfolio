# Find SubStrings

Reads a string and a substring and lists every index where the substring starts, comparing character by character in a `matchesAt` method. Overlapping matches are counted, so `aa` is found twice in `aaa`.

## Run

```bash
javac FindSubStrings.java
java FindSubStrings
```

## Example runs

```
Please input a string: the cat sat on the mat
Please input the substring to find: at
"at" found 3 times, starting at indexes 5, 9, 20
```

```
Please input a string: banana
Please input the substring to find: ana
"ana" found 2 times, starting at indexes 1, 3
```

```
Please input a string: Dublin
Please input the substring to find: x
"x" was not found
```
