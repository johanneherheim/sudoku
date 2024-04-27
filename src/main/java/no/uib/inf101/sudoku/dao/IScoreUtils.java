package no.uib.inf101.sudoku.dao;

import java.util.List;

import no.uib.inf101.sudoku.model.Score;

/**
 * The IScoreUtils interface provides methods for managing scores in a Sudoku
 * game.
 */
public interface IScoreUtils {

    /**
     * Retrieves all scores from the database.
     * 
     * @return a list of Score objects representing the scores
     */
    List<Score> getAllScores();

    /**
     * Retrieves all scores from a specific user.
     * 
     * @param username the username of the user
     * @return a list of Score objects representing the scores of the user
     */
    List<Score> getScoreFromUser(String username);

    /**
     * Inserts a score into the database.
     * 
     * @param username    the username of the user
     * @param score       the score achieved by the user
     * @param elapsedTime the time taken to complete the game in milliseconds
     * @param lifesUsed   the number of lives used by the user
     * @param hintsUsed   the number of hints used by the user
     * @param startBoard  the starting board configuration of the game
     * @param solvedBoard the solved board configuration of the game
     * @param difficulty  the difficulty level of the game
     */
    void insertScore(String username, Integer score, long elapsedTime, Integer lifesUsed, Integer hintsUsed,
            int[][] startBoard, int[][] solvedBoard, Integer difficulty);

    /**
     * Sorts the scores in the following order: 1. Lifes used 2. Difficulty 3. Time
     * used
     * 
     * @param scores the list of Score objects to be sorted
     * @return a sorted list of Score objects
     */
    List<Score> sortScores(List<Score> scores);

}
