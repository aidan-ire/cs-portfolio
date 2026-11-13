const sqlite3 = require('sqlite3').verbose();
const fs = require('fs');
const path = require('path');

const dbPath = path.resolve(__dirname, 'data/app.db');

//  Successfully connect to SQLite
const db = new sqlite3.Database(dbPath, (err) => {
    if (err) {
        console.error('Error connecting to the SQLite database:', err.message);
    } else {
        console.log('Connected to the SQLite database (data/app.db).');

        // SQLite only enforces foreign keys (and ON DELETE CASCADE) when this is switched on for the connection
        db.run('PRAGMA foreign_keys = ON');
        
        //  Read and run the model.sql file to initialize tables and data
        const sqlFilePath = path.resolve(__dirname, 'model.sql');
        
        try {
            const sqlQuery = fs.readFileSync(sqlFilePath, 'utf8');
            
            // db.exec() runs the multiple SQL statements found in model.sql
            db.exec(sqlQuery, (execErr) => {
                if (execErr) {
                    console.error('Error executing model.sql:', execErr.message);
                } else {
                    console.log('model.sql executed successfully. Tables and initial data are ready.');
                }
            });
        } catch (readErr) {
            console.error('Could not read model.sql file:', readErr.message);
        }
    }
});

module.exports = db;