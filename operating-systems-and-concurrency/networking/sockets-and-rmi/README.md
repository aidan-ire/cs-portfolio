# Sockets and remote method invocation

Communication between processes that don't share a parent or a file system, at three levels: TCP
sockets through the C socket calls, the same idea through Java's `Socket` classes, and Java RMI, where
the network disappears behind an ordinary-looking method call.

| File | What it does |
|---|---|
| `socketserver.c` | TCP server on port 8093: prints the client's message and sends a reply |
| `socketclient.c` | connects to the server, sends `hello` and prints the reply |
| `DateServer.java`, `DateClient.java` | Java socket server on port 6013 that sends the date, and its client |
| `RemoteDate.java`, `RemoteDateImpl.java`, `RMIClient.java` | the date service again using RMI |
| `RemoteAdder.java`, `RemoteAdderImpl.java`, `AdderClient.java` | the exercise: an RMI service that adds two integers |

The C and Java date programs are provided listings, with the changes to `socketserver.c` described
below. The adder service is the solution.

## Build and run

C socket programs:

```bash
make
./socketserver &
./socketclient
```

Java socket programs:

```bash
javac *.java
java DateServer &
java DateClient
```

RMI, all from this directory so the registry can find the compiled interfaces:

```bash
rmiregistry &
java RemoteDateImpl &
java RemoteAdderImpl &
java RMIClient
java AdderClient
```

The servers run until killed. `make clean` removes the two C programs and the `.class` files.

## Results

### C sockets

```
$ ./socketserver &
$ ./socketclient
Client: Sent greeting-> hello
Server: Handling client 127.0.0.1
Server: Received msg-> hello
Server: Sent Reply-> server is alive, how are you?
Client: Received Reply-> server is alive, how are you?
```

The client can be run again and again against the same server. Starting a second server while the
first is running:

```
$ ./socketserver
Server: could not bind to port 8093: Address already in use
```

with exit status 1.

### Java sockets

```
$ java DateServer &
$ java DateClient
Fri Nov 13 12:33:13 GMT 2026
```

### RMI

```
$ java RMIClient
Fri Nov 13 12:33:19 GMT 2026

$ java AdderClient
Please input two numbers: 17 25
17 + 25 = 42
```

and the server's terminal shows `add(17, 25) called`, which confirms the addition is done in the
server process and only the result travels back.

| Input to `AdderClient` | Output | Checks |
|---|---|---|
| `17 25` | `17 + 25 = 42` | normal case |
| `-5` and `5` on separate lines | `-5 + 5 = 0` | negative number, input split over lines |
| `abc 2` | `Two whole numbers are needed` | not a number, no remote call made |
| `2147483647 1` | `2147483647 + 1 = -2147483648` | `int` overflow wraps, as it would locally |

C programs run on Ubuntu 24.04 (gcc 13.3) and macOS (Apple clang 17). Java programs run on macOS with
OpenJDK 25.

## Changes to socketserver.c

The exercise asks for a check that the server's socket was created, with an error message that should
appear when a second copy of the server is started.

- `socket()` is checked and the server stops with a message if it fails.
- When a second server starts, `socket()` actually succeeds. The call that fails is `bind()`, because
  the port belongs to the first server. So `bind()` is checked as well (and `listen()`), and that is
  the check that produces the message above.
- The listing prints the received bytes with `%s`, but `recv` does not add a terminating null. The
  server now receives at most one byte fewer than the buffer and terminates the string before printing.

Everything else in the two C files is as given, including the client having no error checks: run with
no server, `socketclient` is ended by a signal when it writes to the unconnected socket, without
printing anything.

## Comparing the three

The C server takes five calls to get as far as talking to a client (`socket`, `bind`, `listen`,
`accept`, `recv`), each with an address structure or a length to fill in. The Java socket version does
the same job with `new ServerSocket(6013)` and `accept()`, and reads and writes lines of text through
stream objects. With RMI there is no visible connection at all: the client asks the registry for an
object by name and calls `getDate()` or `add(a, b)` on it. What is given up at each step is control.
The C version can set any socket option and decide exactly what bytes go on the wire. With RMI the
protocol is fixed, and both ends have to be Java.

## Notes

- On current Java there is no separate stub generation step
  (`rmic` was removed from the JDK): the stub is created at run time, so compiling with `javac` is
  enough.
- `rmiregistry` has to be started in the directory holding the compiled classes, or it can't load
  `RemoteDate` and `RemoteAdder` when the servers register.
- The adder is a separate service with its own interface, registered as `AdderServer`, rather than an
  extra method on the date server.
- `socketclient.c` gives one `-Wall` warning for the unused variable `totalBytesRcvd`, which is in the
  listing.
- Talking to a server on another machine means changing `127.0.0.1` in the client to that machine's
  address. This was only run with both ends on one machine.
