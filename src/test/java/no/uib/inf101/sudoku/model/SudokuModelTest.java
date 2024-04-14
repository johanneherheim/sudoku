package no.uib.inf101.sudoku.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

public class SudokuModelTest {

    @Test
    public void testInitialState() {

        SudokuModel sudokuModel = new SudokuModel("Test Testesen");

        // Welcome screen
        assertEquals(GameState.WELCOME, sudokuModel.getGameState());

        // The user starts the game by choosing a difficulty
        sudokuModel.start(Difficulty.EASY);
        SudokuBoard easyBoard1 = sudokuModel.getBoard();
        assertEquals(Difficulty.EASY, sudokuModel.getDifficulty());

        sudokuModel.start(Difficulty.EASY);
        SudokuBoard easyBoard2 = sudokuModel.getBoard();
        assertEquals(Difficulty.EASY, sudokuModel.getDifficulty());

        // The user starts a different game
        sudokuModel.start(Difficulty.MEDIUM);
        SudokuBoard mediumBoard = sudokuModel.getBoard();
        assertEquals(Difficulty.MEDIUM, sudokuModel.getDifficulty());

        // the model generates different boards when starting on new
        assertNotEquals(easyBoard1, mediumBoard);
        assertNotEquals(easyBoard1, easyBoard2);

        assertEquals(new CellPosition(0, 0), sudokuModel.getSelectedCell());
    }

    @Test
    public void testGiveNumberToCell() {
        SudokuModel sudokuModel = new SudokuModel("Test Testesen");

        int[][] playableBoard = { { 0, 2, 3, 4 }, { 3, 4, 1, 2 }, { 2, 1, 4, 3 }, { 4, 3, 2, 1 } };
        int[][] solvedBoard = { { 1, 2, 3, 4 }, { 3, 4, 1, 2 }, { 2, 1, 4, 3 }, { 4, 3, 2, 1 } };

        CellPosition emptyCell = new CellPosition(0, 0);
        CellPosition givenCell = new CellPosition(0, 1);

        sudokuModel.setBoard(playableBoard, solvedBoard);

        // Test setting number to empty cell
        sudokuModel.setSelectedCell(emptyCell);
        sudokuModel.giveNumberToCell(5);
        assertEquals(Integer.valueOf(5), sudokuModel.getBoard().getNumber(0, 0));

        // Giving a number to a given cell, should not be possible
        sudokuModel.setSelectedCell(givenCell);
        sudokuModel.giveNumberToCell(5);
        assertNotEquals(Integer.valueOf(5), sudokuModel.getBoard().getNumber(0, 1));
    }

    @Test
    public void testSetSelectedSell() {
        SudokuModel sudokuModel = new SudokuModel("Test Testesen");

        sudokuModel.setSelectedCell(new CellPosition(0, 0));
        assertEquals(new CellPosition(0, 0), sudokuModel.getSelectedCell());

        sudokuModel.setSelectedCell(new CellPosition(1, 1));
        assertEquals(new CellPosition(1, 1), sudokuModel.getSelectedCell());
    }

    @Test
    public void testGameState() {
        SudokuModel sudokuModel = new SudokuModel("Test Testesen");

        assertEquals(GameState.WELCOME, sudokuModel.getGameState());

        sudokuModel.start(Difficulty.EASY);
        assertEquals(GameState.PLAYING, sudokuModel.getGameState());

        for (GridCell cell : sudokuModel.getAllTiles()) {
            if (cell.number() == 0) {
                for (int i = 1; i < 10; i++) {
                    sudokuModel.setSelectedCell(cell.pos());
                    sudokuModel.giveNumberToCell(i);
                    // if the board is solved, break the loop
                    if (sudokuModel.isSolved()) {
                        break;
                    }
                }
            }
        }
        assertEquals(GameState.FINISHED, sudokuModel.getGameState());

        sudokuModel.setGameState(GameState.WELCOME);
        assertEquals(GameState.WELCOME, sudokuModel.getGameState());

        sudokuModel.setGameState(GameState.PLAYING);
        assertEquals(GameState.PLAYING, sudokuModel.getGameState());

        sudokuModel.setGameState(GameState.FINISHED);
        assertEquals(GameState.FINISHED, sudokuModel.getGameState());
    }

    @Test
    public void testIsSolved() {
        SudokuModel sudokuModel = new SudokuModel("Test Testesen");
        sudokuModel.start(Difficulty.EASY);

        assertFalse(sudokuModel.isSolved());

        // brute forcing the solution by testing all possible numbers
        for (GridCell cell : sudokuModel.getAllTiles()) {
            if (cell.number() == 0) {
                for (int i = 1; i < 10; i++) {
                    sudokuModel.setSelectedCell(cell.pos());
                    sudokuModel.giveNumberToCell(i);
                    // if the board is solved, break the loop
                    if (sudokuModel.isSolved()) {
                        break;
                    }
                }
            }
        }
        assertTrue(sudokuModel.isSolved());
    }
}