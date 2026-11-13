# Monte Carlo simulation

Three probabilities estimated by simulation: exactly 7 heads in 10 coin flips, a straight flush in a
5-card poker hand, and a 1D random walk being back at its starting point after 20 steps. Each program
runs N random trials, counts the successes and prints successes / N beside the exact answer.

## Build and run

```bash
javac *.java
java CoinFlip
java StraightFlush
java RandomWalk
```

The programs use an unseeded `Random`, so the estimates are different on every run. The tables below
are from one run of each.

## Task 1: exactly 7 heads in 10 flips

One trial is 10 calls to `rand.nextBoolean()`, and it is a success if exactly 7 are true.

Exact value: C(10,7) / 2^10 = 120 / 1024 = **0.117188**, the 0.1172 given in the lab.

| N | Estimate | Error |
|---|---|---|
| 100 | 0.160000 | 0.042813 |
| 1,000 | 0.110000 | 0.007187 |
| 10,000 | 0.121900 | 0.004712 |
| 100,000 | 0.118260 | 0.001073 |
| 1,000,000 | 0.117463 | 0.000275 |
| 10,000,000 | 0.117051 | 0.000136 |

With N = 100 the estimate is only good to one decimal place. Each time N goes up by 100 the error
drops by about 10, which is the usual Monte Carlo behaviour: the error shrinks like 1 / √N, so every
extra correct digit costs 100 times more trials.

## Task 2: straight flush

The deck is an `ArrayList<String>` built as the lab shows (`"10H"`, `"KS"` and so on). Each
trial shuffles it with `Collections.shuffle(deck, rand)` and takes the first 5 cards as the hand.
`isStraightFlush` checks that all five cards have the same suit and that their ranks are five in a
row, with A 2 3 4 5 handled as a separate case because the ace is otherwise high.

Exact value: a straight flush can start on any of 10 ranks (A up to 10) in each of 4 suits, so there
are 40 of them among C(52,5) = 2,598,960 hands. 40 / 2,598,960 = **0.0000153908**, about 1 in 65,000.

| N | Successes | Estimate |
|---|---|---|
| 100,000 | 0 | 0.00000000 |
| 1,000,000 | 20 | 0.00002000 |
| 10,000,000 | 143 | 0.00001430 |

This one needs far more trials than the other two because the event is so rare. 100,000 hands is
expected to contain only 1.5 straight flushes, and this run had none at all, which would give an
estimate of 0. Even at 10 million there are only around 150 successes to go on, so the estimate is
within about 7% of the exact value here but no better.

`isStraightFlush` was also tested directly with fixed hands:

| Hand | Expected | Result |
|---|---|---|
| `5H 6H 7H 8H 9H` | straight flush | PASS |
| `9S 6S 8S 5S 7S` (out of order) | straight flush | PASS |
| `AH 2H 3H 4H 5H` | straight flush | PASS |
| `3D AD 5D 2D 4D` (out of order) | straight flush | PASS |
| `10C JC QC KC AC` | straight flush | PASS |
| `7D 8D 9D 10D JD` | straight flush | PASS |
| `QH KH AH 2H 3H` | no, a straight can't wrap around the ace | PASS |
| `KH AH 2H 3H 4H` | no | PASS |
| `2H 4H 6H 8H 10H` | no, flush only | PASS |
| `5H 6H 7H 8H 9D` | no, straight only | PASS |
| `AH 2H 3H 4H 5S` | no, straight only | PASS |

## Task 3: random walk

The walker starts at 0 and moves +1 or −1 on each of 20 steps, decided by `rand.nextBoolean()`. A
trial is a success if it finishes at 0.

Exact value: the walker is back at 0 only if exactly 10 of the 20 steps are +1, so the probability is
C(20,10) / 2^20 = 184,756 / 1,048,576 = **0.176197**.

| N | Estimate | Error |
|---|---|---|
| 100 | 0.150000 | 0.026197 |
| 1,000 | 0.177000 | 0.000803 |
| 10,000 | 0.175700 | 0.000497 |
| 100,000 | 0.175570 | 0.000627 |
| 1,000,000 | 0.176279 | 0.000082 |
| 10,000,000 | 0.176180 | 0.000017 |

The estimate settles on 0.176. The error doesn't fall on every single row (N = 1,000 happened to land
closer than N = 100,000) because each run is random, but the overall trend is the same 1 / √N as in
Task 1.

## Notes

- A royal flush (10 J Q K A) is counted as a straight flush, as it is the highest one. Leaving it out
  would give 36 hands and a probability of 0.0000138517.
- The coin flip and random walk programs use 100 up to 10
  million, and the straight flush program starts at 100,000 since smaller values almost always give 0.
