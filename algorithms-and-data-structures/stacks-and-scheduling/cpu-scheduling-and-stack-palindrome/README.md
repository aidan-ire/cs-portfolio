# Lab 5: CPU scheduling and stacks

Mostly pen and paper: Gantt charts and performance metrics for FCFS, SJF, SRTF, Round Robin and
priority scheduling, with a short comparison of the algorithms. The programming task checks whether a
string is a palindrome using `java.util.Stack`.

The metrics used throughout:

- **Turnaround time (TAT)** = completion time − arrival time
- **Waiting time (WT)** = turnaround time − burst time
- **Response time (RT)** = time first scheduled − arrival time

When two processes tie, the one that arrived first goes first.

## Exercise 1: FCFS and SJF

| Process | Arrival | Burst |
|---|---|---|
| P1 | 0 | 8 |
| P2 | 1 | 4 |
| P3 | 2 | 9 |
| P4 | 3 | 5 |

### FCFS

Processes run in arrival order.

```
|   P1   |  P2  |    P3    |  P4  |
0        8      12         21     26
```

| Process | Completion | TAT | WT | RT |
|---|---|---|---|---|
| P1 | 8 | 8 | 0 | 0 |
| P2 | 12 | 11 | 7 | 7 |
| P3 | 21 | 19 | 10 | 10 |
| P4 | 26 | 23 | 18 | 18 |
| **Average** | | **15.25** | **8.75** | **8.75** |

### SJF (non-preemptive)

Only P1 has arrived at time 0, so it runs first. When it finishes at 8 the other three are all waiting
and are taken shortest first: P2 (4), P4 (5), P3 (9).

```
|   P1   |  P2  |  P4  |    P3    |
0        8      12     17         26
```

| Process | Completion | TAT | WT | RT |
|---|---|---|---|---|
| P1 | 8 | 8 | 0 | 0 |
| P2 | 12 | 11 | 7 | 7 |
| P3 | 26 | 24 | 15 | 15 |
| P4 | 17 | 14 | 9 | 9 |
| **Average** | | **14.25** | **7.75** | **7.75** |

### Comparison

SJF gives the better average waiting time, 7.75 against 8.75. The only difference between the two
schedules is that P4 (burst 5) goes ahead of P3 (burst 9). P4 waits 9 less and P3 waits 5 more, so the
total drops by 4. Putting a short job before a long one always costs the long job less than it saves
the short one, which is why picking the shortest waiting job each time keeps the average waiting time low.
The price is that the longest job, P3, finishes last.

## Exercise 2: non-preemptive and preemptive SJF

| Process | Arrival | Burst |
|---|---|---|
| P1 | 0 | 7 |
| P2 | 2 | 4 |
| P3 | 4 | 1 |
| P4 | 5 | 4 |

### SJF (non-preemptive)

P1 is alone at time 0 and runs to 7. By then P2, P3 and P4 have all arrived. P3 is shortest, then P2
and P4 tie on 4 and P2 arrived first.

```
|  P1   |P3|  P2  |  P4  |
0       7  8      12     16
```

| Process | Completion | TAT | WT | RT |
|---|---|---|---|---|
| P1 | 7 | 7 | 0 | 0 |
| P2 | 12 | 10 | 6 | 6 |
| P3 | 8 | 4 | 3 | 3 |
| P4 | 16 | 11 | 7 | 7 |
| **Average** | | **8.00** | **4.00** | **4.00** |

### SRTF (preemptive SJF)

The scheduler looks again every time a process arrives and runs whichever has the least time left.

- t = 0: only P1 (7 left).
- t = 2: P2 arrives with 4, P1 has 5 left, so P2 takes over.
- t = 4: P3 arrives with 1, P2 has 2 left, so P3 takes over.
- t = 5: P3 is done. P4 arrives with 4. P2 has 2 left and is the shortest.
- t = 7: P2 is done. P4 (4) is shorter than P1 (5).
- t = 11: P4 is done and P1 finishes its last 5.

```
| P1 | P2 |P3| P2 |  P4  |  P1   |
0    2    4  5    7      11      16
```

