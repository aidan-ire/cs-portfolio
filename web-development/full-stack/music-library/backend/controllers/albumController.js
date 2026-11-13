const db = require('../db'); 

//  Get all albums 
exports.getAllAlbums = (req, res) => {
    const sql = `SELECT * FROM albums`;
    
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

// Get one album by ID 
exports.getOneAlbum = (req, res) => {
    const { id } = req.params;
    const sql = `SELECT * FROM albums WHERE albumID = ?`;
    
    db.get(sql, [id], (err, row) => {
        if (err) {
            return res.status(500).json({ ok: false, error: { message: "Server Error" } });
        }
        if (!row) {
            return res.status(404).json({ ok: false, error: { message: "Album not found" } });
        }
        res.status(200).json({
            ok: true,
            data: row
        });
    });
};

//  Create one album 
exports.createAlbum = (req, res) => {
    const { albumName, releaseYear, numOfListens, artistID } = req.body;

    // Validate input ensuring all required fields are present
    if (!albumName || !releaseYear || !artistID || numOfListens === undefined || numOfListens <= 0) {
        return res.status(400).json({ ok: false, error: { message: "Missing required fields: albumName, releaseYear, numOfListens, or artistID" } });
    }

    const sql = `INSERT INTO albums (albumName, releaseYear, numOfListens, artistID) VALUES (?, ?, ?, ?)`;
    
    db.run(sql, [albumName, releaseYear, numOfListens, artistID], function(err) {
        if (err) {
            return res.status(400).json({ ok: false, error: { message: "Invalid Input or Database Error" } });
        }

        res.status(201).json({
            ok: true,
            data: {
                albumID: this.lastID,
                albumName,
                releaseYear,
                numOfListens,
                artistID
            }
        });
    });
};

// Update one album by ID
exports.updateAlbum = (req, res) => {
    const { id } = req.params;
    const { albumName, releaseYear, numOfListens, artistID } = req.body;

    const sql = `
        UPDATE albums 
        SET albumName = COALESCE(?, albumName), 
            releaseYear = COALESCE(?, releaseYear), 
            numOfListens = COALESCE(?, numOfListens), 
            artistID = COALESCE(?, artistID)
        WHERE albumID = ?
    `;

    db.run(sql, [albumName, releaseYear, numOfListens, artistID, id], function(err) {
        if (err) {
            return res.status(400).json({ ok: false, error: { message: "Update failed" } });
        }
        if (this.changes === 0) {
            return res.status(404).json({ ok: false, error: { message: "Album not found" } });
        }
        
        res.status(200).json({
            ok: true,
            message: "Album updated successfully"
        });
    });
};

// Delete one album by ID 
exports.deleteAlbum = (req, res) => {
    const { id } = req.params;
    const sql = `DELETE FROM albums WHERE albumID = ?`;

    db.run(sql, [id], function(err) {
        if (err) {
            return res.status(500).json({ ok: false, error: { message: "Server Error" } });
        }
        if (this.changes === 0) {
            return res.status(404).json({ ok: false, error: { message: "Album not found" } });
        }

        res.status(200).json({
            ok: true,
            message: "Album deleted successfully"
        });
    });
};