# Practical 10: multithreaded date server

The Java socket date server from practical 6 handled one client at a time. Here it is made
multithreaded: the main thread only accepts connections, and each one is passed to a new worker thread
that sends the date and closes the connection.

| File | What it does |
|---|---|
| `DateServer.java` | listens on port 6013, counts connections and starts a worker for each |
| `WorkerThread.java` | serves one client: sends the date, closes the socket, reports the client number |
| `DateClient.java` | the client from practical 6, unchanged |

## Build and run

```bash
javac *.java
java DateServer &
java DateClient
```

The server runs until killed.

## How it works

`main` loops on `accept()`. For every connection it adds one to a counter and creates a
`WorkerThread`, passing the connected `Socket` and the counter value to the constructor, then goes
straight back to `accept()`. The worker's `run()` holds the code that used to follow `accept()` in
practical 6: wrap the socket's output stream in a `PrintWriter`, print the date to it, close the
socket. It then prints `finished processing client n` on the server's own output.

The counter is only ever touched by the main thread, so it needs no locking. Each worker gets its own
copy of the number and its own socket.

## Sample run

```
$ java DateClient
Fri Nov 13 12:44:22 GMT 2026
```

One client followed by twelve started at the same moment. Every client received one line with the
date, and the server printed:

```
finished processing client 1
finished processing client 2
finished processing client 3
...
finished processing client 12
finished processing client 13
```

Starting a second server while the first is running gives
`java.net.BindException: Address already in use`.

## Notes

- The worker prints the "finished processing" message on the server's standard output, not to the
  client, so the client still receives exactly what it did in practical 6.
- The work per client is so short that the workers finished in the order they were started in this
  run. Nothing in the code guarantees that order.
- A new thread is created for every connection and never reused, which is what the practical asks for.
- Run on macOS with OpenJDK 25.
