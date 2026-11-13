# Assignment 1: mini profile site

A two-page site in HTML and CSS only: a home page with three cards (about me, skills and a weekly
timetable) and a contact page with a form.

| File | What it is |
|---|---|
| `index.html` | home page: nav, three `section` cards inside a flex container, footer |
| `contact.html` | contact form with labelled name, email and message fields |
| `styles.css` | shared stylesheet for both pages |
| `profile_placeholder.svg` | placeholder for the profile picture |
| `screenshot.jpg` | home page at 1280px wide |
| `screenshot_contact.jpg` | contact page at 1280px wide |

## Run

Open `index.html` in a browser. The nav links between the two pages with relative links.

## How it is laid out

- Both pages use `header`, `nav`, `main`, `section` and `footer`.
- The nav is a flex container with a `gap` between the links.
- The three cards sit in a `.cards` flex container with `gap: 20px` and `flex-wrap: wrap`, so they
  drop onto new rows as the window narrows without a media query.
- Each card uses padding, margin, a border, a border radius and a background colour.
- `body` is a column flex container with `min-height: 100vh`, which keeps the footer at the bottom
  of a short page.

## Checked

In Chrome at 1280px wide: the three cards are in one row, each 300px by 300px, the nav computes to
`display: flex` with a 10px gap, and the card text fits inside its card. Both nav links work from
both pages.

## Notes

- The profile picture is a placeholder drawing.
- The form has separate first name and last name fields. It has no `action`, so submitting it only
  reloads the page.
