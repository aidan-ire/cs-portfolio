# Practical 3: basic I/O programming in C

First C programs of the module: formatted I/O with `printf` and `scanf`, then the lower-level `read`,
`write`, `open` and `close` system calls that work on file descriptors. The last program is a small
version of `cat` built from those calls.

| File | What it does |
|---|---|
| `p3a.c` | prints `Hello World` with `printf` |
| `p3b.c` | reads a name and an age with `scanf` and prints them back |
| `p3c.c` | prints `Hello World` with the `write` system call |
| `p3d.c` | reads up to 6 bytes from the keyboard with `read` and reports how many it got |
| `p3e.c` | opens `p3a.c`, reads its first 6 bytes and prints them |
| `p3f.c` | the exercise: asks for a file name and prints the file 10 bytes at a time |

`p3a.c` to `p3e.c` are provided listings. `p3f.c` is the solution.

## Build and run

```bash
make
./p3f
make clean
```

`make` compiles each program with `cc -Wall`. `p3e` has to be run from this directory, because it opens
`p3a.c` by name.

## Sample runs

```
$ ./p3b
What's your name and age?
Aidan 21
Hello Aidan, You are 21 years old

$ ./p3d
Enter 6 characters
abcdefgh
6 characters were entered
The characters were
abcdef

$ ./p3e
6 characters were read from file p3a.c
The characters were
#inclu

$ ./p3f
Enter the name of the file to print
p3a.c
#include <stdio.h>
int main()
{
     printf("Hello World\n\n");
}
```

In `p3d`, typing `abc` and return gives `4 characters were entered`: the newline is a byte like any
other, and `read` returns as soon as the line is available instead of waiting for 6.

## Tests run on p3f

The output after the prompt line was compared with the original file using `cmp`.

| Input | Result | Checks |
|---|---|---|
| `p3a.c` (66 bytes) | identical | ordinary file, last block shorter than 10 |
| 20-byte file | identical | length an exact multiple of 10, so the loop ends on a read of 0 |
| 25-byte file | identical | one short block at the end |
| empty file | identical (no output) | first read returns 0 |
| `p3f.c` (1367 bytes) | identical | longer file |
| `nosuchfile` | `nosuchfile: No such file or directory`, exit status 1 | open fails |
| no input at all | `No file name was entered`, exit status 1 | `scanf` fails |

Built and run on Ubuntu 24.04 (gcc 13.3) and macOS (Apple clang 17), no warnings on either.

## The ten quick questions

1. **List all processes:** `ps -e` (`ps -el` for the long format, `top` for a live view).
2. **Symbols:** `/` is the root directory, `~` is the home directory and `.` is the current directory.
3. **`$PATH`:** an environment variable holding a colon-separated list of directories. When a command
   is typed, the shell looks for a program of that name in each of them in order.
4. **Full path of the current directory:** `pwd`.
5. **A command shell:** a program that reads commands, from the keyboard or from a script, finds the
   programs they name, creates a process for each one, connects up its input and output, and waits for
   it to finish before prompting again.
6. **Running a command alongside the shell:** put `&` at the end, as in `gedit &`.
7. **Output to a file:** `command > file` (`>>` adds to the end instead of replacing).
8. **Output of one process into another:** a pipe, `command1 | command2`.
9. **A shell script:** a text file of shell commands with execute permission, run by a shell as if the
   lines had been typed.
10. **Arguments to a script:** written after the script's name on the command line, and available
    inside it as `$1`, `$2` and so on.

## Notes

- `p3f` uses a do-while loop, which stops at the first block shorter than 10 bytes. That is the right
  test for a file on disk. Return values of `open`, `read`, `write` and `close` are checked.
- The file name is read with `%99s`, so a name with a space in it can't be entered.
- `p3d` and `p3e` mix `printf` and `write`. On a terminal the lines come out in program order. When the
  output is piped or redirected, `printf` text is held in a buffer until the program ends while `write`
  goes out immediately, so the characters appear before the lines that introduce them. `p3f` flushes
  its prompt for that reason.
