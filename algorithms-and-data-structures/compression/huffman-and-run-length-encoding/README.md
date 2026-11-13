# Huffman encoding and run-length encoding

Pen and paper: build a Huffman code for a short string and work out how much it compresses.
Programming: run-length encode a string and print the compression ratio.

## Pen and paper: Huffman encoding of "to be or not to be"

### Frequencies

The string has 18 characters, counting the 5 spaces.

| Character | space | o | t | b | e | n | r |
|---|---|---|---|---|---|---|---|
| Count | 5 | 4 | 3 | 2 | 2 | 1 | 1 |

### Building the tree

Repeatedly take the two lowest counts and join them under a new node whose count is their sum.

| Step | Join | New node | Remaining |
|---|---|---|---|
| 1 | n(1) + r(1) | nr(2) | nr 2, b 2, e 2, t 3, o 4, space 5 |
| 2 | b(2) + e(2) | be(4) | nr 2, t 3, o 4, be 4, space 5 |
| 3 | nr(2) + t(3) | nrt(5) | o 4, be 4, space 5, nrt 5 |
| 4 | o(4) + be(4) | obe(8) | space 5, nrt 5, obe 8 |
| 5 | space(5) + nrt(5) | (10) | obe 8, (10) |
| 6 | obe(8) + (10) | root(18) | |

```
                    (18)
                 0 /    \ 1
               (8)        (10)
             0/   \1     0/    \1
            o      (4)  space   (5)
                  0/ \1        0/  \1
                  b   e       (2)   t
                             0/ \1
                             n   r
```

### Codes

Left branches are 0 and right branches are 1.

| Character | Count | Code | Bits used (count × length) |
|---|---|---|---|
| space | 5 | 10 | 10 |
| o | 4 | 00 | 8 |
| t | 3 | 111 | 9 |
| b | 2 | 010 | 6 |
| e | 2 | 011 | 6 |
| n | 1 | 1100 | 4 |
| r | 1 | 1101 | 4 |
| **Total** | 18 | | **47** |

The encoded string is:

```
t   o  _  b   e   _  o  r    _  n    o  t   _  t   o  _  b   e
111 00 10 010 011 10 00 1101 10 1100 00 111 10 111 00 10 010 011
```

### Compression

- Original: 18 characters × 7 bits = 126 bits
- Huffman: 47 bits
- 47 / 126 = **37.3%** of the original size

There are ties when picking the two lowest counts (for example b, e and the n+r node all have 2 in
step 2), so other trees with different codes are possible. They all come to the same 47 bits.

## Programming: run-length encoding

Each run of the same character is replaced by the length of the run followed by the character. The
program prints the encoded string and its length as a percentage of the original length.

### Build and run

```bash
javac RLE.java
java RLE
```

### Sample run

```
Enter the string to be compressed: 3322251
Compressed as: 23321511
114.3%
```

### Tests run

| Input | Compressed | Ratio | Checks |
|---|---|---|---|
| `3322251` | `23321511` | 114.3% | given example |
| `aaaaaaaaaaaabbb` | `12a3b` | 33.3% | a run longer than 9 |
| `a` | `1a` | 200.0% | single character |
| (empty line) | (empty) | not printed | empty input |
| `aa  b` | `2a2 1b` | 120.0% | spaces are counted like any other character |

### Notes

- The opening lines of `main` are provided starter code. The `RLE` class, `compress` and the ratio
  output are the solution.
- The ratio is printed on its own line with one decimal place.
- No ratio is printed for an empty string, since the original length is 0.
- Because the count is written as a number, a string that itself contains digits (like `3322251`)
  can't always be decoded without knowing where each count ends. The lab only asks for
  encoding.
