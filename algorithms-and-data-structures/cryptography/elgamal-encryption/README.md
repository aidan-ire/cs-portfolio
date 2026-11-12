# Lab 6: ElGamal encryption

Pen and paper: break a small ElGamal key by brute force and recover the message. Programming:
encrypt a message with ElGamal.

## Pen and paper

Bob's public key is (p, g, g^x mod p) = (23, 5, 17). Alice uses ephemeral key y = 4 and sends
c1 = 4, c2 = 19.

### 1) Brute force for the private key x

x is the power that satisfies 5^x mod 23 = 17. Try each power in turn, multiplying the previous
result by 5 and taking the remainder:

| x | 5^x mod 23 |
|---|---|
| 1 | 5 |
| 2 | 25 mod 23 = 2 |
| 3 | 10 |
| 4 | 50 mod 23 = 4 |
| 5 | 20 |
| 6 | 100 mod 23 = 8 |
| 7 | 40 mod 23 = **17** |

So **x = 7**. (As a check, row 4 gives 5^4 mod 23 = 4, which is c1 with y = 4.)

### 2) Recovering the message m

The shared secret is s = c1^x mod p = 4^7 mod 23.

| Power | 4^n mod 23 |
|---|---|
| 4^1 | 4 |
| 4^2 | 16 |
| 4^3 | 64 mod 23 = 18 |
| 4^4 | 72 mod 23 = 3 |
| 4^5 | 12 |
| 4^6 | 48 mod 23 = 2 |
| 4^7 | **8** |

So s = 8. Since c2 = m × s mod p, m is c2 multiplied by the inverse of s.

The inverse of 8 mod 23 is 3, because 8 × 3 = 24 = 1 mod 23.

m = 19 × 3 mod 23 = 57 mod 23 = **11**

**Check:** encrypting m = 11 with y = 4 gives c1 = 5^4 mod 23 = 4 and
c2 = 11 × 17^4 mod 23 = 11 × 8 mod 23 = 88 mod 23 = 19, which is the ciphertext Alice sent.

## Programming

`main` and the outline of `encrypt` are provided starter code; `encrypt` and `modPow` are the
solution. `encrypt` computes

- c1 = g^y mod p
- c2 = m × (g^x)^y mod p

with the ephemeral key y fixed at 3. A helper, `modPow`, does the modular
exponentiation by repeated squaring so the intermediate values stay small.

### Build and run

```bash
javac ElGamal.java
java ElGamal
```

Enter the three key numbers on one line separated by single spaces, then the message.

### Sample run

```
Enter the private key: 
23 5 17 a
Now enter the message: 
2
The ciphertext is: (10, 5)
```

### Tests run

| Key | Message | Ephemeral key | Output | Checks |
|---|---|---|---|---|
| `23 5 17` | 2 | 3 | `(10, 5)` | given example |
| `23 5 17` | 11 | 4 | `(4, 19)` | the pen-and-paper answer, run on a copy with `ephemeral = 4` |
| `2147483647 7 123456789` | 2000000000 | 3 | `(343, 1730575769)` | large prime, checked against Python's `pow` for overflow |

### Notes

- To reproduce the second test, change `int ephemeral = 3;` to `4` in `encrypt`.
- `modPow` uses `long`, so it is safe for any prime p that fits in an `int`.
