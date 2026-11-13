// address of the backend API
const API_URL = "http://localhost:3000";

// ID of the artist loaded into the form, null when the form is for a new artist
let selectedId = null;

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
        artistName: document.getElementById("artist_name").value.trim(),
        genre: document.getElementById("genre").value.trim(),
        monthlyListeners: Number(document.getElementById("monthly_listeners").value)
    };
}

// checks the form before anything is sent, returns false and shows why if something is missing
function formIsValid(artist){
    if(artist.artistName === "" || artist.genre === "" || document.getElementById("monthly_listeners").value === ""){
        showMessage("Please fill in every field.", true);
        return false;
    }
    if(artist.monthlyListeners < 0){
        showMessage("Monthly listeners can't be negative.", true);
        return false;
    }
    return true;
}

// empties the form and goes back to create mode
function clearForm(){
    document.getElementById("artist_form").reset();
    selectedId = null;
    document.getElementById("create_button").disabled = false;
    document.getElementById("update_button").disabled = true;
}

// copies one artist into the form so it can be updated
function editArtist(artist){
    selectedId = artist.artistID;
    document.getElementById("artist_name").value = artist.artistName;
    document.getElementById("genre").value = artist.genre;
    document.getElementById("monthly_listeners").value = artist.monthlyListeners;
    document.getElementById("create_button").disabled = true;
    document.getElementById("update_button").disabled = false;
    showMessage("Editing artist " + artist.artistID + ".", false);
}

// adds one cell of text to a table row
function addCell(row, text){
    const cell = document.createElement("td");
    cell.textContent = text;
    row.appendChild(cell);
}

// builds one table row for an artist, with its edit and delete buttons
function buildRow(artist){
    const row = document.createElement("tr");
    addCell(row, artist.artistID);
    addCell(row, artist.artistName);
    addCell(row, artist.genre);
    addCell(row, artist.monthlyListeners.toLocaleString());

    const actions = document.createElement("td");

    const editButton = document.createElement("button");
    editButton.textContent = "Edit";
    editButton.addEventListener("click", function(){ editArtist(artist); });
    actions.appendChild(editButton);

    const deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.className = "delete_button";
    deleteButton.addEventListener("click", function(){ deleteArtist(artist.artistID); });
    actions.appendChild(deleteButton);

    row.appendChild(actions);
    return row;
}

// RETRIEVE: gets every artist and redraws the table
async function loadArtists(){
    try{
        const result = await sendRequest("/artists", "GET");
        const rows = document.getElementById("artist_rows");
        rows.textContent = "";   // clears the old rows

        for(const artist of result.data){
            rows.appendChild(buildRow(artist));
        }
    }catch(err){
        showMessage("Could not load artists: " + err.message, true);
    }
}

// CREATE: posts the form as a new artist
async function createArtist(){
    const artist = readForm();
    if(!formIsValid(artist)) return;

    try{
        const result = await sendRequest("/artists", "POST", artist);
        clearForm();
        showMessage("Created artist " + result.data.artistID + ".", false);
        loadArtists();
    }catch(err){
        showMessage("Create failed: " + err.message, true);
    }
}

// UPDATE: the artists route takes PATCH
async function updateArtist(){
    if(selectedId === null) return;

    const artist = readForm();
    if(!formIsValid(artist)) return;

    try{
        await sendRequest("/artists/" + selectedId, "PATCH", artist);
        const id = selectedId;
        clearForm();
        showMessage("Updated artist " + id + ".", false);
        loadArtists();
    }catch(err){
        showMessage("Update failed: " + err.message, true);
    }
}

// DELETE: the database cascades, so the artist's albums and their songs go too
async function deleteArtist(id){
    if(!confirm("Delete artist " + id + "? Their albums and songs will be deleted too.")) return;

    try{
        await sendRequest("/artists/" + id, "DELETE");
        if(selectedId === id) clearForm();
        showMessage("Deleted artist " + id + ".", false);
        loadArtists();
    }catch(err){
        showMessage("Delete failed: " + err.message, true);
    }
}

loadArtists();
