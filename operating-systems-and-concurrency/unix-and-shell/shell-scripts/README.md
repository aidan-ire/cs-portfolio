# Practical 2: command shell and shell scripts

Using the bash shell as more than a prompt: starting and stopping shells, file permissions, background
jobs, `grep`, redirection and pipes, and then putting commands into executable script files that take
arguments. The practical ends with two scripts that build new commands out of existing ones.

| File | What it does |
|---|---|
| `script1` | prints `hello world` and then the date |
| `script2` | lists every dictionary word containing `hell` |
| `script3` | prints its first argument |
| `filefind` | searches the home directory tree for file names matching a pattern |
| `xword` | crossword helper: lists dictionary words that fit an outline, in a chosen number of columns |

## Running

The scripts have execute permission set. `script2` and `xword` need the word list at
`/usr/share/dict/words` (the `wamerican` package on Ubuntu).

```bash
./script1
./script2 > scriptout &
./script3 hello
./filefind script
./xword b..m.. 5
```

Everything below was run on Ubuntu 24.04 with bash 5.2, where the word list has 104,334 entries.

## Journal

**Shells and processes.** Typing `bash` starts a second shell as a child of the first, and `ps -l`
then shows two `bash` lines plus `ps` itself. The `PPID` column of the new shell is the `PID` of the
old one. `kill -9` on the new shell's pid, or CTRL-D at its prompt, ends it and the original shell's
prompt comes back. CTRL-D is end of input: a shell that reads end of input has nothing more to run, so
it exits. In the last shell of a terminal window that closes the window.

**Running script1.**

```
$ script1
bash: script1: command not found
$ ./script1
bash: ./script1: Permission denied
$ ls -l script1
-rw-r--r-- 1 student student 17 Nov 13 12:30 script1
$ chmod +x script1
$ ./script1
hello world
```

The first attempt fails because the current directory is not one of the directories in `$PATH`
(`/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin`), so the shell never looks there. The
second finds the file but it has no `x` permission. After `echo date >> script1` the script has two
lines and prints the date as well, and it is still executable, since editing a file doesn't change its
permissions.

**grep and redirection.** `grep quick /usr/share/dict/words` printed 23 words, from `Bisquick` to
`quicksand's`. `grep file text/*` printed the line from each of the four files made in practical 1,
with the file name in front. `grep -i ^q /usr/share/dict/words > temp` put 491 words in `temp`, and
`grep ^q....$ /usr/share/dict/words > temp2` found 32 five-letter words beginning with q.

**Exercise: removing the blank lines from pr.**

```bash
pr -4 temp | grep -v '^$' | more
```

`^$` matches a line with nothing between its start and end, and `-v` keeps only the lines that do not
match. For the 491-word `temp`, `pr -4` wrote 198 lines, 72 of them blank, and the filtered version has
126.

**Exercise: script2.** The command is `grep hell /usr/share/dict/words`. Run as
`./script2 > scriptout &`, the shell printed the job number and pid and gave the prompt back straight
away, and `scriptout` ended up with 71 words (`Michelle`, `Mitchell`, `Othello`, ...).

**script3.** `./script3 hello` prints `hello` and `./script3 there` prints `there`. The shell puts the
first word after the script name into `$1` before the script's commands run, so the same script gives
different output each time. With no argument `$1` is empty and it prints a blank line.

## Problem 1: filefind

```bash
du -a ~ | grep "$1"
```

`du -a` walks the whole tree under the home directory and prints one line per file with its full path,
and `grep` keeps the lines containing the argument.

```
$ ./filefind script
4	/home/student/cs240/practical2/script1
4	/home/student/cs240/practical2/script2
4	/home/student/cs240/practical2/scriptout
4	/home/student/cs240/practical2/script3
```

## Problem 2: xword

```bash
grep "^$1\$" /usr/share/dict/words | pr -"$2" | grep -v '^$'
```

The outline is anchored with `^` and `$` so that only words of exactly that length match, each `.` in
it standing for any one letter. `pr` arranges the matches in the number of columns given by the second
argument and the last `grep` strips the blank lines `pr` adds.

```
$ ./xword b..m.. 5
2026-11-13 12:30                                                  Page 1
balm's	      berm's	    blames	  boom's	brim's
barman	      bitmap	    blammo	  bowman	bummed
beamed	      blamed	    blimps	  bowmen	bummer
beam's	      blamer	    boomed
```

`./xword b..... 5` matched 657 words, printed in five columns over three pages of `pr` output.

## Notes

- `du` on its own only prints directories, so `filefind` uses `du -a` to get a line for every file.
- The match is on the whole line, so a pattern also matches directory names along the path, not only
  the last part.
- `b..m..` gives 18 matches with this word list, which includes possessives such as `balm's`, where
  the apostrophe counts as one of the "any character" positions.
- The scripts start with `#!/bin/bash` and a one-line comment, which the versions typed at the prompt
  during the practical (`echo 'echo hello world' > script1`) don't have.
