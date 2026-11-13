// declare count variable for the study session counter
let count = 0;

// Grab references to the DOM elements for the status & indicator
const indicator = document.getElementById('indicator');
const btn = document.getElementById('change_status');
 //Array of possible states
const statuses = ["Pending", "Active"]; 
//tracks which state we are currenty in, the page starts on Active
let statusIndex = 1; 
// Name to be displayed in welcome banner
const userName = "Aidan";
// No of hours studied for progress checker
const hoursStudied = 3;

// Grab references to the DOM elements for the welcome banner
const welcome = document.getElementById('welcome_banner');
// dynamically renders the welcome message plus the name
welcome.textContent = "Welcome, " + userName + "!";

// Grabs references to the DOM elements for the goals lists
const goalsUl = document.getElementById('goals_ul')

//List of weekly goals
const goals = [
    "Read notes", 
    "Finish lab", 
    "Practice JavaScript"
];
 

// Function to display the counter
function updateCount() {
  document.getElementById("count").innerHTML = count;
}

// Function to increase the counter
function increaseCount() {
  count++;
  updateCount();
}

// Function to decrease the counter
function decreaseCount() {
    if(count > 0){
        count--;
        updateCount();
    }
}

// Function to reset the counter
function resetCount() {
  count = 0;
  updateCount();
}

// Function to track and display each state initiated earlier
function cycleStatus(){
    // Move to the next item in the list
    statusIndex++;
    
    // If we hit the end of the list, it goes back to the start
    if (statusIndex >= statuses.length) {
        statusIndex = 0;
    }
    
    // Get the element through DOM references
    const indicator = document.getElementById("indicator");
    
    // Update the attribute with the next state
    indicator.setAttribute("status", statuses[statusIndex]);
}

// Function to show goals list
function showGoals(){

    // if the elements child length is bigger than 0, it resets the list like a toggle
    if(goalsUl.children.length > 0){
        goalsUl.innerHTML = "";
        return;
    }

    // For each goal in the list, it is appended and shown in the html webpage
    for(const goal of goals){
        // Creates new li element ti be shown
        const li = document.createElement('li');
        // adds each goal to the li element just created
        li.textContent = goal;
        // Adds each of the li element to the goalsUL list
        goalsUl.appendChild(li);
        // Displays each goal in the console
        console.log(goal);
    }
}

// Assignment work for progress checker, easy if/else loop
if(hoursStudied >= 3) {
    console.log("Good progress");
}else if(hoursStudied < 3){
    console.log("You should study more");
}
