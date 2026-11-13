# Bubble Sort Integers

Sorts an array of whole numbers with a bubble sort, printing the array after each pass and the total number of swaps. The sort stops early once a pass makes no swaps.

## Run

```bash
javac BubbleSortIntegers.java
java BubbleSortIntegers
```

## Example runs

```
Please input the number of values: 6
Please input the values: 29 10 14 37 13 5
Start:  [29, 10, 14, 37, 13, 5]
Pass 1: [10, 14, 29, 13, 5, 37]
Pass 2: [10, 14, 13, 5, 29, 37]
Pass 3: [10, 13, 5, 14, 29, 37]
Pass 4: [10, 5, 13, 14, 29, 37]
Pass 5: [5, 10, 13, 14, 29, 37]
Sorted with 10 swaps
```

```
Please input the number of values: 5
Please input the values: 1 2 3 5 4
Start:  [1, 2, 3, 5, 4]
Pass 1: [1, 2, 3, 4, 5]
Pass 2: [1, 2, 3, 4, 5]
Sorted with 1 swap
```
