# CS portfolio

Programming exercises and write-ups from my Computer Science modules, plus some
self-directed study. Each module and lab folder has its own README with what the code does, how to
compile and run it, and sample output.

| Folder | Contents |
|---|---|
| [programming-fundamentals](programming-fundamentals/) | CS161 Introduction to Computer Science I (Java); CS162 Introduction to Computer Science II (Java), to be added |
| [algorithms-and-data-structures](algorithms-and-data-structures/) | CS210 and CS211 algorithms and data structures (Java), CS230 web development (HTML, CSS, JavaScript, Node), CS240 operating systems (C, Java, shell) |
| [cpp](cpp/) | CS310 programming languages and compilers (Java), CS325 professional software development (C++) |
| [machine-learning](machine-learning/) | machine learning notebooks (Python) |

## Highlights

- [CS211 KMP string search](algorithms-and-data-structures/strings/kmp-string-search/): finds every occurrence of a pattern using the KMP partial match table.
- [CS211 AVL trees](algorithms-and-data-structures/trees/avl-tree-and-min-depth/): worked AVL insertions with rotations, and minimum tree depth in Java.
- [CS211 ElGamal](algorithms-and-data-structures/cryptography/elgamal-encryption/): ElGamal encryption, plus brute-forcing a small key by hand.
- [CS210 airport graph](algorithms-and-data-structures/graphs/airport-route-graph/): an adjacency-list graph of airport routes with degree, reach and hub-removal analysis.
- [CS240 sockets and RMI](operating-systems-and-concurrency/networking/sockets-and-rmi/): TCP sockets in C and Java, and Java RMI.
- [CS240 readers-writers](operating-systems-and-concurrency/concurrency/readers-writers/): a multithreaded simulation under readers-first and writers-first policies.
- [CS230 full-stack music library](web-development/full-stack/music-library/): an Express and SQLite API with a plain JavaScript frontend.
- [CS325 Matrix class](cpp/matrix-class/): a C++ integer matrix with dynamic memory and operator overloading (in progress).
- [ML gradient descent from scratch](machine-learning/linear-regression-gradient-descent/): linear regression trained with gradient descent in a notebook.
- [ML Shakespeare transformer](machine-learning/char-transformer-shakespeare/): character-level transformer language model trained on the complete works of Shakespeare.

## Languages and tools

- Java: `javac` and `java`, one folder per exercise or lab
- C and C++: `gcc`/`g++` or `clang`, with a `Makefile` where a practical has one
- Node.js and SQLite for the CS230 server assignments
- Python 3 and Jupyter for the machine learning notebooks (see `machine-learning/requirements.txt`)

## Notes

- Each folder is self-contained; compile and run from inside it.
- Some CS240 programs use Linux-only features (such as POSIX message queues) and are marked as such
  in their READMEs.
