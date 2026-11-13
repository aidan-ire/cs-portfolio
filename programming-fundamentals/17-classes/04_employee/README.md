# Employee

An `Employee` class holding a name, an ID and a yearly salary, with a percentage `giveRaise` method and a monthly pay calculation. The test keeps three employees in an array, gives raises and totals the pay.

## Files

- `Employee.java` — the class
- `TestEmployee.java` — works with an array of employees

## Run

```bash
javac *.java
java TestEmployee
```

## Example run

```
Staff:
  #101 Niamh Byrne, salary 42000.00
  #102 Ciaran Walsh, salary 38500.00
  #103 Aoife Doyle, salary 51250.00
Giving everyone a 3% raise and Ciaran an extra 5%
  #101 Niamh Byrne, salary 43260.00
  #102 Ciaran Walsh, salary 41637.75
  #103 Aoife Doyle, salary 52787.50
Total yearly pay: 137685.25
Aoife's monthly pay: 4398.96
```
