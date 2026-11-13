# AVL insertions and minimum depth

Pen and paper: insert ten values into an AVL tree, showing balance factors and rotations.
Programming: find the minimum depth of a binary tree.

## Pen and paper: AVL insertions

Values: 25, 53, 96, 21, 59, 10, 52, 47, 32, 40

The balance factor of a node is **height(left subtree) − height(right subtree)**. A node is balanced
when its factor is -1, 0 or +1. After each insertion the factors are checked going back up from the
new node, and the first node found at +2 or -2 is fixed with a rotation. Balance factors are shown in
square brackets.

**Insert 25, 53** — no rotation.

```
25[-1]
   \
    53[0]
```

**Insert 96** — 25 becomes -2 and the new value went right-right, so a single **left rotation at 25**.

```
25[-2]                    53[0]
   \                     /    \
    53[-1]      ->    25[0]   96[0]
      \
       96[0]
```

**Insert 21, 59** — no rotation.

```
         53[0]
        /     \
    25[+1]    96[+1]
     /         /
  21[0]     59[0]
```

**Insert 10** — goes left of 21. 25 becomes +2 and the new value went left-left, so a single
**right rotation at 25**.

```
           53[+1]                          53[0]
          /      \                        /     \
      25[+2]     96[+1]      ->       21[0]     96[+1]
       /          /                   /   \      /
    21[+1]     59[0]              10[0]  25[0]  59[0]
     /
  10[0]
```

**Insert 52** — goes right of 25. No rotation (21 is -1, 53 is +1).

**Insert 47** — goes left of 52. 25 becomes -2 and the new value went right then left, so a double
rotation: **right rotation at 52, then left rotation at 25**. 47 ends up above 25 and 52.

```
           53[+2]                               53[+1]
          /      \                             /      \
      21[-2]     96[+1]                    21[-1]     96[+1]
      /    \      /                        /    \      /
  10[0]   25[-2] 59[0]        ->       10[0]   47[0]  59[0]
             \                                 /   \
             52[+1]                        25[0]   52[0]
             /
          47[0]
```

**Insert 32** — goes right of 25. 25 is fine at -1, 47 is +1, but 21 becomes -2. The new value is in
the left subtree of 21's right child, so a double rotation: **right rotation at 47, then left rotation
at 21**. 25 ends up above 21 and 47.

```
           53[+2]                                 53[+1]
          /      \                               /      \
      21[-2]     96[+1]                      25[0]      96[+1]
      /    \      /                         /     \      /
  10[0]   47[+1] 59[0]         ->       21[+1]   47[0]  59[0]
          /   \                          /       /   \
      25[-1]  52[0]                   10[0]   32[0]  52[0]
         \
         32[0]
```

**Insert 40** — goes right of 32. 32 is -1, 47 is +1, 25 is -1, but the root 53 becomes +2. The new
value is in the right subtree of 53's left child, so a double rotation: **left rotation at 25, then
right rotation at 53**. 47 becomes the new root.

```
              53[+2]                                  47[0]
             /      \                               /       \
         25[-1]     96[+1]                      25[0]       53[-1]
         /    \      /                         /     \      /    \
     21[+1]  47[+1] 59[0]         ->       21[+1]  32[-1] 52[0]  96[+1]
      /      /   \                          /         \           /
   10[0]  32[-1] 52[0]                   10[0]       40[0]     59[0]
             \
             40[0]
```

**Summary**

| Insert | Unbalanced node | Case | Rotation |
|---|---|---|---|
| 25, 53 | none | | |
| 96 | 25 (-2) | right-right | left at 25 |
| 21, 59 | none | | |
| 10 | 25 (+2) | left-left | right at 25 |
| 52 | none | | |
| 47 | 25 (-2) | right-left | right at 52, left at 25 |
| 32 | 21 (-2) | right-left | right at 47, left at 21 |
| 40 | 53 (+2) | left-right | left at 25, right at 53 |

## Programming: `minDepth()`

The tree class with its recursive insert, and `main`, are provided starter code. Only
`minDepth(Node root)` is the solution. The minimum depth is the number of nodes on the shortest path from the root to a leaf.

A node with only one child has to be handled separately: the empty side is not a leaf, so the path
must continue through the child that exists. Taking the smaller of the two sides without that check
would give 1 for the second example below instead of 5.

### Build and run

```bash
javac BinaryTree.java
java BinaryTree
```

Type integers separated by spaces and finish with a non-number such as `a`.

### Sample runs

```
9 15 3 20 10 a
Minimum Depth of Binary Tree is 2
```

```
1 2 3 4 5 a
Minimum Depth of Binary Tree is 5
```

### Tests run

| Input | Output | Checks |
|---|---|---|
| `9 15 3 20 10 a` | 2 | given example 1 (path 9 → 3) |
| `1 2 3 4 5 a` | 5 | given example 2, a chain where every node has one child |
| `a` | 0 | empty tree |
| `7 a` | 1 | single node |
| `5 3 8 1 4 6 9 a` | 3 | full tree with three levels |

### Notes

- An empty tree returns 0.
