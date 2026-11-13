# License Plates 2

Reads a plate like `191-D-12345` and finds its county code in an array of codes, printing the county at the same position in a second array. It covers the 25 county codes in use since 2014.

## Run

```bash
javac LicensePlates2.java
java LicensePlates2
```

## Example runs

```
Please input a license plate (eg. 191-D-12345): 191-D-12345
Year: 2019
County: Dublin
```

```
Please input a license plate (eg. 191-D-12345): 201-WX-77
Year: 2020
County: Wexford
```

```
Please input a license plate (eg. 191-D-12345): 191-XX-1
Unknown county code: XX
```

```
Please input a license plate (eg. 191-D-12345): bad
Invalid license plate
```
