# The dining philosophers

Five philosophers sit round a table with one chopstick between each pair. Each one thinks, gets hungry,
picks up the chopstick on either side, eats and puts them back. Each chopstick is a binary semaphore.
The program given in the practical can deadlock, and the task is to change it in three different ways
so that it can't.

| File | What it does |
|---|---|
| `Semaphore.java` | the semaphore class, unchanged in all versions |
| `DiningPhilosophers.java` | the original simulation, which can deadlock |
| `DiningPhilosophers1.java` | fix 1: at most four philosophers at the table |
| `DiningPhilosophers2.java` | fix 2: picking up both chopsticks is a critical section |
| `DiningPhilosophers3.java` | fix 3: odd and even philosophers pick up in opposite orders |

`Semaphore.java` and `DiningPhilosophers.java` are provided listings. `DiningPhilosophers1.java` to
`DiningPhilosophers3.java` are the solutions.

## Build and run

```bash
javac *.java
java DiningPhilosophers
java DiningPhilosophers1
java DiningPhilosophers2
java DiningPhilosophers3
```

Each one runs until stopped with CTRL-C.

## Why the original can deadlock

Every philosopher picks up the right chopstick and then the left. If all five pick up their right one
before any of them picks up a left one, every chopstick is taken and every philosopher is waiting for
one held by a neighbour. Nobody can eat, so nobody ever puts one down.

Thinking takes up to 20 seconds and the gap between the two pickups is tiny, so in normal runs this
almost never happens. 20 seconds of the original looks healthy:

```
Philosopher 0 thinking.
Philosopher 4 thinking.
Philosopher 2 thinking.
Philosopher 3 thinking.
Philosopher 1 thinking.
Philosopher 3 hungry.
Philosopher 3 eating.
Philosopher 0 hungry.
Philosopher 0 eating.
Philosopher 2 hungry.
Philosopher 3 thinking.
Philosopher 2 eating.
```

The order is different on every run, since it depends on the random sleeps and on the scheduler.

## The three fixes

**1. Four at the table.** A counting semaphore `table`, starting at 4, is acquired before the
chopsticks and released after they are put down. With at most four philosophers holding or reaching for
chopsticks, there are five chopsticks among four people, so at least one of them can get two.

**2. Pick up both or wait.** A binary semaphore `pickUp` is held while a philosopher takes both
chopsticks. Only one philosopher can be partway through picking up, so the "everyone holds one" state
can't form. The philosopher inside may have to wait for a chopstick, but only for one held by somebody
who is eating, and putting chopsticks down doesn't need `pickUp`. The price is less concurrency: while
one philosopher waits inside, the other hungry ones queue behind it even if their own chopsticks are
free.

**3. Asymmetry.** Even-numbered philosophers take right then left, odd-numbered ones take left then
right. That makes philosophers 1 and 2 compete for chopstick 2 as their first, and 3 and 4 compete for
chopstick 4. Whoever loses is waiting with empty hands, so the circle of five can't close. No extra
semaphore is needed.

In each file the philosopher class is renamed to match (`Philosopher1`, `Philosopher2`,
`Philosopher3`). With four files in one directory all defining a class called `Philosopher`, each
compile would overwrite `Philosopher.class` and running one version could pick up another's
philosopher.

## Testing

A deadlock that almost never happens can't be shown by watching the normal programs, so two sets of
scratch copies were made with the timings changed. Thinking and eating times were cut to under 3 ms in
both. The second set also had a 50 ms sleep added between the first and second chopstick, which makes
"everyone holds one" nearly certain in the original. The copies were only for testing and are not part
of the practical. The files here have the original timings.

Fast timings only, 15 seconds each:

| Version | Meals | Per philosopher (0 to 4) |
|---|---|---|
| original | 14594 | 2931, 2925, 2913, 2927, 2898 |
| fix 1 | 16272 | 3261, 3232, 3233, 3276, 3270 |
| fix 2 | 15146 | 3040, 3035, 3040, 3031, 3000 |
| fix 3 | 18535 | 4141, 3729, 3718, 3526, 3421 |

With the 50 ms gap between pickups, 12 seconds each:

| Version | Meals | Per philosopher (0 to 4) | Outcome |
|---|---|---|---|
| original | 0 | 0, 0, 0, 0, 0 | deadlocked at once |
| fix 1 | 833 | 169, 168, 164, 167, 165 | still running |
| fix 2 | 221 | 44, 44, 44, 45, 44 | still running |
| fix 3 | 634 | 212, 109, 109, 102, 102 | still running |

The whole output of the original in the second test was ten lines, five `thinking` and five `hungry`,
and then nothing.

The counts depend on the machine and change from run to run. What they show is that the original
survived the first test only by luck, that all three fixes keep going under conditions that stop the
original immediately, and that every philosopher gets to eat. Fix 2 gets through the fewest meals with
the gap in place, which is the cost of letting only one philosopher pick up at a time. A test run
can't prove a deadlock is impossible. The arguments above are what rule it out.

## Notes

- The three fixes are the usual ones for this problem.
- None of the fixes guarantees fairness. `notify` wakes an arbitrary waiting thread, so a philosopher
  could in principle be passed over repeatedly, though the per-philosopher counts above are close to
  even except for philosopher 0 in fix 3. Philosopher 0 is the only one whose first chopstick is not
  some neighbour's first choice as well.
- Run on macOS with OpenJDK 25.
