package no.uib.inf101.sudoku.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.model.Difficulty;
import no.uib.inf101.sudoku.model.SudokuBoard;

/**
 * This interface represents a viewable Sudoku model.
 * It provides methods to retrieve information about the Sudoku grid,
 * set the initial board configuration, and manage game settings.
 */
public interface ViewableSudokuModel {

    /**
     * Returns the dimension of the Sudoku grid.
     *
     * @return the grid dimension
     */
    GridDimension getDimension();

    /**
     * Returns an iterable collection of all the grid cells in the Sudoku grid.
     *
     * @return an iterable collection of grid cells
     */
    Iterable<GridCell> getAllTiles();

    /**
     * Returns the difficulty level of the Sudoku game.
     *
     * @return the difficulty level
     */
    Difficulty getDifficulty();

    /**
     * Returns the current state of the Sudoku board.
     *
     * @return the Sudoku board
     */
    SudokuBoard getBoard();

    /**
     * Sets the initial board configuration with the given playable and solved
     * boards.
     *
     * @param playableBoard the initial playable board configuration
     * @param solvedBoard   the solved board configuration
     */
    void setBoard(int[][] playableBoard, int[][] solvedBoard);

    /**
     * Sets the time elapsed in the Sudoku game.
     *
     * @param time the time elapsed in seconds
     */
    void setTime(int time);

    /**
     * Sets the number of remaining lives in the Sudoku game.
     *
     * @param lifes the number of remaining lives
     */
    void setLifes(int lifes);

    /**
     * Saves the current game state.
     */
    void saveGame();

    /**
     * Starts a new Sudoku game with the specified difficulty level.
     *
     * @param difficulty the difficulty level of the game
     */
    void startGame(Difficulty difficulty);

}
