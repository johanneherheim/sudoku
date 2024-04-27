# Semesteroppgave 2

## Before playing

In [_this_](src/main/java/no/uib/inf101/sudoku/model/Generator.java) file, the number of empty cells for each level is set. Depending on your computer, you may need to adjust the numbers accordingly.

```java
private static final int EASY_CELLS_REMOVED = 30;
private static final int MEDIUM_CELLS_REMOVED = 40;
private static final int HARD_CELLS_REMOVED = 55;
```

## Scripts

This makes it possible to run scripts by calling on aliases:

```bash
source .zshrc
```

This one makes git skip the worktree for the database files:

```bash
setup
```

I use this command to push to both gitlab and github:

```bash
push
```

This command resets the setup:

```bash
reset
```

## How to play

The game has 6 different game pages:

- Welcome page
- Menu page
- Game page
- Game over page
- Leaderboard page
- My-scores page

#### Welcome page

Press the `Start` button to begin.

#### Menu page

Here, you can choose between three difficulties and navigate to the leaderboard. This page also serves as a pause screen. If paused, there is a `Fortsett` button at the top left. You can also exit the game by clicking `Avslutt`.

#### Game page

You have 3 lives, and there is a timer logging how much time you spend. You can navigate through the cells with the mouse or arrow keys. You can only input a number in an empty cell, and if you input the wrong number, the cell turns red and you lose a life. You win by solving the sudoku and lose by running out of lives.

#### Game over page

Here, the user can choose to save their score or discard it. After making a choice, the user is redirected back to the menu.

#### My scores page

In the menu, you can view your top 15 scores as well as the top 15 scores of all users. These tables are updated immediately when you save a new score.
