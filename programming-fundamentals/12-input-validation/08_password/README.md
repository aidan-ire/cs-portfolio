# Password

Asks for a password until it has at least 8 characters, an uppercase letter, a lowercase letter, a digit and no spaces. A rejected password lists every rule it breaks.

## Run

```bash
javac Password.java
java Password
```

## Example run

```
Please choose a password: cat
Password rejected, it needs:
  - at least 8 characters
  - an uppercase letter
  - a digit
Please choose a password: dublin 2026
Password rejected, it needs:
  - an uppercase letter
  - no spaces
Please choose a password: Dublin2026
Password accepted
```
