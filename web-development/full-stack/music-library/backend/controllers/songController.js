const db = require('../db'); 

// Get all songs
exports.getAllSongs = (req, res) => {
    const sql = `SELECT * FROM songs`;
    
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

//Get one song by ID
exports.getOneSong = (req, res) => {
    const { id } = req.params;
    const sql = `SELECT * FROM songs WHERE songID = ?`;
    
    db.get(sql, [id], (err, row) => {
        if (err) {
            return res.status(500).json({ ok: false, error: { message: "Server Error" } });
        }
        if (!row) {
            return res.status(404).json({ ok: false, error: { message: "Song not found" } });
        }
        res.status(200).json({
            ok: true,
            data: row
        });
    });
};

// 3. Create one song
exports.createSong = (req, res) => {
    const { songName, releaseYear, albumID } = req.body;

    // Validate input ensuring all required fields are present
    if (!songName || !releaseYear || !albumID) {
        return res.status(400).json({ ok: false, error: { message: "Missing required fields: songName, releaseYear, or albumID" } });
    }

    const sql = `INSERT INTO songs (songName, releaseYear, albumID) VALUES (?, ?, ?)`;
    
    db.run(sql, [songName, releaseYear, albumID], function(err) {
        if (err) {
            return res.status(400).json({ ok: false, error: { message: "Invalid Input or Database Error" } });
        }

        res.status(201).json({
            ok: true,
            data: {
                songID: this.lastID, 
                songName,
                releaseYear,
                albumID
            }
        });
    });
};

//  Update one song by ID 
exports.updateSong = (req, res) => {
    const { id } = req.params;
    const { songName, releaseYear, albumID } = req.body;

    const sql = `
        UPDATE songs 
        SET songName = COALESCE(?, songName), 
            releaseYear = COALESCE(?, releaseYear), 
            albumID = COALESCE(?, albumID)
        WHERE songID = ?
    `;

    db.run(sql, [songName, releaseYear, albumID, id], function(err) {
        if (err) {
            return res.status(400).json({ ok: false, error: { message: "Update failed" } });
        }
        if (this.changes === 0) {
            return res.status(404).json({ ok: false, error: { message: "Song not found" } });
        }
        
        res.status(200).json({
            ok: true,
            message: "Song updated successfully"
        });
    });
};

// Delete one song by ID 
exports.deleteSong = (req, res) => {
    const { id } = req.params;
    const sql = `DELETE FROM songs WHERE songID = ?`;

    db.run(sql, [id], function(err) {
        if (err) {
            return res.status(500).json({ ok: false, error: { message: "Server Error" } });
        }
        if (this.changes === 0) {
            return res.status(404).json({ ok: false, error: { message: "Song not found" } });
        }

        res.status(200).json({
            ok: true,
            message: "Song deleted successfully"
        });
    });
};