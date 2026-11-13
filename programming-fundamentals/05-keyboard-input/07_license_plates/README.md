# License Plates

Reads a plate in the form `191-D-12345` and prints the year (the first two digits after `20`), the half of the year (a `1` or `2` as the third digit), the county code and the number. Only the three digit form is handled.

## Run

```bash
javac LicensePlates.java
java LicensePlates
```

## Example runs

```
Please input a license plate (eg. 191-D-12345): 191-D-12345
Year: 2019
Registered: January to June
County code: D
Number: 12345
```

```
Please input a license plate (eg. 191-D-12345): 202-CE-6789
Year: 2020
Registered: July to December
County code: CE
Number: 6789
```

```
Please input a license plate (eg. 191-D-12345): nonsense
Invalid license plate
```
