# Assignment 5: full-stack music library

A music library with artists, albums and songs. The backend is an Express API over SQLite; the
frontend is plain HTML, CSS and JavaScript that talks to it with `fetch()`.

```
backend/
    server.js           Express app: middleware, routes, static files
    db.js               opens data/app.db and runs model.sql
    model.sql           tables and the initial data
    routes/             artists.js, albums.js, songs.js (express.Router)
    controllers/        artistController.js, albumController.js, songController.js
    data/app.db         the SQLite database
frontend/
    index.html          home page with a count of each kind of record
    artists.html        form and table for artists
    albums.html         form and table for albums
    songs.html          form and table for songs
    styles.css          shared stylesheet
    home.js, artists.js, albums.js, songs.js
screenshot.jpg          home page at 1280px wide
package.json, package-lock.json
```

## Run

```bash
npm install
node backend/server.js
```

Then open `http://localhost:3000/`. `cd backend && node server.js` works too. The server listens on
port 3000 and serves the frontend as static files. The pages call `http://localhost:3000` directly
and the server allows cross-origin requests, so opening the HTML files straight from disk also works
while the server is running.

## Database

| Table | Columns |
|---|---|
| `artists` | `artistID` (primary key), `artistName`, `genre`, `monthlyListeners` |
| `albums` | `albumID` (primary key), `albumName`, `releaseYear`, `numOfListens`, `artistID` → `artists` |
| `songs` | `songID` (primary key), `songName`, `releaseYear`, `albumID` → `albums` |

Both foreign keys are `ON DELETE CASCADE`: deleting an artist deletes their albums, and deleting an
album deletes its songs. SQLite only enforces foreign keys when `PRAGMA foreign_keys = ON` is set on
the connection, which `db.js` does after connecting. With it on, an album cannot be created for an
artist that does not exist, or a song for an album that does not exist.

`model.sql` creates the tables and inserts 5 artists, 5 albums and 10 songs. `db.js` runs the file
every time the server starts, so each insert is guarded: it only runs if `sqlite_sequence` has no row
for that table, which is true only until the first row is ever inserted. Restarting the server
therefore does not duplicate the data or bring back rows that were deleted.

## API

Each of `/artists`, `/albums` and `/songs` has the same five routes.

| Action | Method and path | Success |
|---|---|---|
| get all | `GET /artists` | 200, `{"ok": true, "data": [...]}` |
| get one | `GET /artists/:id` | 200, `{"ok": true, "data": {...}}` |
| create | `POST /artists` | 201, the new record with its id |
| update | `PATCH /artists/:id`, `PUT /albums/:id`, `PATCH /songs/:id` | 200, message |
| delete | `DELETE /artists/:id` | 200, message |

Updates only change the fields that are sent. Errors come back as
`{"ok": false, "error": {"message": "..."}}` with 400 for missing or invalid input, 404 for an id or
route that does not exist, and 500 for a database error.

## Frontend

A navbar links the home page and the three record pages. Each record page has a form, a message line
and a table of everything in that table of the database.

- **Create** posts the form and reloads the table.
- **Edit** on a row copies it into the form and enables **Update**, which sends a PATCH (artists and
  songs) or PUT (albums).
- **Delete** on a row asks for confirmation, sends a DELETE and reloads the table.
- The album form picks its artist, and the song form its album, from a dropdown filled from the API.
- The message line shows the result of the last request, including the API's own error message when
  a request is rejected.

## Checked

Backend, with `curl` against a copy of this folder so the database here is unchanged:

```
$ curl -X POST localhost:3000/artists -H "Content-Type: application/json" \
       -d '{"artistName":"Hozier","genre":"Indie","monthlyListeners":30000000}'
{"ok":true,"data":{"artistID":6,"artistName":"Hozier","genre":"Indie","monthlyListeners":30000000}}   [201]
$ curl -X POST localhost:3000/artists -H "Content-Type: application/json" -d '{"artistName":"x"}'
{"ok":false,"error":{"message":"Missing required fields"}}   [400]
$ curl -X PATCH localhost:3000/artists/6 -H "Content-Type: application/json" -d '{"genre":"Folk"}'
{"ok":true,"message":"Artist updated successfully"}   [200]
$ curl localhost:3000/artists/6
{"ok":true,"data":{"artistID":6,"artistName":"Hozier","genre":"Folk","monthlyListeners":30000000}}   [200]
$ curl localhost:3000/artists/99
{"ok":false,"error":{"message":"Artist not found"}}   [404]
$ curl -X POST localhost:3000/albums -H "Content-Type: application/json" \
       -d '{"albumName":"Wasteland, Baby!","releaseYear":2019,"numOfListens":5000,"artistID":6}'
{"ok":true,"data":{"albumID":6,"albumName":"Wasteland, Baby!","releaseYear":2019,"numOfListens":5000,"artistID":6}}   [201]
$ curl -X POST localhost:3000/albums -H "Content-Type: application/json" \
       -d '{"albumName":"Orphan","releaseYear":2019,"numOfListens":5,"artistID":999}'
{"ok":false,"error":{"message":"Invalid Input or Database Error"}}   [400]
$ curl -X PUT localhost:3000/albums/6 -H "Content-Type: application/json" -d '{"numOfListens":6000}'
{"ok":true,"message":"Album updated successfully"}   [200]
$ curl -X POST localhost:3000/songs -H "Content-Type: application/json" \
       -d '{"songName":"Movement","releaseYear":2019,"albumID":6}'
{"ok":true,"data":{"songID":11,"songName":"Movement","releaseYear":2019,"albumID":6}}   [201]
$ curl -X POST localhost:3000/songs -H "Content-Type: application/json" \
       -d '{"songName":"Orphan","releaseYear":2019,"albumID":999}'
{"ok":false,"error":{"message":"Invalid Input or Database Error"}}   [400]
$ curl -X DELETE localhost:3000/songs/11
{"ok":true,"message":"Song deleted successfully"}   [200]
$ curl -X DELETE localhost:3000/songs/11
{"ok":false,"error":{"message":"Song not found"}}   [404]
$ curl localhost:3000/nothing
{"ok":false,"error":{"message":"Route not found"}}   [404]
```

Cascade and persistence, as ids left in each table:

| Step | Artists | Albums | Songs |
|---|---|---|---|
| after the creates above | 1 to 6 | 1 to 6 | 1 to 11 |
| delete song 11, album 5, artist 1, artist 6 | 2, 3, 4, 5 | 2, 3, 4 | 3 to 8 |
| stop and start the server | 2, 3, 4, 5 | 2, 3, 4 | 3 to 8 |
| delete `data/app.db` and start the server | 1 to 5 | 1 to 5 | 1 to 10 |

After the deletes a query for albums without an artist and songs without an album returned 0 for
both. The server was also started as `node backend/server.js` from this folder and as `node server.js`
from `backend/`, and returned the same data both ways.

Frontend, in Chrome against the same copy: each page loaded its full table (5 artists, 5 albums, 10
songs), then one record was created, edited, updated and deleted through the page on each of the
three pages, with the table and the message line checked after each step. Submitting an empty form,
and an album with 0 listens, showed a message and sent nothing. The home page showed 5, 5 and 10.
The browser console had no errors.

## Notes

- Albums are updated with PUT and artists and songs with PATCH. The frontend uses the matching
  method for each.
- One artist in the initial data has no albums.
- Creating an album needs `numOfListens` to be more than 0.
- An update with no fields in the body returns 200 and changes nothing.
- The frontend has the API address `http://localhost:3000` at the top of each script.
