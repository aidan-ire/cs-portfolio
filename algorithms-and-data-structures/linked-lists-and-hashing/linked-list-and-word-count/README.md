# Linked lists and hash tables

Five methods added to a singly linked list of integers (`get`, `insert_sorted`, `reverse`,
`sort_list`, `merge_lists`), and a word count of *The Picture of Dorian Gray* using a `HashMap` to
find the most frequent six-letter word.

## Build and run

```bash
javac *.java
java LinkedListJava
java WordCount path/to/dorian.txt
```

`WordCount` reads `dorian.txt` from the current folder if no path is given.

## Tasks 1 to 5: linked list

Each method takes the head of a list, with the names and signatures from the lab.

| Method | What it does |
|---|---|
| `int get(Node head, int index)` | returns the element at `index`, where the first element is at index 1 |
| `void insert_sorted(Node head, int value_to_add)` | inserts a value into a sorted list, keeping it sorted |
| `Node reverse(Node head)` | builds a new list in reverse order and returns its head |
| `Node sort_list(Node head)` | builds a new sorted list by inserting every element with `insert_sorted` |
| `Node merge_lists(Node head1, Node head2)` | builds a new sorted list from the elements of both lists |

`reverse`, `sort_list` and `merge_lists` all create new nodes and leave the input lists as they were.

### Output

```
List: 10 -> 20 -> 30 -> 40 -> null
After deleting 30: 10 -> 20 -> 40 -> null
Search 40: true
Search 30: false

get(head, 1): 10
get(head, 3): 40
get(head, 4): -1
After insert_sorted 25, 50, 5, 20: 5 -> 10 -> 20 -> 20 -> 25 -> 40 -> 50 -> null
Reversed copy: 50 -> 40 -> 25 -> 20 -> 20 -> 10 -> 5 -> null
Original after reverse: 5 -> 10 -> 20 -> 20 -> 25 -> 40 -> 50 -> null

Unsorted: 7 -> 3 -> 9 -> 1 -> 3 -> 8 -> null
sort_list: 1 -> 3 -> 3 -> 7 -> 8 -> 9 -> null

List 1: 1 -> 4 -> 6 -> 10 -> null
List 2: 2 -> 3 -> 6 -> 12 -> 15 -> null
merge_lists: 1 -> 2 -> 3 -> 4 -> 6 -> 6 -> 10 -> 12 -> 15 -> null
merge_lists with the unsorted list: 1 -> 1 -> 3 -> 3 -> 4 -> 6 -> 7 -> 8 -> 9 -> 10 -> null
merge_lists with an empty list: 2 -> 3 -> 6 -> 12 -> 15 -> null
```

`main` covers the cases that matter for each method: `get` at the first position, the last position
and past the end; `insert_sorted` in the middle, at the end, before the head and with a value already
in the list; `sort_list` with duplicates; and `merge_lists` with sorted lists, an unsorted list and an
empty list.

### Behaviour notes

- **`insert_sorted` and the head.** The method is `void`, so it can't return a new
  head, and Java passes the `head` reference by value, so assigning to it inside the method changes
  nothing for the caller. When the new value is smaller than the head, the method puts a new node
  second in the list holding the head's old value, and writes the new value into the head node. The
  list ends up correct and the caller's `head` still points at the first node. For the same reason the
  method can't add to an empty list (`head == null`), so `sort_list` and `merge_lists` create the
  first node themselves.
- **`get` out of range.** An index below 1 or past the end of the list returns -1.
- **`merge_lists` input.** The method doesn't rely on the two lists being sorted and inserts every
  element into its place. This is O(n²) where a merge of two
  sorted lists could be O(n), but the result is right for any input.

## Task 6: word count with a HashMap

`WordCount` reads the whole file, lowercases it, and splits it into words with `split("\\W+")` as the
lab suggests. If the file is the Project Gutenberg edition, only the novel itself is counted:
the text between the line starting `*** START` and the line starting `*** END`. A file without those
two lines is counted in full.

Each word goes into a `HashMap<String, Integer>` with `containsKey` and `put`: 1 the first time it is
seen, and one higher each time after that. The program then goes through `entrySet()` and keeps the
six-letter word with the highest count.

### Result

```
Words in the text: 80493
Different words: 6843
Most frequent six-letter word: dorian (415 times)
```

The most frequent six-letter word is **dorian**. The next ones are a long way behind (`things` 128,
`seemed` 119).

### Tests run

| File | Output | Checks |
|---|---|---|
| the novel | `dorian (415 times)` | counts agree with a separate count of the same file |
| text before `*** START`, a title on the marker line, text after `*** END` | only the words between the two lines are counted | header, marker line and licence left out |
| file with a `*** START` line but no `*** END` line | whole file counted | incomplete markers |
| `Hello, hello! Six-letter words: yellow yellow purple. _x_ 42` | 10 words, 8 different, `yellow (2 times)` | case folding, punctuation, repeated words |
| `a bb ccc` | `There are no six-letter words in the text` | no word of the right length |
| empty file | 0 words, no six-letter words | empty input |
| no file | `Could not find dorian.txt, ...` | missing file |

### Notes

- `dorian.txt` isn't included in this folder. The counts above are from the plain text edition on
  [Project Gutenberg](https://www.gutenberg.org/ebooks/174) (ebook 174), saved as UTF-8. That file
  has Project Gutenberg's own header and licence text around the novel, which the program leaves out.
  The title page, preface and chapter headings are between the two marker lines, so they are counted.
  A different edition of the text may give slightly different totals.
- `\\W+` treats letters, digits and the underscore as part of a word, so a hyphenated word counts as
  two words and `don't` becomes `don` and `t`.
