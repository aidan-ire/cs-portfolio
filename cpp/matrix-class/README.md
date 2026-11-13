# CA 3: Matrix class

**Status: complete.** All 22 test cases pass (22 passed, 0 failed).

An integer `Matrix` class that stores its values in a dynamically allocated 2D array and frees the
memory in its destructor.

## What the class supports

- construction from a size (all zeros), from another matrix (deep copy) and from a 2D array
- copy assignment
- `rows()`, `columns()`, `get(i, j)`, `set(i, j, value)` with bounds checking
- `+`, `-`, `*` (standard matrix multiplication), `~` (transpose) and `==`
- `toStr()` for a printable version of the matrix

Out-of-range indexes throw `out_of_range`. A NULL array, or matrices whose sizes don't fit the
operation, throw `invalid_argument`.

## Files

- `Matrix.h` — class definition with a doc comment per method
- `Matrix.cpp` — implementation
- `TestMatrix.cpp` — test cases, one or more per method

## Build and run

```bash
g++ -o testMatrix TestMatrix.cpp Matrix.cpp
./testMatrix
```

Each test prints `PASS` or `FAIL` with its name, followed by a total.
