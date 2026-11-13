# ASCII Difference

Reads a word and prints the ASCII value difference between each character and the next one, then which neighbouring pair is furthest apart.

## Run

```bash
javac ASCIIDifference.java
java ASCIIDifference
```

## Example runs

```
Please input a word: Dublin
M (77) -> a (97): 20
a (97) -> y (121): 24
y (121) -> n (110): -11
n (110) -> o (111): 1
o (111) -> o (111): 0
o (111) -> t (116): 5
t (116) -> h (104): -12
Largest jump: a to y
```

```
Please input a word: abcz
a (97) -> b (98): 1
b (98) -> c (99): 1
c (99) -> z (122): 23
Largest jump: c to z
```
