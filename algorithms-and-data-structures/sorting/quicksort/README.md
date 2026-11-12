# Lab 8: quicksort

Pen and paper: trace quicksort on nine numbers, identifying the pivots and swaps. Programming: sort
an array with a hand-written quicksort.

## Pen and paper

Numbers: 63 27 77 28 11 85 79 24 60

This uses the version from the lectures: the **rightmost element is the pivot**. A left scan moves
right looking for a value bigger than the pivot, a right scan moves left looking for a value smaller
than the pivot, and the two are swapped. When the scans cross, the pivot is swapped with the element
where the left scan stopped, which puts it in its final position. Pivots are shown in **bold** and
values already in their final position in *italics*.

### Range 0–8, pivot 60

| Step | Array | What happened |
|---|---|---|
| start | 63 27 77 28 11 85 79 24 **60** | |
| swap | 24 27 77 28 11 85 79 63 **60** | left scan stops at 63, right scan stops at 24, swap 63 ↔ 24 |
| swap | 24 27 11 28 77 85 79 63 **60** | left scan stops at 77, right scan stops at 11, swap 77 ↔ 11 |
| pivot | 24 27 11 28 *60* 85 79 63 77 | left scan stops at 77, right scan stops at 28, scans have crossed, swap 77 ↔ 60 |

60 is in its final position. Left part: 24 27 11 28. Right part: 85 79 63 77.

### Range 0–3 (24 27 11 28), pivot 28

| Step | Array | What happened |
|---|---|---|
| start | 24 27 11 **28** | |
| pivot | 24 27 11 *28* | nothing is bigger than 28, so the left scan runs all the way to the pivot. No swap is needed |

Left part: 24 27 11. Right part: empty.

### Range 0–2 (24 27 11), pivot 11

| Step | Array | What happened |
|---|---|---|
| start | 24 27 **11** | |
| pivot | *11* 27 24 | left scan stops at 24 straight away, nothing is smaller than 11, swap 24 ↔ 11 |

Left part: empty. Right part: 27 24.

### Range 1–2 (27 24), pivot 24

| Step | Array | What happened |
|---|---|---|
| start | 27 **24** | |
| pivot | *24* 27 | left scan stops at 27, swap 27 ↔ 24 |

27 is on its own, so it is in place. The left half is now 11 24 27 28.

### Range 5–8 (85 79 63 77), pivot 77

| Step | Array | What happened |
|---|---|---|
| start | 85 79 63 **77** | |
| swap | 63 79 85 **77** | left scan stops at 85, right scan stops at 63, swap 85 ↔ 63 |
| pivot | 63 *77* 85 79 | left scan stops at 79, right scan stops at 63, scans have crossed, swap 79 ↔ 77 |

Left part: 63 (on its own, in place). Right part: 85 79.

### Range 7–8 (85 79), pivot 79

| Step | Array | What happened |
|---|---|---|
| start | 85 **79** | |
| pivot | *79* 85 | left scan stops at 85, swap 85 ↔ 79 |

### Result

```
11 24 27 28 60 63 77 79 85
```

| | |
|---|---|
| Pivots, in order | 60, 28, 11, 24, 77, 79 |
| Swaps between the two scans | 63 ↔ 24, 77 ↔ 11, 85 ↔ 63 |
| Swaps that place a pivot | 77 ↔ 60, 24 ↔ 11, 27 ↔ 24, 79 ↔ 77, 85 ↔ 79 |
| Total swaps | 8 |

## Programming

`recQuickSort` sorts the array in place. `Arrays.sort()` is not used.

The trace above uses the rightmost element as the pivot. That choice has a bad case: on input that is
already sorted, one side of every partition is empty, so the recursion goes n levels deep and the sort
takes O(n²) time. The input can be up to 100,000 elements, so the program picks its pivot with
**median-of-three** instead (the median of the first, middle and last elements), which splits sorted
input evenly. Ranges of three or fewer elements are sorted directly.

| Method | What it does |
|---|---|
| `recQuickSort` | picks a pivot, partitions, then sorts each side |
| `medianOf3` | orders the first, middle and last elements and parks the median at `right - 1` |
| `partitionIt` | the two-scan partition, returns the pivot's final index |
| `manualSort` | sorts ranges of 1 to 3 elements |
| `swap` | swaps two positions |

### Build and run

```bash
javac Lab8_Tuesday.java
java Lab8_Tuesday
```

Enter the number of elements, then the elements separated by spaces.

### Sample run

```
Enter number of elements: 9
Enter the numbers:
63 27 77 28 11 85 79 24 60 a
Sorted array:
11 24 27 28 60 63 77 79 85 
```

### Tests run

| Input | Result | Checks |
|---|---|---|
| sample input (9 numbers) | `11 24 27 28 60 63 77 79 85` | given example |
| `1` / `5` | `5` | single element |
| `4` / `3 3 3 3` | `3 3 3 3` | all equal |
| `6` / `-1000000000 1000000000 0 -5 5 0` | `-1000000000 -5 0 0 5 1000000000` | the limits of the allowed range |
| 480 random arrays, sizes 0 to 11, values -5 to 5 | all match Python's `sorted` | small sizes and many duplicates |
| 100,000 elements: random, sorted, reversed, all equal, four distinct values, rising then falling | all match Python's `sorted`, about 0.2 s each | the upper size limit and the bad cases for a rightmost pivot |

### Notes

- `main` is provided starter code; `recQuickSort` and its helper methods are the solution.
- The sample input ends with an `a`. The program reads exactly n numbers, so anything after them is
  ignored.
