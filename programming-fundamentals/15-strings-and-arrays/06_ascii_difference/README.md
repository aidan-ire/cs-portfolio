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
D (68) -> u (117): 49
u (117) -> b (98): -19
b (98) -> l (108): 10
l (108) -> i (105): -3
i (105) -> n (110): 5
Largest jump: D to u
```

```
Please input a word: abcz
a (97) -> b (98): 1
b (98) -> c (99): 1
c (99) -> z (122): 23
Largest jump: c to z
```
