# Binary Search Game

The user thinks of a number from 1 to 100 and the program guesses it. Each guess is the middle of the numbers still possible, so it never needs more than 7 guesses. Answers other than h, l or c are asked for again, and answers that contradict each other are caught.

## Run

```bash
javac BinarySearchGame.java
java BinarySearchGame
```

## Example runs

```
Think of a whole number from 1 to 100 and I will guess it.
After each guess answer h (higher), l (lower) or c (correct).
Guess 1: is it 50? h
Guess 2: is it 75? l
Guess 3: is it 62? h
Guess 4: is it 68? x
Please answer h, l or c: l
Guess 5: is it 65? c
Got it in 5 guesses!
```

```
Think of a whole number from 1 to 100 and I will guess it.
After each guess answer h (higher), l (lower) or c (correct).
Guess 1: is it 50? l
Guess 2: is it 25? l
Guess 3: is it 12? l
Guess 4: is it 6? l
Guess 5: is it 3? l
Guess 6: is it 1? c
Got it in 6 guesses!
```

```
Think of a whole number from 1 to 100 and I will guess it.
After each guess answer h (higher), l (lower) or c (correct).
Guess 1: is it 50? h
Guess 2: is it 75? l
Guess 3: is it 62? h
Guess 4: is it 68? l
Guess 5: is it 65? h
Guess 6: is it 66? l
Those answers don't add up, there is no number left that fits them.
```
