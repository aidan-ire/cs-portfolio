const express = require('express');
const router = express.Router();
const SongController = require('../controllers/songController');

router.get('/', SongController.getAllSongs);
router.get('/:id', SongController.getOneSong);
router.post('/', SongController.createSong);
router.patch('/:id', SongController.updateSong); 
router.delete('/:id', SongController.deleteSong);

module.exports = router;