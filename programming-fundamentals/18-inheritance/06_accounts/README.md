# Accounts

An `Account` superclass and two subclasses that change how it behaves. `SavingsAccount` adds monthly interest in `monthEnd`. `CurrentAccount` overrides `withdraw` to allow an overdraft up to a limit and charges a fee for each month that ends overdrawn. The test makes the same calls on all three and compares the results.

## Files

- `Account.java` — the superclass, which can't go below 0
- `SavingsAccount.java` — adds interest
- `CurrentAccount.java` — allows an overdraft
- `TestAccounts.java` — runs the same steps on each account

## Run

```bash
javac *.java
java TestAccounts
```

## Example run

```
After depositing 1000 into each:
  Account         Liam     balance  1000.00
  Savings         Emma     balance  1000.00
  Current         Jack     balance  1000.00
Withdrawing 1100 from each:
  Account: refused
  Savings: refused
  Current: allowed
After 3 month ends (interest for savings, overdraft fees for current):
  Account         Liam     balance  1000.00
  Savings         Emma     balance  1006.01
  Current         Jack     balance  -115.00
```
