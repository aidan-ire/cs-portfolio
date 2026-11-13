# Named pipes and message queues

Two ways for unrelated processes to communicate: named pipes, which have a name in the file system so
any process can open them, and POSIX message queues, which hold whole messages with priorities. In
between is an exercise on redirecting standard output by rearranging the descriptor table with `dup`.

| File | What it does |
|---|---|
| `namedpipe.c` | run as `reader` or `writer` on a named pipe called `my_pipe` |
| `simpleoutput.c` | prints a name with `printf` |
| `outputredirect.c` | the same, with standard output redirected into a named pipe called `fifo` |
| `mqueuewriter.c` | creates the queue `/test_queue` and sends 10 messages with priorities 1 to 10 |
| `mqueuereader.c` | receives everything on the queue, then removes it |
| `mymqueuewriter.c` | sends five messages typed in by the user, each with its own priority |

`mqueuewriter.c` and `mqueuereader.c` are provided listings. `namedpipe.c`, `simpleoutput.c`,
`outputredirect.c` and `mymqueuewriter.c` are the solutions.

## Build and run

```bash
make
make clean
```

The message queue programs use `<mqueue.h>`, which exists on Linux but not on macOS. The Makefile
builds them only on Linux (linking with `-lrt`) and builds the three pipe programs everywhere.
`make clean` also removes the pipes and output files the programs create.

Results below are from Ubuntu 24.04 (gcc 13.3). The pipe programs were also run on macOS.

## Part A: named pipes

```
$ ./namedpipe writer &
Named Pipe created successfully
$ ./namedpipe reader &
Using existing named pipe
writer: Sent message <1 message text> to named pipe
writer: Sent message <2 message text> to named pipe
...
writer: Sent message <10 message text> to named pipe
reader: Read message <1 message text2 mess> 20 characters
reader: Read message <age text3 message te> 20 characters
reader: Read message <xt4 message text5 me> 20 characters
reader: Read message <ssage text6 message > 20 characters
reader: Read message <text7 message text8 > 20 characters
reader: Read message <message text9 messag> 20 characters
reader: Read message <e text10 message tex> 20 characters
reader: Read message <t> 1 characters
reader: Read message <> 0 characters
reader: Read message <> 0 characters
```

What this shows:

- The writer prints nothing after creating the pipe until the reader starts. `open` for writing blocks
  until some process has the pipe open for reading.
- A pipe is a stream of bytes with no message boundaries. The writer made ten writes of 14 or 15 bytes
  (141 bytes in total), and the reader got them back as seven blocks of 20 and one of 1, cutting across
  the messages.
- The last two reads return 0. By then the writer has closed its end, so there is nothing more to come.
- On the second run both processes print `Using existing named pipe`, and `ls -l` shows the pipe is
  still there: `prw------- 1 student student 0 Nov 13 12:32 my_pipe`. The `p` marks it as a pipe.

On macOS one run split the data differently (four blocks of 20, then 4, 14, 14, 14 and 15 bytes),
because the reader caught up with the writer partway through. How the bytes are divided between reads
depends on timing.

**gzip as the reader.** With the writer started on its own and blocked:

```
$ gzip <my_pipe >pipeout.gz
$ ls -l pipeout.gz
-rw-r--r-- 1 student student 61 Nov 13 12:32 pipeout.gz
$ gunzip pipeout.gz
$ cat pipeout
1 message text2 message text3 message text4 message text5 message text6 message text7 message text8 message text9 message text10 message text
```

`gzip` knows nothing about `namedpipe`, it just reads its standard input, which the shell has
connected to the pipe. `cat pipeout.gz` printed mostly unprintable bytes. After `gunzip` the file is
the 141 bytes the writer sent, with no newlines because the messages don't contain any.

## Part B: redirecting standard output

```
$ ./simpleoutput
Aidan Hughes
$ ./outputredirect &
$ cat fifo
Aidan Hughes
```

`outputredirect` prints nothing on the terminal. It opens `fifo` for writing (descriptor 3), closes
descriptor 1 and calls `dup(3)`. `dup` uses the lowest free slot, which is now 1, so standard output
is the pipe and the `printf` goes there. The program waits in `open` until `cat` opens the other end.

## Part C: message queues (Linux only)

```
$ ./mqueuewriter
Opened a message queue called /test_queue and got descriptor 3
Sending message : '1 message text' to message queue
...
Sending message : '10 message text' to message queue
Closed the queue
$ ./mqueuereader
Opened a message queue called /test_queue and got descriptor 3
Number of messages in the queue 10
Received message : 10 message text with priority 10 numchars 15
Received message : 9 message text with priority 9 numchars 14
...
Received message : 1 message text with priority 1 numchars 14
Closed the queue
Destroyed the queue
```

The writer has finished and gone before the reader starts, and the messages are still there: the
kernel keeps the queue (it is visible as `/dev/mqueue/test_queue`) until it is unlinked. They come back
whole, one per `mq_receive`, highest priority first, which is the reverse of the order they were sent
in. Running the reader again straight afterwards gives `Couldn't open queue /test_queue`, since the
first run removed it.

### mymqueuewriter

```
$ ./mymqueuewriter
Opened a message queue called /test_queue and got descriptor 3
Enter message 1: first message
Enter its priority: 3
Sending message : 'first message' to message queue
Enter message 2: second one is urgent
Enter its priority: 20
Sending message : 'second one is urgent' to message queue
Enter message 3: third
Enter its priority: 1
Sending message : 'third' to message queue
Enter message 4: fourth
Enter its priority: 3
Sending message : 'fourth' to message queue
Enter message 5: fifth
Enter its priority: 7
Sending message : 'fifth' to message queue
Closed the queue
$ ./mqueuereader
Opened a message queue called /test_queue and got descriptor 3
Number of messages in the queue 5
Received message : second one is urgent with priority 20 numchars 20
Received message : fifth with priority 7 numchars 5
Received message : first message with priority 3 numchars 13
Received message : fourth with priority 3 numchars 6
Received message : third with priority 1 numchars 5
Closed the queue
Destroyed the queue
```

Two messages with the same priority (3) come out in the order they went in.

| Input | Result | Checks |
|---|---|---|
| five messages, priorities 3, 20, 1, 3, 7 | received in the order 20, 7, 3, 3, 1 | priority order, ties first in first out |
| message with leading and trailing spaces | spaces kept, length counts them | only the newline is removed |
| priority `abc` | warning printed, sent with priority 0 | not a number |
| priority `-4` | warning printed, sent with priority 0 | negative |
| input ends after three messages | `Input ended early, 3 messages sent`, queue closed | end of input |

## Notes

- `namedpipe.c` creates the pipe before it looks at its arguments, so running it with a wrong argument still leaves `my_pipe` behind.
- `mymqueuewriter.c` reads each message with `fgets` and removes the newline, reads the priority with
  `scanf`, then discards the rest of that line so the newline after the number isn't taken as the next
  message.
- `mqueuereader.c` gives two warnings with `-Wall` (an `int *` passed where `mq_receive` expects
  `unsigned int *`, and `rc` set but not used). It is a provided listing and is left as it is.
- A one-line comment at the top of the three message queue files says they are Linux only.
- If the writer is run twice without the reader in between, the queue's limit of 10 messages is
  reached and the next `mq_send` blocks until something is received.
