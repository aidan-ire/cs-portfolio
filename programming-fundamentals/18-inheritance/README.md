# Inheritance

Subclasses that reuse a superclass with `extends` and `super`, override its methods, and are used through superclass references (polymorphism), plus an abstract class. Each folder is self-contained: the classes, a `Test…` class with a `main`, and a short README with how to run it and an example run.

| Exercise | Folder | What it does |
|---|---|---|
| 1. Squares | [01_squares](01_squares/) | `Square extends Rectangle`, kept with rectangles in one array. |
| 2. Person | [02_person](02_person/) | `Teacher extends Person`, overriding `introduce` and `toString` and calling the `super` versions. |
| 3. Student | [03_student](03_student/) | `Student extends Person` with a student number, marks, an average and a pass check. |
| 4. Vehicles | [04_vehicles](04_vehicles/) | `Car`, `Motorbike` and `Truck` extend `Vehicle`, each overriding its type and wheel count. |
| 5. Animals | [05_animals](05_animals/) | An abstract `Animal` with an abstract `sound`, implemented by four animals. |
| 6. Accounts | [06_accounts](06_accounts/) | Savings and current accounts that change how withdrawing and month end work. |
