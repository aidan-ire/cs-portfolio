# Binary search tree

Pen and paper: build a binary search tree from eight numbers, then delete them all. Programming:
fill in the insert, height and traversal methods of a binary tree class.

## Pen and paper

### 1) Insert 84, 55, 32, 14, 28, 40, 94, 25

Each value starts at the root and goes left if it is smaller than the node and right if it is bigger,
until it reaches an empty spot.

| Insert | Path taken |
|---|---|
| 84 | becomes the root |
| 55 | left of 84 |
| 32 | left of 84, left of 55 |
| 14 | left of 84, left of 55, left of 32 |
| 28 | left of 84, left of 55, left of 32, right of 14 |
| 40 | left of 84, left of 55, right of 32 |
| 94 | right of 84 |
| 25 | left of 84, left of 55, left of 32, right of 14, left of 28 |

```
            84
           /  \
         55    94
        /
      32
     /  \
   14    40
     \
      28
     /
    25
```

### 2) Delete 84, 55, 32, 14, 28, 40, 94, 25 in order

There are three cases. A leaf is just removed. A node with one child is replaced by that child. A node
with two children is replaced by its inorder successor (the smallest value in its right subtree), and
the successor is then removed from where it was.

**Delete 84** — two children (55 and 94). The successor is 94, the smallest value in the right
subtree. 94 takes the root's place.

```
          94
         /
       55
      /
    32
   /  \
 14    40
   \
    28
   /
  25
```

**Delete 55** — one child (32). 32 moves up into its place.

```
        94
       /
     32
    /  \
  14    40
    \
     28
    /
   25
```

**Delete 32** — two children (14 and 40). The successor is 40, which is a leaf, so 40 replaces 32.

```
        94
       /
     40
    /
  14
    \
     28
    /
   25
```

**Delete 14** — one child (28). 28 moves up.

```
      94
     /
   40
  /
 28
 /
25
```

**Delete 28** — one child (25). 25 moves up.

```
    94
   /
  40
 /
25
```

**Delete 40** — one child (25). 25 moves up.

```
  94
 /
25
```

**Delete 94** — the root, with one child (25). 25 becomes the root.

```
25
```

**Delete 25** — a leaf, and the last node. The tree is now empty.

## Programming

The `Node` class, the `BinaryTree` class outline and `main` are provided starter code. Four methods are
the solution:

| Method | What it does |
|---|---|
| `addNode` | inserts a value, lower values to the left and higher to the right. A value already in the tree is ignored |
| `getHeight` | height counted in nodes: 0 for an empty tree, 1 for a single node |
| `traverseInorder` | left subtree, node, right subtree (prints the values in ascending order) |
| `traversePreorder` | node, left subtree, right subtree |

### Build and run

```bash
javac BinaryTree.java
java BinaryTree
```

Type integers separated by spaces and finish with a non-number such as `a`.

### Sample run

```
5 3 8 1 4 6 9 a
The height of the binary tree is: 3
Inorder traversal of the binary tree:
1 3 4 5 6 8 9 
Preorder traversal of the binary tree:
5 3 1 4 8 6 9 
```

### Tests run

| Input | Result | Checks |
|---|---|---|
| `5 3 8 1 4 6 9 a` | height 3, inorder `1 3 4 5 6 8 9`, preorder `5 3 1 4 8 6 9` | given example |
| `a` | height 0, both traversals empty | empty tree |
| `4 4 2 a` | height 2, inorder `2 4` | duplicate ignored |
| `1 2 3 4 a` | height 4 | sorted input gives a chain |

### Notes

- Duplicate values are ignored, which matches the rule that the left child is always lower and the
  right child always higher.
