# Eircode

Checks the format only: a routing key (a letter and two digits, or `D6W`) then four letters or digits, with an optional space between. The routing key is not looked up against the real list.

## Run

```bash
javac Eircode.java
java Eircode
```

## Example runs

```
Please input an Eircode: W23 F2K1
Valid Eircode format
```

```
Please input an Eircode: A65TF12
Valid Eircode format
```

```
Please input an Eircode: W2 3F2K1
Invalid Eircode format
```
