# Bank Account

A `BankAccount` class whose balance can only change through `deposit` and `withdraw`. Both return `false`, leaving the balance as it was, for a zero or negative amount, or for a withdrawal larger than the balance. `TestBankAccount` is a small menu for using an account.

## Files

- `BankAccount.java` — the class
- `TestBankAccount.java` — a deposit/withdraw/balance menu

## Run

```bash
javac *.java
java TestBankAccount
```

## Example run

```
Opened Sean Murphy (IE-1001): balance 0.00
d = deposit, w = withdraw, b = balance, q = quit: d
Amount to deposit: 250
Deposited 250.00
d = deposit, w = withdraw, b = balance, q = quit: w
Amount to withdraw: 80.50
Withdrew 80.50
d = deposit, w = withdraw, b = balance, q = quit: w
Amount to withdraw: 500
Can't withdraw 500.00, the balance is 169.50
d = deposit, w = withdraw, b = balance, q = quit: d
Amount to deposit: -10
A deposit must be more than 0
d = deposit, w = withdraw, b = balance, q = quit: b
Sean Murphy (IE-1001): balance 169.50
d = deposit, w = withdraw, b = balance, q = quit: q
Closing: Sean Murphy (IE-1001): balance 169.50
```
