// address of the backend API
const API_URL = "http://localhost:3000";

// ID of the album loaded into the form, null when the form is for a new album
let selectedId = null;

// artistID -> artistName, filled from /artists so the table can show names
let artistNames = {};

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
        albumName: document.getElementById("album_name").value.trim(),
        releaseYear: Number(document.getElementById("release_year").value),
        numOfListens: Number(document.getElementById("num_of_listens").value),
        artistID: Number(document.getElementById("artist_id").value)
    };
}

// checks the form before anything is sent, returns false and shows why if something is missing
function formIsValid(album){
    if(album.albumName === "" || album.releaseYear === 0 || album.artistID === 0){
        showMessage("Please fill in every field.", true);
        return false;
    }
    if(album.numOfListens <= 0){
        showMessage("Number of listens must be more than 0.", true);   // the API rejects 0 as well
        return false;
    }
    return true;
}

// empties the form and goes back to create mode
function clearForm(){
    document.getElementById("album_form").reset();
    selectedId = null;
    document.getElementById("create_button").disabled = false;
    document.getElementById("update_button").disabled = true;
}

// copies one album into the form so it can be updated
function editAlbum(album){
    selectedId = album.albumID;
    document.getElementById("album_name").value = album.albumName;
    document.getElementById("release_year").value = album.releaseYear;
    document.getElementById("num_of_listens").value = album.numOfListens;
    document.getElementById("artist_id").value = album.artistID;
    document.getElementById("create_button").disabled = true;
    document.getElementById("update_button").disabled = false;
    showMessage("Editing album " + album.albumID + ".", false);
}

// adds one cell of text to a table row
function addCell(row, text){
    const cell = document.createElement("td");
    cell.textContent = text;
    row.appendChild(cell);
}

// builds one table row for an album, with its edit and delete buttons
function buildRow(album){
    const row = document.createElement("tr");
    addCell(row, album.albumID);
    addCell(row, album.albumName);
    addCell(row, album.releaseYear);
    addCell(row, album.numOfListens.toLocaleString());
    addCell(row, artistNames[album.artistID] + " (" + album.artistID + ")");

    const actions = document.createElement("td");

    const editButton = document.createElement("button");
    editButton.textContent = "Edit";
    editButton.addEventListener("click", function(){ editAlbum(album); });
    actions.appendChild(editButton);

    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.className = "delete_button";
    deleteButton.addEventListener("click", function(){ deleteAlbum(album.albumID); });
    actions.appendChild(deleteButton);

    row.appendChild(actions);
    return row;
}

// fills the artist dropdown, an album has to belong to an artist that exists
async function loadArtistOptions(){
    const result = await sendRequest("/artists", "GET");
    const select = document.getElementById("artist_id");
    select.textContent = "";
    artistNames = {};

    for(const artist of result.data){
        artistNames[artist.artistID] = artist.artistName;

        const option = document.createElement("option");
        option.value = artist.artistID;
        option.textContent = artist.artistName;
        select.appendChild(option);
    }
}

// RETRIEVE: gets every album and redraws the table
async function loadAlbums(){
    try{
        await loadArtistOptions();   // names are needed before the rows are built

        const result = await sendRequest("/albums", "GET");
        const rows = document.getElementById("album_rows");
        rows.textContent = "";   // clears the old rows

        for(const album of result.data){
            rows.appendChild(buildRow(album));
        }
    }catch(err){
        showMessage("Could not load albums: " + err.message, true);
    }
}

// CREATE: posts the form as a new album
async function createAlbum(){
    const album = readForm();
    if(!formIsValid(album)) return;

    try{
        const result = await sendRequest("/albums", "POST", album);
        clearForm();
        showMessage("Created album " + result.data.albumID + ".", false);
        loadAlbums();
    }catch(err){
        showMessage("Create failed: " + err.message, true);
    }
}

// UPDATE: the albums route takes PUT, the other two take PATCH
async function updateAlbum(){
    if(selectedId === null) return;

    const album = readForm();
    if(!formIsValid(album)) return;

    try{
        const id = selectedId;
        await sendRequest("/albums/" + id, "PUT", album);
        clearForm();
        showMessage("Updated album " + id + ".", false);
        loadAlbums();
    }catch(err){
        showMessage("Update failed: " + err.message, true);
    }
}

// DELETE: the database cascades, so the album's songs go too
async function deleteAlbum(id){
    if(!confirm("Delete album " + id + "? Its songs will be deleted too.")) return;

    try{
        await sendRequest("/albums/" + id, "DELETE");
        if(selectedId === id) clearForm();
        showMessage("Deleted album " + id + ".", false);
        loadAlbums();
    }catch(err){
        showMessage("Delete failed: " + err.message, true);
    }
}

loadAlbums();
