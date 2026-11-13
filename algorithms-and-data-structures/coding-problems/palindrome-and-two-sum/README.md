# Palindrome and two sum

Two short Java programs.

| File | What it does |
|---|---|
| `palindrome.java` | reads a word and prints whether it is a palindrome, using two indexes moving in from each end |
| `twoSum.java` | finds the two positions in an array whose values add up to a target, using a HashMap of value → index so it only needs one pass |

## Running

```bash
javac palindrome.java && java palindrome
javac twoSum.java && java twoSum
```

`twoSum` uses the array `{2,0,7,8,9,1,5,6}` with target 9 and prints the two indexes, `0` and `2`
(the values 2 and 7).
