# Valid Age

Asks for an age until it gets a whole number from 0 to 120. Each line is checked character by character before it is converted, so text, decimals and negative numbers are all rejected without an exception.

## Run

```bash
javac ValidAge.java
java ValidAge
```

## Example run

```
Please input your age: twenty
"twenty" is not a valid age, it must be a whole number from 0 to 120
Please input your age: -5
"-5" is not a valid age, it must be a whole number from 0 to 120
Please input your age: 150
"150" is not a valid age, it must be a whole number from 0 to 120
Please input your age: 19.5
"19.5" is not a valid age, it must be a whole number from 0 to 120
Please input your age: 19
Age accepted: 19
```
