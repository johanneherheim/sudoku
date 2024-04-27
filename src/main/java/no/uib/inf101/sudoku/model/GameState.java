package no.uib.inf101.sudoku.model;

/**
 * The GameState enum represents the different states of a Sudoku game.
 * It includes the following states:
 * - WELCOME: The initial state when the game is started.
 * - PLAYING: The state when the player is actively playing the game.
 * - MENU: The state when the player is in the game menu.
 * - FINISHED: The state when the game is finished.
 * - MY_SCORES: The state when the player is viewing their scores.
 * - LEADERBOARD: The state when the player is viewing the leaderboard.
 */
public enum GameState {
    WELCOME, PLAYING, MENU, FINISHED, MY_SCORES, LEADERBOARD
}
