# Sorting algorithms

Three tasks on bubble sort, insertion sort and selection sort: count the swaps made when sorting 1000
integers, change one of the algorithms to sort strings, and sort an array of `Customer` objects in
three different ways.

## Build and run

```bash
javac *.java
java SortIntegers
java SortStrings
java SortCustomers
```

## Task 1: counting swaps

`SortIntegers` fills an array with 1000 random integers from 0 to 9999 and gives each algorithm its
own copy of it. Bubble sort and selection sort add one to a counter every time they swap two elements.

```
n = 1000
Bubble sort:    sorted = true, swaps = 250055
Insertion sort: sorted = true, no swaps
Selection sort: sorted = true, swaps = 992
```

| Algorithm | Swaps for n = 1000 |
|---|---|
| Bubble sort | 250,055 |
| Selection sort | 992 |
| Insertion sort | none, it shifts elements instead |

There is a very big difference: bubble sort makes about 250 times more swaps. Bubble sort only ever
swaps neighbours, so each swap moves an element one place and fixes exactly one pair that was in the
wrong order. A random array has about n(n − 1) / 4 such pairs, which is 249,750 for n = 1000, and the
count is right beside that. Selection sort does all of its searching first and then makes a single
swap per pass that puts the smallest remaining element straight into its final position, so it can
never make more than n − 1 = 999 swaps. It made 992 because in 7 passes the smallest element was
already in place. Both still make about n² / 2 comparisons, so both are O(n²), but selection sort
writes to the array far less often.

## Task 2: sorting strings

`SortStrings` is insertion sort with `String` in place of `int`. The only real change is the
comparison, which uses `compareTo` since `>` doesn't work on strings.

```
Before:
pear apple fig banana cherry apple date Kiwi 
After:
Kiwi apple apple banana cherry date fig pear 
```

`compareTo` goes by character codes, so a word starting with a capital comes before every lowercase
word.

## Task 3: sorting customers

`Customer.java` is the class from the lab. `SortCustomers` uses insertion sort again with a
different comparison for each ordering. Whole `Customer` objects are moved, so each name stays with its
surname and age.

```
Sorted by first name:
Adam Smith (51)
Adam Reed (32)
Alice Brown (25)
Bob Adams (28)
Bob Gates (20)
Carl English (29)
Diana Clark (35)
Diana Davis (22)
John Smith (30)
Mary Walsh (18)

Sorted by age:
Mary Walsh (18)
Bob Gates (20)
Diana Davis (22)
Alice Brown (25)
Bob Adams (28)
Carl English (29)
John Smith (30)
Adam Reed (32)
Diana Clark (35)
Adam Smith (51)

Sorted by first name, then surname:
Adam Reed (32)
Adam Smith (51)
Alice Brown (25)
Bob Adams (28)
Bob Gates (20)
Carl English (29)
Diana Clark (35)
Diana Davis (22)
John Smith (30)
Mary Walsh (18)
```

In the first ordering the two Adams are in the order they were in the original array (Smith, then
Reed), because insertion sort is stable and only the first name is compared. The third ordering uses
the surname to decide between them, which puts Reed first.

## Notes

- `SortIntegers.java` has the same three algorithms in their standard form with the swap counters added.
- The random array uses a fixed seed so the swap counts above are the same on every run. A different
  seed gives a bubble sort count that is still close to 250,000.
- Selection sort skips the swap when the smallest element is already in position. A version that
  always swaps would report exactly n − 1 = 999.
