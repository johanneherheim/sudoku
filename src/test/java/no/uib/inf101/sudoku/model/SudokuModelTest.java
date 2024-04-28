package no.uib.inf101.sudoku.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

public class SudokuModelTest {

    @Test
    public void testStart() {

        SudokuModel sudokuModel = new SudokuModel("Test Testesen");

        // First gamestate should be WELCOME
        assertEquals(GameState.WELCOME, sudokuModel.getGameState());

        // The user starts the game by choosing a difficulty
        sudokuModel.startGame(Difficulty.EASY);
        SudokuBoard easyBoard1 = sudokuModel.getBoard();
        assertEquals(Difficulty.EASY, sudokuModel.getDifficulty());

        // The user starts a new game with the same difficulty
        sudokuModel.startGame(Difficulty.EASY);
        SudokuBoard easyBoard2 = sudokuModel.getBoard();
        assertEquals(Difficulty.EASY, sudokuModel.getDifficulty());

        // The user starts a new game with a different difficulty
        sudokuModel.startGame(Difficulty.MEDIUM);
        SudokuBoard mediumBoard = sudokuModel.getBoard();
        assertEquals(Difficulty.MEDIUM, sudokuModel.getDifficulty());

        // the model generates different boards every time
        assertNotEquals(easyBoard1, mediumBoard);
        assertNotEquals(easyBoard1, easyBoard2);

        // The selected cell should start at (0, 0)
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
    public void testSetSelectedCell() {
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

        sudokuModel.startGame(Difficulty.EASY);
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
        sudokuModel.startGame(Difficulty.EASY);

        int[][] unsolvedBoard = {
                { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };

        int[][] solvedBoard = {
                { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
                { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
                { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
                { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
                { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
                { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
                { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
                { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
                { 3, 4, 5, 2, 8, 6, 1, 7, 9 }
        };

        assertFalse(sudokuModel.isSolved());

        sudokuModel.setBoard(unsolvedBoard, solvedBoard);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuModel.setSelectedCell(new CellPosition(i, j));
                sudokuModel.giveNumberToCell(solvedBoard[i][j]);
            }

        }

        assertTrue(sudokuModel.isSolved());
    }
}