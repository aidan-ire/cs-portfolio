# Person

A `Person` class and a `Teacher` subclass. `Teacher` overrides `introduce` and `toString`, calling the `Person` versions with `super` and adding to them. The test shows that the overridden method still runs through a `Person` variable.

## Files

- `Person.java` — the superclass
- `Teacher.java` — the subclass
- `TestPerson.java` — uses both

## Run

```bash
javac *.java
java TestPerson
```

## Example run

```
Hi, I'm Orla and I'm 19
Hi, I'm Mr Kavanagh and I'm 45, and I teach Computer Science
Through a Person variable: Hi, I'm Mr Kavanagh and I'm 45, and I teach Computer Science
toString: Orla (19) | Mr Kavanagh (45), teaches Computer Science
Teacher's name from Person: Mr Kavanagh
```
