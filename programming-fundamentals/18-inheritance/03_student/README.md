# Student

`Student` extends `Person` with a student number and an array of module marks, plus methods for the average and whether the student passes (an average of 40 or more). Out-of-range marks are rejected. The test puts people and students in one `Person[]`.

## Files

- `Person.java` — the superclass
- `Student.java` — the subclass
- `TestStudent.java` — adds marks and prints everyone

## Run

```bash
javac *.java
java TestStudent
```

## Example run

```
Adding a mark of 105 to Ciara: false
Hi, I'm Tom and I'm 52
  Tom (52)
Hi, I'm Ciara and I'm 20, student number 26100123
  Ciara (20), 26100123, 4 marks, average 69.0
Hi, I'm Darragh and I'm 19, student number 26100456
  Darragh (19), 26100456, 3 marks, average 35.0
Ciara passing: true
Darragh passing: false
```
