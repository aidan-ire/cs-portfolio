# The producer consumer problem

A bounded buffer shared by producer threads that put items in and consumer threads that take them out,
synchronised with semaphores. Producers must wait when the buffer is full, consumers must wait when it
is empty, and only one thread at a time may change the buffer.

| File | What it does |
|---|---|
| `Semaphore.java` | counting semaphore built on `synchronized`, `wait` and `notify` |
| `Buffer.java` | circular buffer of 5 slots with `insert` and `remove` |
| `Producer.java` | puts the current date into the buffer, over and over |
| `Consumer.java` | takes dates out of the buffer, over and over |
| `BoundedBufferSimulation.java` | starts two producers and two consumers on one buffer |
| `boundedbuffer.c` | the same problem in C with pthreads and POSIX semaphores |

`Semaphore.java` and `boundedbuffer.c` are provided listings. `Buffer.java` (`insert` and `remove`),
`Producer.java`, `Consumer.java` and `BoundedBufferSimulation.java` are provided classes with the
solution written into them.

## Build and run

```bash
javac *.java
java BoundedBufferSimulation
```

```bash
cc -Wall boundedbuffer.c -l pthread -o boundedbuffer
./boundedbuffer
```

Both run until stopped with CTRL-C. The C program needs Linux (see the notes).

## insert and remove

```java
public void insert(Object item) {
   empty.acquire();
   mutex.acquire();

   buffer[in] = item;
   in = (in + 1) % BUFFER_SIZE;

   mutex.release();
   full.release();
}
```

`empty` starts at 5 and counts free slots, `full` starts at 0 and counts items, and `mutex` protects
the array and the `in` and `out` indexes. `remove` is the mirror image: acquire `full`, take the item
at `out`, release `empty`.

The order of the two acquires matters. If a producer took `mutex` first and then blocked on `empty`
because the buffer was full, no consumer could get `mutex` to remove anything, and everything would
stop.

## Changes to Producer, Consumer and the simulation

- Both constructors take an id and a sleep period in milliseconds as well as the buffer, and store
  them in fields.
- Each loop prints the id with its message (`Producer 1 inserted ...`, `Consumer 2 removed ...`).
- `main` creates two producers and two consumers with ids 1 and 2, all with a 1000 ms sleep period,
  sharing one `Buffer`.

## Sample run

```
Producer 1 inserted Fri Nov 13 12:33:33 GMT 2026
Consumer 1 removed Fri Nov 13 12:33:33 GMT 2026
Consumer 2 removed Fri Nov 13 12:33:33 GMT 2026
Producer 2 inserted Fri Nov 13 12:33:33 GMT 2026
Consumer 1 removed Fri Nov 13 12:33:34 GMT 2026
Consumer 2 removed Fri Nov 13 12:33:34 GMT 2026
Producer 1 inserted Fri Nov 13 12:33:34 GMT 2026
Producer 2 inserted Fri Nov 13 12:33:34 GMT 2026
```

In 9 seconds there were 16 inserts and 16 removes. A `removed` line can appear before the `inserted`
line for the same item, as in lines 2 to 4 above. The buffer operations happened in the right order,
but each thread prints after its buffer call has returned, and the consumer can get to its `println`
first.

## boundedbuffer.c: why the output slows down

Run for 26 seconds on Ubuntu 24.04:

```
Producer 1 Inserted 1794573136
Producer 2 Inserted 1794573136
Producer 2 Inserted 1794573137
Producer 1 Inserted 1794573137
Consumer 1 Removed 1794573136
Consumer 2 Removed 1794573136
...
Producer 1 Inserted 1794573145
Consumer 2 Removed 1794573140
Producer 2 Inserted 1794573145
Consumer 1 Removed 1794573141
Producer 2 Inserted 1794573147
Consumer 2 Removed 1794573141
Producer 1 Inserted 1794573147
```

Each producer sleeps 1 second between inserts and each consumer sleeps 2 seconds between removes. With
two of each, 2 items go in per second and 1 comes out, so the buffer gains one item a second. It holds
10, so after about 10 seconds it is full. That is the point in the output where the timestamps stop
going up by one (…144, …145) and start going up by two (…147, …149).

From then on a producer blocks in `sem_wait(&empty)` until a consumer removes something. Producers can
only insert as fast as consumers remove, 1 item a second between them, and every `Inserted` line comes
straight after a `Removed` line. The consumers were never the ones waiting.

## Notes

- The Java simulation doesn't slow down in the same way, because its producers and consumers all sleep
  for the same time, so in the run above the buffer never filled.
- `Semaphore.release` uses `notify`, which wakes one waiting thread but not any particular one, so
  threads are not served in the order they started waiting.
- `boundedbuffer.c` creates its semaphores with `sem_init`. macOS declares that function but doesn't
  implement it (the compiler warns that it is deprecated), so the program was built and run on Linux
  only. A one-line comment at the top of the file says so.
- In the listing's `main`, the variables named `p1` and `p2` are passed to the consumer threads and
  `c1` and `c2` to the producers. The values are 1 and 2 either way, so the output is not affected.
