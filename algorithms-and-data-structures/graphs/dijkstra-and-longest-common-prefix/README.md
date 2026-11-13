# Dijkstra's algorithm and longest common prefix

Pen and paper: find the shortest path between two vertices of a weighted graph with Dijkstra's
algorithm. Programming: find the longest common prefix of a list of words.

## Pen and paper: shortest path from B to D

The graph is undirected with eight vertices (A to H) and these edges:

| Edge | Weight | | Edge | Weight |
|---|---|---|---|---|
| B – G | 3 | | C – H | 4 |
| B – F | 9 | | G – H | 8 |
| F – G | 5 | | H – D | 9 |
| F – E | 1 | | C – D | 7 |
| E – C | 6 | | C – A | 5 |
| | | | A – D | 2 |

Every vertex starts with a distance of ∞ except B, which is 0. At each step the unvisited vertex with
the smallest distance is visited, and each of its unvisited neighbours is updated if going through it
is shorter. Each cell shows the best distance so far and the vertex it was reached from. A visited
vertex is marked with ✓ and does not change again.

| Step | Visit | A | B | C | D | E | F | G | H |
|---|---|---|---|---|---|---|---|---|---|
| start | | ∞ | 0 | ∞ | ∞ | ∞ | ∞ | ∞ | ∞ |
| 1 | B (0) | ∞ | ✓ | ∞ | ∞ | ∞ | 9 / B | 3 / B | ∞ |
| 2 | G (3) | ∞ | ✓ | ∞ | ∞ | ∞ | 8 / G | ✓ | 11 / G |
| 3 | F (8) | ∞ | ✓ | ∞ | ∞ | 9 / F | ✓ | ✓ | 11 / G |
| 4 | E (9) | ∞ | ✓ | 15 / E | ∞ | ✓ | ✓ | ✓ | 11 / G |
| 5 | H (11) | ∞ | ✓ | 15 / E | 20 / H | ✓ | ✓ | ✓ | ✓ |
| 6 | C (15) | 20 / C | ✓ | ✓ | 20 / H | ✓ | ✓ | ✓ | ✓ |
| 7 | D (20) | 20 / C | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ |

What happens at each step:

1. Visit B. G = 0 + 3 = 3 and F = 0 + 9 = 9.
2. Visit G (smallest, 3). F through G is 3 + 5 = 8, which beats 9, so F is updated. H = 3 + 8 = 11.
3. Visit F (8). E = 8 + 1 = 9.
4. Visit E (9). C = 9 + 6 = 15.
5. Visit H (11). D = 11 + 9 = 20. C through H would be 11 + 4 = 15, which is not better than the 15
   it already has, so C stays as it is.
6. Visit C (15). A = 15 + 5 = 20. D through C would be 15 + 7 = 22, worse than 20, so no change.
7. A and D are tied on 20. Visiting D finishes the search, since its distance is now final.
   (Visiting A first makes no difference: D through A would be 20 + 2 = 22.)

Following the "reached from" entries back from D: D ← H ← G ← B.

**Shortest path: B → G → H → D, with a total weight of 3 + 8 + 9 = 20.**

For comparison, the route through the middle of the graph, B → G → F → E → C → D, comes to
3 + 5 + 1 + 6 + 7 = 22.

## Programming: longest common prefix

`findLongestCommonPrefix` starts with the whole first word as the prefix and cuts it back against each
of the other words in turn. If the prefix becomes empty it stops early.

### Build and run

```bash
javac LongestCommonPrefix.java
java LongestCommonPrefix
```

### Sample runs

```
Enter words separated by spaces:
machine marvellous maybe
Longest Common Prefix: ma
```

```
Enter words separated by spaces:
exam study hard
Longest Common Prefix: 
```

### Tests run

| Input | Output | Checks |
|---|---|---|
| `machine marvellous maybe` | `ma` | given example 1 |
| `exam study hard` | (empty string) | given example 2 |
| `single` | `single` | one word |
| `same same same` | `same` | identical words |
| `ab a abc` | `a` | a word shorter than the first one |
| (empty line) | (empty string) | no words |

### Notes

- `findLongestCommonPrefix` is the solution; the rest of the file is provided starter code.
- The comparison is case sensitive, so `May` and `maybe` have no common prefix.
