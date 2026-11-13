// address of the backend API
const API_URL = "http://localhost:3000";

// asks the API for one list and shows how many records came back
async function loadCount(resource, elementId){
    const response = await fetch(API_URL + "/" + resource);
    const result = await response.json();
    document.getElementById(elementId).textContent = result.data.length;
}

async function loadCounts(){
    try{
        await loadCount("artists", "artist_count");
        await loadCount("albums", "album_count");
        await loadCount("songs", "song_count");
    }catch(err){
        // fetch only throws when the server can't be reached at all
        document.getElementById("message").textContent = "Could not reach the API. Is the server running?";
        document.getElementById("message").className = "error";
    }
}

loadCounts();
