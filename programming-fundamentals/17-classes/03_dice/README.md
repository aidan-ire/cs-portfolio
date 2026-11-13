# Dice

A `Dice` class for a die with any number of sides (six by default) that uses `java.util.Random` to roll. The test rolls a pair of dice, then rolls one die 60,000 times to show each face comes up about a sixth of the time. Results differ on every run.

## Files

- `Dice.java` — the class
- `TestDice.java` — rolls dice and counts how often each face comes up

## Run

```bash
javac *.java
java TestDice
```

## Example run

```
Rolling two dice 5 times:
  2 + 3 = 5
  6 + 6 = 12  doubles!
  3 + 5 = 8
  1 + 2 = 3
  1 + 3 = 4
Rolling one die 60000 times:
  1: 9883 (16.5%)
  2: 10037 (16.7%)
  3: 10076 (16.8%)
  4: 9847 (16.4%)
  5: 10060 (16.8%)
  6: 10097 (16.8%)
A 20-sided die rolled: 13
```
