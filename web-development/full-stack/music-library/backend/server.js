const express = require('express');
const cors = require('cors');
const path = require('path');

// Initialize the Express application
const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// A request sent with no body leaves req.body undefined, so default it to an empty object
app.use((req, res, next) => {
    if (!req.body) req.body = {};
    next();
});

// Import Routes
const artistRoutes = require('./routes/artists');
const albumRoutes = require('./routes/albums');
const songRoutes = require('./routes/songs');

// Register Routes
app.use('/artists', artistRoutes);
app.use('/albums', albumRoutes);
app.use('/songs', songRoutes);

app.use(express.static(path.join(__dirname, '../frontend')));

// Any route that isn't defined above gets a JSON 404
app.use((req, res) => {
    res.status(404).json({ ok: false, error: { message: "Route not found" } });
});

// Error handler so errors such as a malformed JSON body are returned as JSON, not an HTML page
app.use((err, req, res, next) => {
    if (err.type === 'entity.parse.failed') {
        return res.status(400).json({ ok: false, error: { message: "Invalid JSON in request body" } });
    }
    res.status(500).json({ ok: false, error: { message: "Server Error" } });
});

app.listen(3000, () => {
    console.log('Server running on port 3000');
});