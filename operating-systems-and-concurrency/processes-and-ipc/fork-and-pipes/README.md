# Practical 4: process creation and pipes

Creating processes with `fork`, replacing a process's program with `exec`, and passing data between a
parent and its child through an unnamed pipe.

| File | What it does |
|---|---|
| `p4a.c` | forks; the child prints a line and exits, the parent carries on |
| `p4b.c` | `p4a.c` with the child's `exit(0)` replaced by `sleep(5)` |
| `p4c.c` | forks a child that runs `ls` while the parent waits for it |
| `p4d.c` | `p4c.c` changed so the child runs `ps` |
| `p4problem.c` | fork and exec inside a loop, for analysis |
| `p4e.c` | one process writes a string into a pipe and reads it back 5 bytes at a time |
| `p4f.c` | `p4e.c` with a 10-byte buffer |
| `p4g.c` | the parent writes to a pipe every 5 seconds and the child reads from it |
| `p4h.c` | `p4g.c` with each process closing the end of the pipe it doesn't use |

`p4a.c`, `p4c.c`, `p4problem.c` and `p4e.c` are provided listings. `p4b.c`, `p4d.c`, `p4f.c`, `p4g.c`
and `p4h.c` are the solutions.

## Build and run

```bash
make
./p4a
make clean
```

`p4g` and `p4h` run until stopped with CTRL-C.

## Results

Output below is from Ubuntu 24.04, run at a terminal.

### p4a: which process prints "Which process am I?"

```
I am the parent
Which process am I?
I am the child
```

Only the parent. The child calls `exit(0)` inside the `if`, so it never reaches the last `printf`. The
order of the parent's and child's lines is up to the scheduler.

### p4b

```
I am the child
I am the parent
Which process am I?
Which process am I?
```

Without the `exit`, the child also falls out of the `if` and runs the last `printf`, so the question is
printed twice. The parent's copy appears at once and the parent ends, which brings the shell prompt
back. The child's copy arrives 5 seconds later, after the prompt.

### p4d

```
    PID TTY          TIME CMD
      1 ?        00:00:00 bash
    169 ?        00:00:00 p4d
    170 ?        00:00:00 ps
Child 170 complete
```

The only change from `p4c.c` is the program handed to `execlp`: `/bin/ps` instead of `/bin/ls`. The
listing shows the parent `p4d` still alive, waiting, and the child, which has pid 170 and is now
running `ps`. The pid the parent prints is the same 170.

### p4problem

Expected before running: the loop looks as if it creates several children that each run `ls`, but the
`execlp` is in the `pid != 0` branch, so it is the parent that gets replaced each time.

- Pass 1: the original process forks. As the parent it prints the child's pid and becomes `ls`. It
  never returns to the loop.
- The child (pid 0 branch not taken) continues the loop with `i = 2` and forks. Now it is a parent, so
  it prints its own child's pid and becomes `ls`.
- That child does the same on pass 3.
- The last child finds `i = 4`, leaves the loop and ends without printing anything.

So there should be three `Process` lines, each one pid higher than the last, and three directory
listings. Four processes exist in total, in a chain rather than a parent with three children.

```
Process 174
Process 175
Process 176
Makefile  p4b    p4c.c  p4e    p4f.c  p4h    p4problem.c
Makefile  p4b    p4c.c  p4e    p4f.c  p4h    p4problem.c
p4a       p4b.c  p4d    p4e.c  p4g    p4h.c
p4a       p4b.c  p4d    p4e.c  p4g    p4h.c
p4a.c     p4c    p4d.c  p4f    p4g.c  p4problem
p4a.c     p4c    p4d.c  p4f    p4g.c  p4problem
Makefile  p4b    p4c.c  p4e    p4f.c  p4h    p4problem.c
p4a       p4b.c  p4d    p4e.c  p4g    p4h.c
p4a.c     p4c    p4d.c  p4f    p4g.c  p4problem
```

That matches, with the three listings running at the same time and their lines interleaved. Nothing
waits for anything, so the shell prompt can come back after the first `ls` and before the others.

When the output was piped (`./p4problem | cat`) the three listings appeared but the `Process` lines did
not. With a pipe, `printf` keeps the text in a buffer inside the process, and `execlp` throws the whole
process image away, buffer included, before it is ever written.

### p4e and p4f

```
read descriptor = 3, write descriptor = 4,buffersize = 5
 Data read from pipe =Welco
 Data read from pipe =me to
 Data read from pipe = Unix
 Data read from pipe = pipe
 Data read from pipe =s
```

```
read descriptor = 3, write descriptor = 4,buffersize = 10
 Data read from pipe =Welcome to
 Data read from pipe = Unix pipe
 Data read from pipe =s
```

The pipe gets descriptors 3 and 4, the first two free slots after stdin, stdout and stderr. The 21-byte
message takes five reads of 5 or three reads of 10, the last one short in both cases, which is what
ends the loop.

### p4g and p4h

```
Parent writing: Welcome to Unix pipes
Child read: Welcome to Unix pipes
Parent writing: Welcome to Unix pipes
Child read: Welcome to Unix pipes
```

A pair of lines every 5 seconds, the same for both programs. The difference shows when the parent is
killed:

| Program | After `kill` on the parent |
|---|---|
| `p4g` | the child is still in the process list 6 seconds later, blocked in `read` |
| `p4h` | the child prints `Child: pipe closed by parent, exiting` and ends |

`read` on a pipe only returns 0 when no process has the write end open. In `p4g` the child still holds
its own copy of the write end, so it can never see end of file and waits for ever. In `p4h` the child
closed that copy at the start, so the parent's was the only one.

## Quick questions from the previous practical

1. **`scanf` and `read`:** `scanf` is a C library function that reads text from standard input and
   converts it into typed values (strings, integers) according to a format. `read` is a system call
   that copies raw bytes from any descriptor into a buffer, up to a count, with no conversion and no
   terminating null.
2. **Value returned by `read`:** the number of bytes actually read, which can be fewer than asked for.
   0 means end of file and -1 means an error.
3. **Process descriptor table:** a per-process array of pointers to entries in the system file table.
   The index into it is the file descriptor a program passes to `read` and `write`. Entries 0, 1 and 2
   are standard input, output and error.
4. **`open`:** creates a system file table entry for the file, recording the access mode and a
   position starting at 0, points the lowest free slot of the descriptor table at it and returns that
   slot's index.
5. **`close`:** frees the descriptor slot so it can be reused, and lets the system release the file
   table entry once nothing else refers to it. For a pipe it also matters to the other side, as `p4h`
   shows.

## Notes

- The provided listings are kept as given, including the absence of error checks. `p4g.c` and `p4h.c`
  check `pipe`, `fork`, `read` and `write`.
- `p4c` and `p4d` print `Child n complete` with no newline at the end, as the listing has it.
- The end-of-file check in the child of `p4h.c` goes beyond the two `close` calls.
  Without it the child would loop printing empty reads once the parent had gone.
- Also built on macOS (Apple clang 17) with no warnings, where `p4d` and `p4e` were run.
