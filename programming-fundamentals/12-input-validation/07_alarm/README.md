# Alarm

Reads the current time as 24-hour `hh:mm` and a number of minutes, then prints when the alarm goes off, wrapping past midnight. Both inputs are checked and asked for again if invalid.

## Run

```bash
javac Alarm.java
java Alarm
```

## Example runs

```
Please input the current time (hh:mm): 7:45
Invalid time, use 24-hour hh:mm, eg. 07:45. Try again: 07:45
Please input the minutes until the alarm: 30
The alarm goes off at 08:15
```

```
Please input the current time (hh:mm): 23:50
Please input the minutes until the alarm: ten
That is not a whole number of minutes. Try again: 25
The alarm goes off at 00:15 the next day
```

```
Please input the current time (hh:mm): 09:00
Please input the minutes until the alarm: 4320
The alarm goes off at 09:00, 3 days later
```
