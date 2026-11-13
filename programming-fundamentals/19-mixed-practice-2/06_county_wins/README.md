# County Wins

Reads the winning county of each final, one per line, until `done`. It then prints how many wins each county has, most first, with ties in alphabetical order. Counties and their counts are kept in two parallel `ArrayList`s, since the number of counties isn't known in advance. Names are matched regardless of case.

## Run

```bash
javac CountyWins.java
java CountyWins
```

## Example run

```
Enter the winning county of each final, one per line, then "done":
Kerry
Dublin
kerry
Galway
Dublin
KERRY
Cork
Galway
done
Wins per county:
  Kerry      3
  Dublin     2
  Galway     2
  Cork       1
```
