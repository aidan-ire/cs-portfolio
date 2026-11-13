# Responsive study dashboard

A single-page study dashboard in HTML, CSS and JavaScript. It has a profile card, a weekly goals card
and a study status card, changes layout at 700px, and has a few small interactive parts.

| File | What it is |
|---|---|
| `index.html` | the page: header with nav, three `section` cards, footer |
| `style.css` | layout, hover effects, the status label and the media query |
| `script.js` | welcome message, session counter, goals button, status toggle |
| `profile_placeholder.svg` | placeholder for the profile picture |
| `screenshot-desktop.jpg` | the page at 1280px wide |
| `screenshot-mobile.jpg` | the page at 375px wide |

## Run

Open `index.html` in a browser. Open the console to see the logged output.

## What it does

- **Layout.** On a wide screen the nav links are in a row and the three cards sit side by side. At
  700px or narrower the cards stack in a column, the nav links become vertical, text and gaps get
  smaller, and the profile picture is hidden.
- **Status label.** The word after "Status:" is not in the HTML. It comes from a `::after`
  pseudo-element on `#indicator`, green for Active and orange for Pending.
- **Hover.** Nav links change colour and get a wavy underline. Cards scale up slightly and gain a
  shadow.
- **Welcome message.** `script.js` writes `Welcome, Aidan!` into an empty paragraph with
  `textContent`.
- **Study counter.** Add Session, Decrease Session and Reset change `count` and update the page.
  The count does not go below 0.
- **Goals.** The card lists four goals. Show Goals loops over the three goals in the `goals` array,
  adds each one to a second list under the first and logs it to the console; clicking again hides
  that second list.
- **Status.** Change Status switches the label between Active and Pending.
- **Decision.** `hoursStudied` is 3, so the console shows `Good progress` when the page loads.

## Checked

In Chrome:

| Width | Cards | Nav links | Profile picture |
|---|---|---|---|
| 1280px | one row of three, each 360px wide | row | shown |
| 690px | one column | column | hidden |
| 375px | one column, no horizontal scrolling | column | hidden |

The welcome text, counter (add, add, reset gives 2 then 0), goals button (three goals shown, then
hidden) and status label (Active, then Pending, then Active) were also checked from the console.

## Notes

- The profile picture is a placeholder drawing.
- The nav links have empty `href`s; the specification says they do not need to go anywhere.
