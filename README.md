# Semesteroppgave 2

1. run this command:

```bash
source .zshrc
```

2. run all scripts for setting up with this command

```bash
setup
```

3. push the changes to github and gitlab

```bash
push
```

running this command resets the setup:

```bash
reset
```

## How to play

The game has 6 different game-pages.

- welcome page
- menu page
- game page
- game over page
- leaderboard page
- my scores page

#### Welcome page

Press the `start` button to start

#### Menu page

Here you can choose between three difficulties, and navigate to the leaderboard. This page also serves as a pause screen, and if so, there is a `Fortsett` button at the top left. You can also exit the game by clicking `Avslutt`

#### Game page

You have 3 lifes, and the timer goes up when this is the current page. It is possible to navigate through the cells with the keyboard-arrowkeys, and the mouse. It is only possible to give a number to a cell that was not given, and it you give a number a wrong number the cell turns red and you loose a life. You win by solving the sudoku and loose by not having any lifes left.

#### Game over page

Here the user can choose to save their score or trash it. When done so, the user gets sent back to the menu.

#### The scores pages

In the menu, it is possible to see both your own top 15 scores, and the top 15 scores to all users. These tables gets updated immediately when you save a new score.
