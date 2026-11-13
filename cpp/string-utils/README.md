# String functions

C-string functions written without the built-in C++ string class, plus test cases for each one.

| Function | What it does |
|---|---|
| `length` | number of characters before the `'\0'` |
| `copy` | copies `src` into `dest`, or returns `NULL` if `dest` is not big enough |
| `indexOf` | index of the first occurrence of a character, or -1 |
| `substring` | new string from index `i` up to `j - 1`, or `NULL` if the range is invalid |
| `replace` | new string with every `c` replaced by `p` |

## Files

- `string_utils.cpp` — the five functions, each with a doc comment
- `tests.cpp` — five test cases per function, named `[function]_name_test_case_[k]`
- `main.cpp` — runs every test

## Build and run

```bash
g++ -Wall -o ca2 main.cpp
./ca2
```

`main.cpp` includes `tests.cpp`, which includes `string_utils.cpp`, so only `main.cpp` is compiled.
Each test prints one line in the form `name: expected X, got Y`.

## Notes

- The real size of a `char*` buffer can't be found, so `copy` treats `dest` as big enough only if it
  already holds a string at least as long as `src`.
- `substring` and `replace` return memory allocated with `new[]`; the caller is responsible for
  `delete[]`.
