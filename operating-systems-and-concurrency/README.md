# Operating systems and concurrency (C, Java, shell)

Systems programming on Linux: the shell, processes, inter-process communication, networking and
classic synchronisation problems. These come from my second-year operating systems module (CS240).

| Topic | Project | What it covers |
|---|---|---|
| Unix and shell | [Unix basics](unix-and-shell/unix-basics/) | navigating the file system and managing files and processes |
| Unix and shell | [Shell scripts](unix-and-shell/shell-scripts/) | permissions, jobs, pipes and bash scripts (`filefind`, `xword`) |
| C programming | [Basic I/O in C](c-programming/basic-io/) | `printf`/`scanf` and the `read`, `write`, `open` and `close` system calls |
| Processes and IPC | [Fork and pipes](processes-and-ipc/fork-and-pipes/) | `fork`, `exec` and unnamed pipes |
| Processes and IPC | [Named pipes and message queues](processes-and-ipc/named-pipes-and-message-queues/) | FIFOs and POSIX message queues (Linux only) |
| Networking | [Sockets and RMI](networking/sockets-and-rmi/) | TCP sockets in C and Java, and Java RMI |
| Networking | [Multithreaded date server](networking/multithreaded-date-server/) | a socket server with a thread per client |
| Concurrency | [Producer consumer](concurrency/producer-consumer/) | a bounded buffer synchronised with semaphores, in Java and C |
| Concurrency | [Dining philosophers](concurrency/dining-philosophers/) | the deadlock and three ways to avoid it |
| Concurrency | [Readers and writers](concurrency/readers-writers/) | readers-first and writers-first policies |

C projects have a `Makefile`; run `make` inside the folder. Java projects build with `javac *.java`.
