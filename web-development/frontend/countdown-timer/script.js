let timeLeft = 60;
let timerId = null;

function startTimer() {
  if (timerId !== null) return;

  // buttons switch state as soon as start is clicked, not on the first tick
  document.getElementById("Start").disabled = true;
  document.getElementById("Pause").disabled = false;
  document.getElementById("Stop").disabled = false;

  timerId = setInterval(() => {
    timeLeft--;

    document.getElementById("timer").textContent = timeLeft;

    if(timeLeft <= 15){
        document.getElementById("timer").classList.add("critical");
    }

    if (timeLeft <= 0) {
        alert("take a short break!");
        stopTimer();
    }
  }, 1000);
}

function stopTimer(){
    document.getElementById("timer").classList.remove("critical");
    clearInterval(timerId);
    timerId = null;
    timeLeft = 60;
    document.getElementById("timer").textContent = timeLeft;
    document.getElementById("Start").disabled = false;
    document.getElementById("Pause").disabled = true;
    document.getElementById("Stop").disabled = true;
    // stopping while paused would leave the button saying "Resume"
    document.getElementById("Pause").textContent = "Pause";
}

function pauseTimer(){
    if(document.getElementById("Pause").textContent == "Pause"){
        clearInterval(timerId);
        document.getElementById("Pause").textContent = "Resume";
    }else if(document.getElementById("Pause").textContent == "Resume"){
        timerId = null;
        startTimer();
        document.getElementById("Pause").textContent = "Pause";
    }
}