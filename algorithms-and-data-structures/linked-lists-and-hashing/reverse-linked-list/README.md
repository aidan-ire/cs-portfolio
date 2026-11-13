# Reverse a linked list

Two parts: diagrams of three kinds of linked list, and a Java method that reverses a singly linked
list.

## Pen and paper: three kinds of list

`first` and `last` are references held by the list object. `next` and `prev` are references held by
each link.

| List | FIRST | LAST | NEXT | PREV |
|---|---|---|---|---|
| Single-ended singly linked | yes | no | yes | no |
| Double-ended singly linked | yes | yes | yes | no |
| Double-ended doubly linked | yes | yes | yes | yes |

**1) Single-ended singly linked list**

```
first
  |
  v
+---+      +---+      +---+
| A |--next->| B |--next->| C |--next-> null
+---+      +---+      +---+
```

Only the front of the list can be reached directly. Getting to the end means following `next` from
`first`.

**2) Double-ended singly linked list**

```
first                   last
  |                       |
  v                       v
+---+      +---+      +---+
| A |--next->| B |--next->| C |--next-> null
+---+      +---+      +---+
```

Same links as before, but the list also keeps `last`, so inserting at the end is O(1). The links still
only point forwards, so deleting the last link still needs a walk from `first`.

**3) Double-ended doubly linked list**

```
first                             last
  |                                 |
  v                                 v
        +---+ --next-> +---+ --next-> +---+
null <--| A |          | B |          | C |--> null
 (prev) +---+ <-prev-- +---+ <-prev-- +---+ (next)
```

Every link has both `next` and `prev`. The first link's `prev` and the last link's `next` are null.
The list can be walked in either direction, and insert or delete is O(1) at both ends.

## Programming: `reverse()`

`Link` and `LinkedList` are the provided starter classes; `reverse(LinkedList list)` is the solution.

The method walks the list once and turns each `next` pointer around, keeping three references:
`prev` (the part already reversed), `current` (the link being changed) and `next` (the rest of the
list). At the end `first` is set to the old last link. It runs in O(n) time and uses O(1) extra space.

### Build and run

```bash
javac Lab1.java
java Lab1
```

Type integers separated by spaces and finish with any non-number, such as `a`. The program prints the
list, then the reversed list.

### Sample runs

```
Please enter integers (separated by spaces, ending with 'a'
1 2 3 4 5 a
1 2 3 4 5 
5 4 3 2 1 
```

```
Please enter integers (separated by spaces, ending with 'a'
1 2 a
1 2 
2 1 
```

### Tests run

| Input | Reversed output | Checks |
|---|---|---|
| `1 2 3 4 5 a` | `5 4 3 2 1` | given example 1 |
| `1 2 a` | `2 1` | given example 2 |
| `10 20 30 a` | `30 20 10` | the example in the code comments |
| `7 a` | `7` | single link |
| `a` | (empty line) | empty list |
| `-3 0 3 3 a` | `3 3 0 -3` | negatives, zero and duplicates |

### Notes

- The values are printed separated by spaces.
