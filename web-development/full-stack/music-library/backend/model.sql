-- Artist Table
CREATE TABLE IF NOT EXISTS artists (
    artistID INTEGER PRIMARY KEY AUTOINCREMENT,
    artistName TEXT NOT NULL,
    genre TEXT NOT NULL,
    monthlyListeners INTEGER NOT NULL
);

-- Album Table
CREATE TABLE IF NOT EXISTS albums (
    albumID INTEGER PRIMARY KEY AUTOINCREMENT,
    albumName TEXT NOT NULL,
    releaseYear INTEGER NOT NULL,
    numOfListens INTEGER NOT NULL,
    artistID INTEGER, 
    CONSTRAINT FK_artistID
        FOREIGN KEY (artistID) 
        REFERENCES artists(artistID)
        ON DELETE CASCADE
);

-- Song Table
CREATE TABLE IF NOT EXISTS songs (
    songID INTEGER PRIMARY KEY AUTOINCREMENT,
    songName TEXT NOT NULL,
    releaseYear INTEGER NOT NULL,
    albumID INTEGER,
    CONSTRAINT FK_albumID 
        FOREIGN KEY (albumID) 
        REFERENCES albums(albumID)
        ON DELETE CASCADE
);

-- Initial data
-- sqlite_sequence gets a row for a table the first time something is inserted into it and keeps
-- it after deletes, so each block below only runs once and deleted rows stay deleted after a restart

INSERT INTO artists (artistID, artistName, genre, monthlyListeners)
SELECT * FROM (
    SELECT 1, 'Cavetown', 'Indie Pop', 8500000
    UNION ALL SELECT 2, 'Mother Mother', 'Indie Rock', 12000000
    UNION ALL SELECT 3, 'SAULT', 'R&B/Soul', 2300000
    UNION ALL SELECT 4, 'Mac Miller', 'Hip Hop', 28000000
    UNION ALL SELECT 5, 'Beyonce', 'Pop', 10000000
)
WHERE NOT EXISTS (SELECT 1 FROM sqlite_sequence WHERE name = 'artists');

INSERT INTO albums (albumID, albumName, releaseYear, numOfListens, artistID)
SELECT * FROM (
    SELECT 1, 'Lemon Boy', 2018, 250000000, 1
    UNION ALL SELECT 2, 'O My Heart', 2008, 890000000, 2
    UNION ALL SELECT 3, 'Untitled (Black Is)', 2020, 150000000, 3
    UNION ALL SELECT 4, 'Swimming', 2018, 1200000000, 4
    UNION ALL SELECT 5, 'Circles', 2020, 950000000, 4
)
WHERE NOT EXISTS (SELECT 1 FROM sqlite_sequence WHERE name = 'albums');

INSERT INTO songs (songID, songName, releaseYear, albumID)
SELECT * FROM (
    SELECT 1, 'Lemon Boy', 2018, 1
    UNION ALL SELECT 2, 'Fool', 2018, 1
    UNION ALL SELECT 3, 'Hayloft', 2008, 2
    UNION ALL SELECT 4, 'Ghosting', 2008, 2
    UNION ALL SELECT 5, 'Wildfires', 2020, 3
    UNION ALL SELECT 6, 'Bow', 2020, 3
    UNION ALL SELECT 7, 'Self Care', 2018, 4
    UNION ALL SELECT 8, '2009', 2018, 4
    UNION ALL SELECT 9, 'Good News', 2020, 5
    UNION ALL SELECT 10, 'Blue World', 2020, 5
)
WHERE NOT EXISTS (SELECT 1 FROM sqlite_sequence WHERE name = 'songs');
