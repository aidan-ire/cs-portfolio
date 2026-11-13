# CS portfolio

Programming work from my Computer Science and Software Engineering degree. It's organised by topic rather than by course. Every project folder has its own
README with what the code does, how to build and run it, and sample output.

My machine learning projects, including a character-level transformer trained on Shakespeare, are
in a separate repo: [machine-learning](https://github.com/aidan-ire/machine-learning).

| Area | Languages | Contents |
|---|---|---|
| [programming-fundamentals](programming-fundamentals/) | Java | 119 short exercises across 19 topics: selection, loops, strings, arrays, methods, recursion, input validation, searching and sorting, classes and inheritance |
| [algorithms-and-data-structures](algorithms-and-data-structures/) | Java | sorting, searching, recursion, linked lists and hashing, trees, graphs, string search, compression, cryptography, bit manipulation and simulation |
| [operating-systems-and-concurrency](operating-systems-and-concurrency/) | C, Java, shell | Unix and shell scripting, processes and pipes, message queues, sockets and RMI, and semaphore-based concurrency problems |
| [web-development](web-development/) | HTML, CSS, JavaScript, Node.js | static and responsive frontends, a REST API with Express and SQLite, and a full-stack music library |
| [cpp](cpp/) | C++ | number algorithms, C-string functions and a matrix class with operator overloading |

## Highlights

- [Shakespeare transformer](https://github.com/aidan-ire/machine-learning/tree/main/char-transformer-shakespeare): character-level transformer language model trained on the complete works of Shakespeare.
- [Gradient descent from scratch](https://github.com/aidan-ire/machine-learning/tree/main/linear-regression-gradient-descent): linear regression trained with hand-written gradient descent, compared against scikit-learn.
- [Full-stack music library](web-development/full-stack/music-library/): an Express and SQLite API with a plain JavaScript frontend.
- [C++ matrix class](cpp/matrix-class/): an integer matrix with dynamic memory and operator overloading.
- [KMP string search](algorithms-and-data-structures/strings/kmp-string-search/): finds every occurrence of a pattern using the KMP partial match table.
- [AVL trees](algorithms-and-data-structures/trees/avl-tree-and-min-depth/): worked AVL insertions with rotations, and minimum tree depth.
- [ElGamal encryption](algorithms-and-data-structures/cryptography/elgamal-encryption/): ElGamal encryption, plus brute-forcing a small key by hand.
- [Airport route graph](algorithms-and-data-structures/graphs/airport-route-graph/): an adjacency-list graph with degree, reach and hub-removal analysis.
- [Sockets and RMI](operating-systems-and-concurrency/networking/sockets-and-rmi/): TCP sockets in C and Java, and Java RMI.
- [Readers and writers](operating-systems-and-concurrency/concurrency/readers-writers/): a multithreaded simulation under readers-first and writers-first policies.

## Languages and tools

- Java: `javac` and `java`, one folder per exercise or project
- C and C++: `gcc`/`g++` or `clang`, with a `Makefile` where a project has one
- Node.js and SQLite for the web backends

## Notes

- Each folder is self-contained; compile and run from inside it.
- The POSIX message queue programs use Linux-only features and are marked as such in their README.
