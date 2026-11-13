// address of the backend API
const API_URL = "http://localhost:3000";

// ID of the song loaded into the form, null when the form is for a new song
let selectedId = null;

// albumID -> albumName, filled from /albums so the table can show names
let albumNames = {};

// shows the result of the last request under the form
function showMessage(text, isError){
    const message = document.getElementById("message");
    message.textContent = text;
    message.className = isError ? "error" : "";
}

// sends a request and returns the parsed JSON body, throws with the API's message on an error status
async function sendRequest(path, method, body){
    const options = { method: method, headers: { "Content-Type": "application/json" } };
    if(body !== undefined) options.body = JSON.stringify(body);

    const response = await fetch(API_URL + path, options);
    const result = await response.json();

    if(!response.ok) throw new Error(result.error.message);
    return result;
}

// reads the form inputs into an object with the field names the API expects
function readForm(){
    return {
        songName: document.getElementById("song_name").value.trim(),
        releaseYear: Number(document.getElementById("release_year").value),
        albumID: Number(document.getElementById("album_id").value)
    };
}

// checks the form before anything is sent, returns false and shows why if something is missing
function formIsValid(song){
    if(song.songName === "" || song.releaseYear === 0 || song.albumID === 0){
        showMessage("Please fill in every field.", true);
        return false;
    }
    return true;
}

// empties the form and goes back to create mode
function clearForm(){
    document.getElementById("song_form").reset();
    selectedId = null;
    document.getElementById("create_button").disabled = false;
    document.getElementById("update_button").disabled = true;
}

// copies one song into the form so it can be updated
function editSong(song){
    selectedId = song.songID;
    document.getElementById("song_name").value = song.songName;
    document.getElementById("release_year").value = song.releaseYear;
    document.getElementById("album_id").value = song.albumID;
    document.getElementById("create_button").disabled = true;
    document.getElementById("update_button").disabled = false;
    showMessage("Editing song " + song.songID + ".", false);
}

// adds one cell of text to a table row
function addCell(row, text){
    const cell = document.createElement("td");
    cell.textContent = text;
    row.appendChild(cell);
}

// builds one table row for a song, with its edit and delete buttons
function buildRow(song){
    const row = document.createElement("tr");
    addCell(row, song.songID);
    addCell(row, song.songName);
    addCell(row, song.releaseYear);
    addCell(row, albumNames[song.albumID] + " (" + song.albumID + ")");

    const actions = document.createElement("td");

    const editButton = document.createElement("button");
    editButton.textContent = "Edit";
    editButton.addEventListener("click", function(){ editSong(song); });
    actions.appendChild(editButton);

    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.className = "delete_button";
    deleteButton.addEventListener("click", function(){ deleteSong(song.songID); });
    actions.appendChild(deleteButton);

    row.appendChild(actions);
    return row;
}

// fills the album dropdown, a song has to belong to an album that exists
async function loadAlbumOptions(){
    const result = await sendRequest("/albums", "GET");
    const select = document.getElementById("album_id");
    select.textContent = "";
    albumNames = {};

    for(const album of result.data){
        albumNames[album.albumID] = album.albumName;

        const option = document.createElement("option");
        option.value = album.albumID;
        option.textContent = album.albumName;
        select.appendChild(option);
    }
}

// RETRIEVE: gets every song and redraws the table
async function loadSongs(){
    try{
        await loadAlbumOptions();   // names are needed before the rows are built

        const result = await sendRequest("/songs", "GET");
        const rows = document.getElementById("song_rows");
        rows.textContent = "";   // clears the old rows

        for(const song of result.data){
            rows.appendChild(buildRow(song));
        }
    }catch(err){
        showMessage("Could not load songs: " + err.message, true);
    }
}

// CREATE: posts the form as a new song
async function createSong(){
    const song = readForm();
    if(!formIsValid(song)) return;

    try{
        const result = await sendRequest("/songs", "POST", song);
        clearForm();
        showMessage("Created song " + result.data.songID + ".", false);
        loadSongs();
    }catch(err){
        showMessage("Create failed: " + err.message, true);
    }
}

// UPDATE: the songs route takes PATCH
async function updateSong(){
    if(selectedId === null) return;

    const song = readForm();
    if(!formIsValid(song)) return;

    try{
        const id = selectedId;
        await sendRequest("/songs/" + id, "PATCH", song);
        clearForm();
        showMessage("Updated song " + id + ".", false);
        loadSongs();
    }catch(err){
        showMessage("Update failed: " + err.message, true);
    }
}

// DELETE: removes one song
async function deleteSong(id){
    if(!confirm("Delete song " + id + "?")) return;

    try{
        await sendRequest("/songs/" + id, "DELETE");
        if(selectedId === id) clearForm();
        showMessage("Deleted song " + id + ".", false);
        loadSongs();
    }catch(err){
        showMessage("Delete failed: " + err.message, true);
    }
}

loadSongs();
