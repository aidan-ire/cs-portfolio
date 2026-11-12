# Lab 7: string searching (naive and KMP)

Pen and paper: trace the naive string search and build the KMP partial match table. Programming:
find every occurrence of a pattern in a text using KMP.

## Pen and paper

Text: `ABABDABACDABABCABAB` (19 characters, indexes 0–18)
Pattern: `ABABCABAB` (9 characters)

### (a) Naive string search

The pattern is lined up at each position of the text in turn (shifts 0 to 10) and compared left to
right. At the first mismatch the pattern moves along by one and the comparison starts again from the
beginning of the pattern.

| Shift | Text under the pattern | Comparisons | Result |
|---|---|---|---|
| 0 | `ABABDABAC` | 5 | A B A B match, pattern[4] `C` vs text `D` mismatch |
| 1 | `BABDABACD` | 1 | pattern[0] `A` vs `B` mismatch |
| 2 | `ABDABACDA` | 3 | A B match, pattern[2] `A` vs `D` mismatch |
| 3 | `BDABACDAB` | 1 | `A` vs `B` mismatch |
| 4 | `DABACDABA` | 1 | `A` vs `D` mismatch |
| 5 | `ABACDABAB` | 4 | A B A match, pattern[3] `B` vs `C` mismatch |
| 6 | `BACDABABC` | 1 | `A` vs `B` mismatch |
| 7 | `ACDABABCA` | 2 | A matches, pattern[1] `B` vs `C` mismatch |
| 8 | `CDABABCAB` | 1 | `A` vs `C` mismatch |
| 9 | `DABABCABA` | 1 | `A` vs `D` mismatch |
| 10 | `ABABCABAB` | 9 | all 9 characters match |

The pattern is found once, at **index 10**. The search takes 10 shifts and **29 comparisons** in
total.

### (b) KMP partial match table (LPS array)

`lps[i]` is the length of the longest proper prefix of `pattern[0..i]` that is also a suffix of it.

| i | Substring `pattern[0..i]` | Longest prefix that is also a suffix | lps[i] |
|---|---|---|---|
| 0 | `A` | none (a single character has no proper prefix) | 0 |
| 1 | `AB` | none | 0 |
| 2 | `ABA` | `A` | 1 |
| 3 | `ABAB` | `AB` | 2 |
| 4 | `ABABC` | none (nothing ends in C except the whole string) | 0 |
| 5 | `ABABCA` | `A` | 1 |
| 6 | `ABABCAB` | `AB` | 2 |
| 7 | `ABABCABA` | `ABA` | 3 |
| 8 | `ABABCABAB` | `ABAB` | 4 |

```
pattern:  A B A B C A B A B
lps:      0 0 1 2 0 1 2 3 4
```

How the values come out when building it left to right, keeping `len` as the length of the prefix
currently matched:

- i = 1: `B` vs pattern[0] `A`, no match and len is 0, so lps[1] = 0
- i = 2: `A` vs pattern[0] `A`, match, len = 1
- i = 3: `B` vs pattern[1] `B`, match, len = 2
- i = 4: `C` vs pattern[2] `A`, no match. len falls back to lps[1] = 0. `C` vs pattern[0] `A`, no
  match, so lps[4] = 0
- i = 5 to 8: `A`, `B`, `A`, `B` match pattern[0], [1], [2], [3] in turn, so len goes 1, 2, 3, 4

## Programming

`KMPSearch` prints the starting index of every match, and `computeLPS` builds the table above. No
built-in search methods are used. The text position never moves backwards; on a mismatch only the
pattern position falls back, using the table.

### Build and run

```bash
javac Lab7_StringSearch.java
java Lab7_StringSearch
```

### Sample run

```
Enter text: ABABDABACDABABCABAB
Enter pattern: ABABCABAB
10 
```

### Tests run

| Text | Pattern | Output | Checks |
|---|---|---|---|
| `ABABDABACDABABCABAB` | `ABABCABAB` | `10` | given example |
| `AAAAA` | `AA` | `0 1 2 3` | overlapping matches |
| `abcabcabc` | `c` | `2 5 8` | single-character pattern, several matches |
| `abc` | `abc` | `0` | pattern is the whole text |
| `hello` | `xyz` | `-1` | no match |
| `abc` | `abcd` | `-1` | pattern longer than the text |
| `abc` | (empty) | `-1` | empty pattern |

### Notes

- Several match indexes are printed on one line separated by spaces.
- An empty pattern prints -1.
- `KMPSearch` and `computeLPS` are the solution; the rest of the file is provided starter code.
