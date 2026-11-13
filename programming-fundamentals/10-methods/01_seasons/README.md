# Seasons

Reads a month number and prints its season using the meteorological seasons: winter is December to February, spring March to May, summer June to August and autumn September to November. The lookup is done in a `getSeason` method, and a number outside 1–12 is rejected.

## Run

```bash
javac Seasons.java
java Seasons
```

## Example runs

```
Please input a month number (1-12): 4
Month 4 is in Spring
```

```
Please input a month number (1-12): 12
Month 12 is in Winter
```

```
Please input a month number (1-12): 13
13 is not a valid month
```
