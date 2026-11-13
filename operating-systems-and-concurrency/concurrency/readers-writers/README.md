# Readers and writers

Threads share a data item. Any number of readers may use it at the same time, but a writer needs it
to itself. The practical asks for a multithreaded simulation under two policies: readers first (no
reader waits unless a writer is already writing) and then writers first.

| File | What it does |
|---|---|
| `Semaphore.java` | the semaphore class from the earlier practicals |
| `DataAccessPolicyManager.java` | read and write locks for the first policy, readers first |
| `DataAccessPolicyManager2.java` | the second policy, writers first |
| `Reader.java` | thread that repeatedly sleeps, then reads |
| `Writer.java` | thread that repeatedly sleeps, then writes |
| `ReadersWritersSimulation.java` | creates one manager and the reader and writer threads |

`Semaphore.java` and `DataAccessPolicyManager.java` are provided listings. `Reader.java` and
`Writer.java` complete the provided outlines. `ReadersWritersSimulation.java` and
`DataAccessPolicyManager2.java` are the solutions.

## Build and run

```bash
javac *.java
java ReadersWritersSimulation
java ReadersWritersSimulation 5 2 2
```

The arguments are the number of readers, the number of writers and the policy (1 or 2). All three are
optional and default to 3 readers, 2 writers, policy 1. It runs until stopped with CTRL-C.

## Reader and Writer

Each thread loops for ever: sleep for up to 5 seconds, ask for the lock, read or write for up to 3
seconds, release the lock. It prints a line at each stage. `is reading` is printed after the lock has
been acquired and `has finished reading` before it is released, so the output shows exactly who was
inside at any moment.

The constructors keep the one parameter of the provided outlines. Threads are told apart by name: `main`
calls `setName("Reader 1")` and so on, and the threads print `getName()`.

## Sample run (policy 1)

```
Policy 1 (readers first): 3 readers, 2 writers
Reader 3 is sleeping
Reader 1 is sleeping
Reader 2 is sleeping
Writer 1 is sleeping
Writer 2 is sleeping
Writer 2 wants to write
Writer 2 is writing
Writer 2 has finished writing
Writer 2 is sleeping
Reader 1 wants to read
Reader 1 is reading
Reader 3 wants to read
Reader 3 is reading
Writer 2 wants to write
Reader 1 has finished reading
Reader 1 is sleeping
Reader 2 wants to read
Reader 2 is reading
Writer 1 wants to write
Reader 2 has finished reading
Reader 2 is sleeping
```

Readers 1 and 3 are reading together. Writer 2 asks to write while they are, and has to wait. Reader 2
then arrives after Writer 2 and is let straight in ahead of it. That is the first policy: as long as
readers keep arriving, a waiting writer keeps waiting.

## The second policy

`DataAccessPolicyManager2` adds a gate in front of the readers, a semaphore called `readTry`.

- A reader takes `readTry`, does the same counting as in the first policy, and releases `readTry`
  again immediately. Readers still share the data with each other.
- Writers are counted too. The first writer to arrive takes `readTry` and it is only released when the
  last waiting writer has finished. While it is held, new readers are stopped at the gate.
- Readers that were already reading finish normally, then the writer gets `wrt`. Writers still go one
  at a time.

The class extends `DataAccessPolicyManager` and overrides its four methods. That way `Reader` and
`Writer`, whose `lockManager` field has the first class's type, work with either policy unchanged and
the simulation picks one from its third argument, instead of having a second copy of each class with
the name replaced.

## Testing

A script read the output line by line, kept track of who was reading and writing, and counted
any moment where a writer was inside together with anyone else. It also counted reads that began while
some writer had already printed `wants to write`.

Normal timings, 25 seconds, 3 readers and 2 writers:

| Policy | Output lines | Violations | Most readers at once | Reads begun while a writer was waiting |
|---|---|---|---|---|
| 1 | 81 | 0 | 3 | 8 |
| 2 | 86 | 0 | 3 | 0 |

Scratch copies with the sleeps cut to 20 ms and 10 ms, 15 seconds, 5 readers and 2 writers:

| Policy | Output lines | Violations | Most readers at once | Reads | Writes | Reads begun while a writer was waiting |
|---|---|---|---|---|---|---|
| 1 | 19045 | 0 | 5 | 3823 | 937 | 2245 |
| 2 | 17579 | 0 | 5 | 3052 | 1341 | 93 |

Neither policy ever let a writer in with anyone else, and both let all the readers in together. Under
policy 2 the writers got about 40% more writes done and reads overtaking a waiting writer almost
disappeared.

The 93 that remain are a limit of measuring from the output: a writer prints `wants to write` just
before it calls `acquireWriteLock`, and a reader that is already through the gate at that moment
prints `is reading` just after. The numbers depend on the machine and vary between runs.

Other inputs: a non-numeric argument or a policy other than 1 or 2 prints a usage line and stops.
`0 2 2` runs with writers only.

## Notes

- Policy 1 can starve writers and policy 2 can starve readers. Neither is fair, each just chooses who
  is allowed to be kept waiting.
- "Priority" for writers here means no new reader starts while a writer is waiting. Among the threads
  queued on one semaphore the order is not controlled, because `notify` wakes an arbitrary one.
- The second policy is the standard semaphore solution to the second readers/writers problem.
- Run on macOS with OpenJDK 25.