| Process | Completion | TAT | WT | RT |
|---|---|---|---|---|
| P1 | 16 | 16 | 9 | 0 |
| P2 | 7 | 5 | 1 | 0 |
| P3 | 5 | 1 | 0 | 0 |
| P4 | 11 | 6 | 2 | 2 |
| **Average** | | **7.00** | **3.00** | **0.50** |

### Which process benefits most?

| Process | WT without preemption | WT with preemption | Change |
|---|---|---|---|
| P1 | 0 | 9 | +9 |
| P2 | 6 | 1 | −5 |
| P3 | 3 | 0 | −3 |
| P4 | 7 | 2 | −5 |

P2 and P4 gain the most in absolute terms, 5 time units each, and P2 is the one that gains directly
from preempting: it starts the moment it arrives and its turnaround halves from 10 to 5. P3 is the
only process that ends up with no waiting at all, finishing one time unit after it arrives. They
benefit because they are short jobs that arrive while the long job P1 is running, and without
preemption they are stuck behind it until time 7. P1 pays for all of it, going from no waiting to
waiting 9.

## Exercise 3: Round Robin

| Process | Arrival | Burst |
|---|---|---|
| P1 | 0 | 10 |
| P2 | 0 | 4 |
| P3 | 1 | 5 |

### Round Robin, quantum = 3

A process that uses up its quantum goes to the back of the ready queue. P3 arrives at time 1, during
P1's first slice, so it is already in the queue behind P2 when P1 is put back.

| Time | Runs | Left after | Ready queue after |
|---|---|---|---|
| 0–3 | P1 | 7 | P2, P3, P1 |
| 3–6 | P2 | 1 | P3, P1, P2 |
| 6–9 | P3 | 2 | P1, P2, P3 |
| 9–12 | P1 | 4 | P2, P3, P1 |
| 12–13 | P2 | 0 | P3, P1 |
| 13–15 | P3 | 0 | P1 |
| 15–19 | P1 | 0 | empty |

P1's last two slices (15–18 and 18–19) are shown as one block since nothing else is left to run.

```
| P1 | P2 | P3 | P1 |P2| P3 |  P1  |
0    3    6    9    12 13   15     19
```

| Process | Completion | TAT | WT | RT |
|---|---|---|---|---|
| P1 | 19 | 19 | 9 | 0 |
| P2 | 13 | 13 | 9 | 3 |
| P3 | 15 | 14 | 9 | 5 |
| **Average** | | **15.33** | **9.00** | **2.67** |

### FCFS on the same processes

```
|    P1    |  P2  |  P3   |
0          10     14      19
```

| Process | Completion | TAT | WT | RT |
|---|---|---|---|---|
| P1 | 10 | 10 | 0 | 0 |
| P2 | 14 | 14 | 10 | 10 |
| P3 | 19 | 18 | 13 | 13 |
| **Average** | | **14.00** | **7.67** | **7.67** |

### Comparison and the effect of the quantum

Round Robin is worse on average turnaround (15.33 against 14.00) and waiting time (9.00 against
7.67), but much better on response time (2.67 against 7.67): every process gets the CPU within 5 time
units, where under FCFS P3 waits 13 before it runs at all. It is also fairer, as all three processes
wait exactly 9.

The quantum decides where Round Robin sits between those two. With a large quantum every process
finishes inside its first slice and Round Robin turns into FCFS (a quantum of 10 gives exactly the
FCFS table above). A small quantum gives quick responses but more switching between processes, and
each switch costs time in a real system, which these tables don't count. Working the same processes
with other quanta gives:

| Quantum | Avg TAT | Avg WT | Avg RT |
|---|---|---|---|
| 1 | 14.33 | 8.00 | 0.67 |
| 2 | 14.33 | 8.00 | 1.67 |
| 3 | 15.33 | 9.00 | 2.67 |
| 5 | 13.67 | 7.33 | 4.33 |
| 10 | 14.00 | 7.67 | 7.67 |

Response time gets steadily worse as the quantum grows. Turnaround and waiting time don't move in one
direction, they depend on how the quantum lines up with the burst lengths.

## Exercise 4: priority scheduling

Lower number = higher priority.

| Process | Arrival | Burst | Priority |
|---|---|---|---|
| P1 | 0 | 5 | 2 |
| P2 | 1 | 3 | 1 |
| P3 | 2 | 8 | 4 |
| P4 | 3 | 6 | 3 |

