# Number algorithms

Five small programs that each read integers from standard input. Each file has a comment block
explaining how the algorithm works.

| File | Task |
|---|---|
| `reverse.cpp` | reverses the digits of an integer, keeping the sign |
| `gcd.cpp` | greatest common divisor of two integers using Euclid's algorithm |
| `palindrome.cpp` | checks whether an integer reads the same in both directions (negatives are not palindromes) |
| `pow.cpp` | power function in O(log n) by halving the exponent |
| `log2.cpp` | checks whether an integer is a power of two by repeated division |

## Build and run

```bash
g++ -Wall -o reverse reverse.cpp && ./reverse
```

The other files build the same way.

## Sample runs

| Program | Input | Output |
|---|---|---|
| `reverse` | `1234567` | `Reverse of 1234567 is: 7654321` |
| `reverse` | `-89356` | `Reverse of -89356 is: -65398` |
| `gcd` | `24 36` | `The gcd between 24 and 36 is 12` |
| `gcd` | `90 299` | `The gcd between 90 and 299 is 1` |
| `palindrome` | `1221` | `1221 is a palindrome` / `true` |
| `palindrome` | `-12221` | `-12221 is NOT a palindrome` / `false` |
| `pow` | `2 3` | `2 to the power of 3 is 8` |
| `pow` | `1 200` | `1 to the power of 200 is 1` |
| `log2` | `128` | `128 is a power of two` / `true` |
| `log2` | `23` | `23 is NOT a power of two` / `false` |

## Notes

- Overflow is not handled in `reverse.cpp` or `pow.cpp`.
- `gcd(0, 0)` is undefined, so the function returns -1 for it.
- `pow.cpp` expects a non-negative exponent.
