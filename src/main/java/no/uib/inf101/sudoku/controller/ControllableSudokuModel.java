package no.uib.inf101.sudoku.controller;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.model.GameState;

/**
 * The ControllableSudokuModel interface represents a controllable Sudoku game
 * model.
 * It provides methods to interact with the Sudoku game, such as getting the
 * dimension of the grid,
 * getting the current state of the game, getting the selected cell position,
 * getting the number of remaining lives,
 * getting a cell from a specified position, checking if the Sudoku game is
 * solved, setting the game state,
 * giving a number to a cell, setting the selected cell position, and decreasing
 * the number of lives.
 */
public interface ControllableSudokuModel {

    /**
     * Returns the dimension of the grid.
     *
     * @return the dimension of the grid
     */
    GridDimension getDimension();

    /**
     * Returns the current state of the Sudoku game.
     *
     * @return the current GameState of the Sudoku game
     */
    GameState getGameState();

    /**
     * Returns the currently selected cell position.
     *
     * @return the currently selected cell position
     */
    CellPosition getSelectedCell();

    /**
     * Returns the number of remaining lives.
     *
     * @return the number of remaining lives
     */
    int getLifes();

    /**
     * Gives the cell at the specified position a number.
     *
     * @return the cell at the specified position
     */
    GridCell getCellFromPosition(CellPosition cellPosition);

    /**
     * Checks if the Sudoku game is solved.
     *
     * @return true if the Sudoku game is solved, false otherwise
     */
    boolean isSolved();

    /**
     * Sets the current state of the Sudoku game.
     *
     * @param gameState the new GameState of the Sudoku game
     */
    void setGameState(GameState gameState);

    /**
     * Gives a number to the currently selected cell.
     *
     * @param number the number to be given to the cell
     */
    void giveNumberToCell(Integer number);

    /**
     * Sets the currently selected cell position.
     *
     * @param cellPosition the new selected cell position
     */
    void setSelectedCell(CellPosition cellPosition);

    /**
     * Decreases the number of remaining lives with one.
     */
    void decreaseLifes();
}
