# Assignment 4: book management API

A REST API for a list of books, built with Express and stored in SQLite. There is no frontend; every
response is JSON.

| File | What it is |
|---|---|
| `server.js` | the whole server: database setup and the five routes |
| `database.db` | the SQLite database, holding three books |
| `package.json`, `package-lock.json` | dependencies: `express` and `sqlite3` |
| `Screenshots/` | each request being made from an API client, with the server running underneath |

## Run

```bash
npm install
node server.js
```

The server listens on port 3000 and opens `database.db` relative to the folder it is started from, so
start it from this folder.

## The table

`books` is created with `CREATE TABLE IF NOT EXISTS`: `id` (integer primary key, autoincrement),
`title` and `author` (text, required), `year` (integer, optional) and `status` (text, required). A
`CHECK` constraint limits `status` to `to-read`, `reading` or `completed`, and the POST and PUT routes
check it as well before touching the database.

## Routes

| Method | Path | Success | Errors |
|---|---|---|---|
| GET | `/books` | 200, array of books | |
| GET | `/books?status=reading` | 200, only books with that status | |
| GET | `/books/:id` | 200, one book | 404 if there is no such book |
| POST | `/books` | 201, `{"id": n}` | 400 if `title`, `author` or `status` is missing, or `status` is not allowed |
| PUT | `/books/:id` | 200, message | 400 for a bad `status` or nothing to update, 404 if there is no such book |
| DELETE | `/books/:id` | 200, message | 404 if there is no such book |

PUT changes `title`, `year` and `status` only; an `author` in the body is ignored. All queries use
`?` placeholders. Unknown routes return a JSON 404, and a body that is not valid JSON returns a JSON
400.

## Checked

Run with `curl` against a copy of this folder, so the database here is unchanged.

```
$ curl localhost:3000/books/1
{"id":1,"title":"The Hobbit","author":"J.R.R. Tolkien","year":1937,"status":"to-read"}   [200]
$ curl localhost:3000/books/999
{"error":"Book not found"}   [404]
$ curl "localhost:3000/books?status=reading"
[]   [200]
$ curl -X POST localhost:3000/books -H "Content-Type: application/json" \
       -d '{"title":"Dune","author":"Frank Herbert","year":1965,"status":"reading"}'
{"id":5}   [201]
$ curl "localhost:3000/books?status=reading"
[{"id":5,"title":"Dune","author":"Frank Herbert","year":1965,"status":"reading"}]   [200]
$ curl -X POST localhost:3000/books -H "Content-Type: application/json" -d '{"title":"X","author":"A"}'
{"error":"Title, author, and status are required"}   [400]
$ curl -X POST localhost:3000/books -H "Content-Type: application/json" \
       -d '{"title":"X","author":"A","status":"done"}'
{"error":"Invalid status. Must be 'to-read', 'reading', or 'completed'."}   [400]
$ curl -X PUT localhost:3000/books/5 -H "Content-Type: application/json" \
       -d '{"title":"Dune Messiah","year":1969,"status":"completed","author":"Someone Else"}'
{"message":"Book updated successfully"}   [200]
$ curl localhost:3000/books/5
{"id":5,"title":"Dune Messiah","author":"Frank Herbert","year":1969,"status":"completed"}   [200]
$ curl -X PUT localhost:3000/books/999 -H "Content-Type: application/json" -d '{"title":"x"}'
{"error":"Book not found"}   [404]
$ curl -X DELETE localhost:3000/books/5
{"message":"Book deleted successfully"}   [200]
$ curl -X DELETE localhost:3000/books/5
{"error":"Book not found"}   [404]
$ curl -X POST localhost:3000/books -H "Content-Type: application/json" -d '{bad json'
{"error":"Invalid JSON in request body"}   [400]
$ curl localhost:3000/nothing
{"error":"Route not found"}   [404]
```

Persistence: a second book (`Emma`, id 6) was added, the server was stopped and started again, and
`GET /books` returned the three original books and `Emma`.

## Notes

- New books get id 5 onwards because id 4 was used by a book that has since been deleted, and
  `AUTOINCREMENT` does not reuse ids.
- All three books in `database.db` are `to-read`, so `?status=reading` is empty until one is added
  or updated.
- PUT skips a field whose value is falsy, so `year` cannot be set to 0 and `title` cannot be emptied.
- `?status=` with a value outside the three allowed ones returns an empty array, not an error.
