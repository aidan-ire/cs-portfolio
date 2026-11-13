# Assignment 3: countdown timer

A one-minute countdown shown on an apple, with Start, Pause and Stop buttons. When it reaches zero the
page shows an alert saying to take a short break.

| File | What it is |
|---|---|
| `index.html` | nav with a Timer button, the apple with the `#timer` paragraph over it, three buttons |
| `styles.css` | page colours, fonts (Roboto and Orbitron from Google Fonts), button and timer styles |
| `script.js` | `startTimer`, `stopTimer` and `pauseTimer` |
| `apple.svg` | the apple, drawn as an SVG with a transparent background |
| `proof.png` | the page at 1280px wide |

## Run

Open `index.html` in a browser. The fonts load from Google Fonts, so without a connection the page
falls back to a sans-serif font.

## How it works

- `timeLeft` starts at 60. `timerId` holds the `setInterval` handle while a countdown is in progress
  and is `null` otherwise, which is how `startTimer` avoids starting a second interval.
- **Start** disables itself, enables Pause and Stop, and starts an interval that takes one off
  `timeLeft` every second.
- At 15 seconds or less the timer gets the `critical` class: red text on a light background with 5px
  rounded corners.
- At 0 the alert is shown and `stopTimer` runs.
- **Pause** clears the interval and changes its own label to Resume. Clicking it again starts a new
  interval from the same `timeLeft` and changes the label back.
- **Stop** clears the interval, resets the display to 60, removes `critical`, puts the Pause label
  back and returns the buttons to their starting state (only Start enabled).
- The timer text is white, and yellow while the mouse is over it.

## Checked

Run in Chrome by calling the handlers and reading the page state:

| Step | Timer | Start | Pause | Stop |
|---|---|---|---|---|
| page load | 60 | enabled | disabled | disabled |
| straight after Start | 60 | disabled | enabled | enabled |
| 2 seconds later | 58 | disabled | enabled | enabled |
| Pause, wait 1.5 seconds | 58 | disabled | enabled, says Resume | enabled |
| Resume, 1 second later | 57 | disabled | enabled, says Pause | enabled |
| Pause, then Stop | 60 | enabled | disabled, says Pause | disabled |
| Start again, measured over 3 seconds | 3 ticks | | | |
| reaching 15 | `critical` class on | | | |
| reaching 0 | alert "take a short break!", back to 60 | enabled | disabled | disabled |

## Notes

- The specification's file name `styles.css` is used.
- The Timer button in the nav wraps a link back to this page.