### Non-preemptive priority

P1 is the only process at time 0, and since there is no preemption it runs to 5 even though the
higher priority P2 arrives at 1. After that all three others are waiting and go in priority order:
P2 (1), P4 (3), P3 (4).

```
|  P1  | P2 |   P4   |    P3    |
0      5    8        14         22
```

| Process | Completion | TAT | WT | RT |
|---|---|---|---|---|
| P1 | 5 | 5 | 0 | 0 |
| P2 | 8 | 7 | 4 | 4 |
| P3 | 22 | 20 | 12 | 12 |
| P4 | 14 | 11 | 5 | 5 |
| **Average** | | **10.75** | **5.25** | **5.25** |

### Starvation and aging

P3 has the lowest priority and runs last here, but it does run because no new processes arrive. In a
system where higher priority processes keep arriving, a low priority process can be passed over every
time the scheduler picks and never get the CPU. That is starvation.

Aging fixes it by raising the priority of a process the longer it waits, for example by lowering its
priority number by 1 for every so many time units spent in the ready queue. A process that has waited
long enough ends up with the highest priority in the queue, so it is guaranteed to run eventually no
matter how low it started.

## Exercise 5: comparison and discussion

| Algorithm | Advantage | Disadvantage | Where it might be used |
|---|---|---|---|
| FCFS | simple and fair in arrival order, no starvation | one long job at the front holds up every short job behind it (convoy effect) | a print queue |
| SJF (non-preemptive) | low average waiting time, short jobs are not held up by long ones that are waiting | burst times have to be known or estimated, and long jobs can starve | overnight batch jobs whose running times are known in advance |
| SJF preemptive (SRTF) | lowest average waiting time overall, short jobs get through quickly | more context switches, and long jobs can starve if short ones keep arriving | a server handling a mix of quick requests and long ones, where the quick ones shouldn't wait |
| Round Robin | good response time and every process gets a fair share | higher average turnaround, and the overhead of switching if the quantum is small | time sharing on a desktop or multi-user system |
| Priority | important work runs first | low priority processes can starve unless aging is used | operating systems putting system and real-time tasks ahead of background work |

**Many interactive users with short tasks.** Round Robin. What matters to someone at a keyboard is how
quickly the system reacts, and Round Robin gives every process a turn within a bounded time (at most
quantum × number of processes ahead of it). Short tasks finish in one or two quanta and nobody gets
stuck behind a long job. Exercise 3 shows this: the average response time was 2.67 against 7.67 for
FCFS.

**Batch system with long, CPU-heavy jobs.** Nobody is waiting on a response, so what matters is
getting the work through with as little overhead as possible, which means a non-preemptive algorithm.
SJF is the most efficient if the running times are known, as it gives the lowest average waiting and
turnaround time. FCFS is the fallback when they aren't, since it has the least overhead and long jobs
running to completion keep context switches to a minimum. Round Robin would be a poor choice here: it
adds switching and makes every job finish later.

## Exercise 6: checking the answers

Every Gantt chart and table above was checked against a separate simulation of each algorithm, and
all the values matched.

## Exercise 7: palindrome with a stack

`StackPalindrome.java` pushes each character of the string onto a `Stack<Character>`, then pops them
all off to build the reversed string. The string is a palindrome if the reversed string equals the
original.

### Build and run

```bash
javac StackPalindrome.java
java StackPalindrome
```

### Sample runs

```
Please input a string: racecar
Reversed: racecar
racecar is a palindrome
```

```
Please input a string: hello
Reversed: olleh
hello is not a palindrome
```

### Tests run

| Input | Reversed | Result |
|---|---|---|
| `madam` | `madam` | palindrome |
| `racecar` | `racecar` | palindrome |
| `12321` | `12321` | palindrome |
| `a` | `a` | palindrome |
| (empty) | (empty) | palindrome |
| `hello` | `olleh` | not a palindrome |
| `ab` | `ba` | not a palindrome |
| `Madam` | `madaM` | not a palindrome, the comparison is case sensitive |
| `never odd or even` | `neve ro ddo reven` | not a palindrome, spaces are compared too |

### Notes

- The string is compared exactly as typed. Capitals and spaces are not ignored.
