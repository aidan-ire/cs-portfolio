# Flight Codes

Asks for a flight code until it is valid: two uppercase letters followed by 3 or 4 digits, such as `EI123`. Each rejection says what is wrong, and the valid code is split into airline and flight number.

## Run

```bash
javac FlightCodes.java
java FlightCodes
```

## Example run

```
Please input a flight code: ei123
Invalid: the first two characters must be uppercase letters
Please input a flight code: EI12
Invalid: it must be 5 or 6 characters long
Please input a flight code: EI12A
Invalid: the letters must be followed by 3 or 4 digits
Please input a flight code: FR1234
FR1234 is valid, airline FR, flight number 1234
```
