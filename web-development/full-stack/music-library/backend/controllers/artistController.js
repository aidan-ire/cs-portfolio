const db = require('../db'); 

// Get all artists 
exports.getAllArtists = (req, res) => {
    const sql = `SELECT * FROM artists`;
    
    db.all(sql, [], (err, rows) => {
        if (err) {
            return res.status(500).json({ ok: false, error: { message: "Server Error" } });
        }
        res.status(200).json({
            ok: true,
            data: rows
        });
    });
};

// Get one artist by ID 
exports.getOneArtist = (req, res) => {
    const { id } = req.params;
    const sql = `SELECT * FROM artists WHERE artistID = ?`;
    
    db.get(sql, [id], (err, row) => {
        if (err) {
            return res.status(500).json({ ok: false, error: { message: "Server Error" } });
        }
        if (!row) {
            return res.status(404).json({ ok: false, error: { message: "Artist not found" } });
        }
        res.status(200).json({
            ok: true,
            data: row
        });
    });
};

// Create one artist 
exports.createArtist = (req, res) => {
    const { artistName, genre, monthlyListeners } = req.body;

    // Validate input
    if (!artistName || !genre || monthlyListeners === undefined) {
        return res.status(400).json({ ok: false, error: { message: "Missing required fields" } });
    }

    const sql = `INSERT INTO artists (artistName, genre, monthlyListeners) VALUES (?, ?, ?)`;
    
    db.run(sql, [artistName, genre, monthlyListeners], function(err) {
        if (err) {
            return res.status(400).json({ ok: false, error: { message: "Invalid Input or Database Error" } });
        }

        res.status(201).json({
            ok: true,
            data: {
                artistID: this.lastID, 
                artistName,
                genre,
                monthlyListeners
            }
        });
    });
};

// Update one artist by ID 
exports.updateArtist = (req, res) => {
    const { id } = req.params;
    const { artistName, genre, monthlyListeners } = req.body;

    const sql = `
        UPDATE artists 
        SET artistName = COALESCE(?, artistName), 
            genre = COALESCE(?, genre), 
            monthlyListeners = COALESCE(?, monthlyListeners)
        WHERE artistID = ?
    `;

    db.run(sql, [artistName, genre, monthlyListeners, id], function(err) {
        if (err) {
            return res.status(400).json({ ok: false, error: { message: "Update failed" } });
        }
        if (this.changes === 0) {
            return res.status(404).json({ ok: false, error: { message: "Artist not found" } });
        }
        
        res.status(200).json({
            ok: true,
            message: "Artist updated successfully"
        });
    });
};

// Delete one artist by ID 
exports.deleteArtist = (req, res) => {
    const { id } = req.params;
    const sql = `DELETE FROM artists WHERE artistID = ?`;

    db.run(sql, [id], function(err) {
        if (err) {
            return res.status(500).json({ ok: false, error: { message: "Server Error" } });
        }
        if (this.changes === 0) {
            return res.status(404).json({ ok: false, error: { message: "Artist not found" } });
        }

        res.status(200).json({
            ok: true,
            message: "Artist deleted successfully"
        });
    });
};