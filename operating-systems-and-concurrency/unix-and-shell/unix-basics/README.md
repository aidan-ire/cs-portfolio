# Practical 1: Unix basics

An introduction to working at the shell prompt on Ubuntu: moving around the file system, creating,
copying, comparing and deleting files, and looking at the processes that are running. There is no
program to write, so this is a short lab journal of the commands and the answers to the exercises at
the end of the practical.

The commands were run in a bash shell on Ubuntu 24.04. Files were created with `echo` and `>` rather
than the gedit editor, since the shell used had no desktop.

## Walkthrough

| Command | What it did |
|---|---|
| `ls`, `ls -l` | listed the current directory; `-l` adds permissions, owner, size in bytes and modification date |
| `pwd` | printed the full path of the current directory (`/home/student`) |
| `cd ~`, `cd cs240`, `cd ..` | moved to the home directory, into a subdirectory, and up to the parent |
| `mkdir CS240`, `mkdir temp`, `rmdir temp` | made two directories and removed the empty one |
| `mv CS240 cs240` | renamed the directory (names are case sensitive, so these are different names) |
| `cat myfile` | printed the file to the terminal |
| `cp myfile myfile2` | made a copy |
| `cmp myfile myfile2` | after changing one character in the copy: `myfile myfile2 differ: char 8, line 1` |
| `rm myfile`, `rm *` | deleted one file, then everything in the current directory (checked with `pwd` first) |
| `date`, `who` | printed the date and time, and who is logged in |
| `man ls` | opened the manual page; space pages through it and `q` quits |
| `ps` | listed the processes started from this terminal (the shell and `ps` itself) |
| `ps -e` | listed every process on the system, not only the ones belonging to this terminal |
| `ps -el` | the same list in long format, with the parent process id, priority and state |
| `pstree` | showed the processes as a tree, each one drawn under the process that created it |
| `time ls` | ran `ls` and then printed how long it took |
| `top` | live table of processes sorted by CPU use, `q` to leave |

Running a command with `&` on the end (`gedit myfile &`) starts it without making the shell wait, so
the prompt comes straight back. Without the `&` the shell is blocked until the editor is closed.

`kill -9 <pid>` on the `gnome-terminal-` process: signal 9 can't be caught or ignored, so the terminal
program is ended immediately, its window disappears and the shell running inside it goes with it. This
one needs the desktop, so it was not repeated in the shell the rest of the journal was run in.

## Test yourself exercises

Starting in a new directory `~/CS240`.

**1. Create file1 to file4, each containing "This is file n".**

```bash
echo "This is file 1" > file1
echo "This is file 2" > file2
echo "This is file 3" > file3
echo "This is file 4" > file4
```

**2. Copy each to a `.bak` file.**

```bash
cp file1 file1.bak
cp file2 file2.bak
cp file3 file3.bak
cp file4 file4.bak
```

**3. Change file1.bak to "This is a copy of file1".**

```bash
echo "This is a copy of file1" > file1.bak
```

**4. Compare the files.**

```
$ cmp file1 file1.bak
file1 file1.bak differ: char 9, line 1
$ cmp file2 file2.bak
$
```

`cmp` reports the first byte where the files differ. Both start with `This is ` (8 characters) and the
9th is `f` in one and `a` in the other. For identical files it prints nothing at all.

**5. Time the comparison.**

```
$ time cmp file1 file1.bak
file1 file1.bak differ: char 9, line 1

real	0m0.001s
user	0m0.000s
sys	0m0.001s
```

`real` is the elapsed time, `user` and `sys` are the CPU time spent in the program and in the kernel
on its behalf. The numbers depend on the machine.

**6. List the root directory.**

```
$ ls /
bin  boot  dev  etc  home  lib  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
```

**7. What does `ls /d*` do?**

The shell expands `/d*` before `ls` runs, to every name in the root directory beginning with `d`. Here
that is only `/dev`, so the command becomes `ls /dev` and lists the contents of the device directory
(`null`, `random`, `pts` and so on), not the name `/dev` itself. `echo /d*` prints `/dev`, which shows
the expansion.

**8. Remove the `.bak` files.**

```bash
rm *.bak
```

**9. and 10. Make a directory called text and move the files into it.**

```bash
mkdir text
mv file* text
```

**11. List the files in text. How many?**

```
$ ls text
file1  file2  file3  file4
```

Four files.

**12. Terminate the shell with a command.**

```bash
exit
```

`logout` works in a login shell, and `kill -9 $$` also does it, since `$$` is the shell's own process id.
