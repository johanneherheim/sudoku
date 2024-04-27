package no.uib.inf101.sudoku.model;

import java.time.LocalDateTime;

/**
 * Represents a score in a Sudoku game.
 * 
 * @param username    the username of the player
 * @param score       the score achieved by the player
 * @param finishedAt  the date and time when the game was finished
 * @param timeUsed    the time used by the player to complete the game
 * @param lifesUsed   the number of lives used by the player
 * @param hintsUsed   the number of hints used by the player
 * @param startBoard  the initial state of the Sudoku board
 * @param solvedBoard the solved state of the Sudoku board
 * @param difficulty  the difficulty level of the Sudoku game
 */
public record Score(String username, Integer score, LocalDateTime finishedAt, Integer timeUsed, Integer lifesUsed,
                Integer hintsUsed, String startBoard, String solvedBoard, Integer difficulty) {

}
