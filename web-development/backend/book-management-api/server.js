// required dependencies as listed in the assignment
const express = require('express');
const sqlite3 = require('sqlite3').verbose();

//initialize express app and set the port
const app = express();
const PORT = 3000;

// Middleware to parse JSON bodies
app.use(express.json());

// A request sent with no body leaves req.body undefined, so default it to an empty object
app.use((req, res, next) => {
    if (!req.body) req.body = {};
    next();
});

// Initialize the SQLite database
const db = new sqlite3.Database('./database.db', (err) => {
    if (err) {
        console.error("Error opening database " + err.message);
    } else {
        console.log("Connected to the SQLite database.");
        
        // Create the books table if it doesn't exist
        db.run(`CREATE TABLE IF NOT EXISTS books (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            author TEXT NOT NULL,
            year INTEGER,
            status TEXT NOT NULL CHECK(status IN ('to-read', 'reading', 'completed'))
        )`, (err) => {
            if (err) console.error("Table creation error:", err.message);
            else console.log("Table created or already exists.");
        });
    }
});

// GET /books, with optional status query parameter
app.get('/books', (req, res) => {
    const status = req.query.status;
    let sql = 'SELECT * FROM books';
    let params = [];

    if (status) {
        sql += ' WHERE status = ?';
        params.push(status);
    }

    db.all(sql, params, (err, rows) => {
        if (err) return res.status(500).json({ error: err.message });
        res.json(rows);
    });
});

// GET /books/:id, Get a specific book by ID
app.get('/books/:id', (req, res) => {
    const sql = 'SELECT * FROM books WHERE id = ?';
    db.get(sql, [req.params.id], (err, row) => {
        if (err) return res.status(500).json({ error: err.message });
        if (!row) return res.status(404).json({ error: "Book not found" });
        res.json(row);
    });
});

// POST /books, Add a new book to the database
app.post('/books', (req, res) => {
    // takes in the required fields from the request body
    const { title, author, year, status } = req.body;

    // Validates that the required fields are present
    if (!title || !author || !status) {
        return res.status(400).json({ error: "Title, author, and status are required" });
    }

    //validates that the status is one of the allowed values
    const validStatuses = ['to-read', 'reading', 'completed'];
    if (!validStatuses.includes(status)) {
        return res.status(400).json({ error: "Invalid status. Must be 'to-read', 'reading', or 'completed'." });
    }

    const sql = 'INSERT INTO books (title, author, year, status) VALUES (?, ?, ?, ?)';
    db.run(sql, [title, author, year, status], function(err) {
        if (err) return res.status(500).json({ error: err.message });
        res.status(201).json({ id: this.lastID });
    });
});

// PUT /books/:id, Update an existing book's information if one exists
app.put('/books/:id', (req, res) => {
    const { title, year, status } = req.body;
    let updates = [];
    let params = [];

    // Only allow updating title, year, and status
    if (title) { updates.push('title = ?'); params.push(title); }
    if (year) { updates.push('year = ?'); params.push(year); }
    // Validate status if it's provided
    if (status) {
        const validStatuses = ['to-read', 'reading', 'completed'];
        if (!validStatuses.includes(status)) {
            return res.status(400).json({ error: "Invalid status" });
        }
        updates.push('status = ?'); params.push(status);
    }

    // If no valid fields to update are provided, return an error
    if (updates.length === 0) {
        return res.status(400).json({ error: "No valid fields to update provided" });
    }

    params.push(req.params.id);
    const sql = `UPDATE books SET ${updates.join(', ')} WHERE id = ?`;

    db.run(sql, params, function(err) {
        if (err) return res.status(500).json({ error: err.message });
        if (this.changes === 0) return res.status(404).json({ error: "Book not found" });
        res.json({ message: "Book updated successfully" });
    });
});

//DELETE /books/:id, deletes a book from the database if one exists
app.delete('/books/:id', (req, res) => {
    const sql = 'DELETE FROM books WHERE id = ?';
    db.run(sql, [req.params.id], function(err) {
        if (err) return res.status(500).json({ error: err.message });
        if (this.changes === 0) return res.status(404).json({ error: "Book not found" });
        res.json({ message: "Book deleted successfully" });
    });
});

// Any route that isn't defined above gets a JSON 404
app.use((req, res) => {
    res.status(404).json({ error: "Route not found" });
});

// Error handler so errors such as a malformed JSON body are returned as JSON, not an HTML page
app.use((err, req, res, next) => {
    if (err.type === 'entity.parse.failed') {
        return res.status(400).json({ error: "Invalid JSON in request body" });
    }
    res.status(500).json({ error: "Server error" });
});

// Starts the server
app.listen(PORT, () => {
    console.log(`Server listening on port ${PORT}`);
});