# Animals

An abstract `Animal` class with an abstract `sound` method, so every subclass must supply its own sound. `Dog`, `Cat`, `Cow` and `Hen` do so, `Hen` overrides `legs`, and `Dog` adds a `fetch` method.

## Files

- `Animal.java` — the abstract superclass
- `Dog.java` — subclass
- `Cat.java` — subclass
- `Cow.java` — subclass
- `Hen.java` — subclass with 2 legs
- `TestAnimals.java` — makes each animal speak and counts the legs

## Run

```bash
javac *.java
java TestAnimals
```

## Example run

```
Shep the Dog says Woof
  Shep fetches the ball
Tom the Cat says Meow
Daisy the Cow says Moo
Henrietta the Hen says Cluck
Legs on the farm: 14
```
