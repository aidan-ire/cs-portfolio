# Lab 5: bit manipulation

Pen and paper: evaluate a bitwise expression by hand. Programming: reverse the bits of a 32-bit
integer.

## Pen and paper: `((7|8)<<1)^(5&6)`

Working from the inside out, in binary:

**1) `7 | 8`** — OR gives a 1 wherever either number has a 1.

```
  0111   (7)
| 1000   (8)
  ----
  1111   (15)
```

**2) `15 << 1`** — shifting left by one moves every bit up a place and puts a 0 on the right, which
doubles the number.

```
 1111 << 1  =  11110   (30)
```

**3) `5 & 6`** — AND gives a 1 only where both numbers have a 1.

```
  0101   (5)
& 0110   (6)
  ----
  0100   (4)
```

**4) `30 ^ 4`** — XOR gives a 1 where the two bits are different.

```
  11110   (30)
^ 00100   (4)
  -----
  11010   (26)
```

11010 = 16 + 8 + 2 = **26**

`BitManipulation.java` is the provided program, and running it prints `26`.

## Programming: reverse bits

`reverseBits(int n)` builds the answer one bit at a time. On each of 32 passes it shifts the result
left, copies in the lowest bit of `n`, and shifts `n` right, so the lowest bit of `n` ends up as the
highest bit of the result.

### Build and run

```bash
javac BitManipulation.java ReverseBits.java
java BitManipulation
java ReverseBits
```

### Output

```
26
```

```
964176192
1073741822
```

| Input | Binary | Output | Binary |
|---|---|---|---|
| 43261596 | `00000010100101000001111010011100` | 964176192 | `00111001011110000010100101000000` |
| 2147483644 | `01111111111111111111111111111100` | 1073741822 | `00111111111111111111111111111110` |

Both match the given examples.

### Notes

- `main` and the `reverseBits` signature are provided starter code; the body of `reverseBits` is the
  solution.
- `>>>` (unsigned shift) is used so the method would still work on a negative input. The input is
  limited to even values from 0 to 2^31 − 2, so the result always fits in a positive `int`.
