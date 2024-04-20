package no.uib.inf101.sudoku.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SudokuBoardTest {
    @Test
    public void prettyStringTest() {

        int[][] board = {
                { 1, 2, 3, 4 },
                { 3, 4, 1, 2 },
                { 2, 1, 4, 3 },
                { 4, 3, 2, 1 } };

        SudokuBoard sudokuBoard = new SudokuBoard(board, board);

        String expected = String.join("\n", new String[] {
                "1234",
                "3412",
                "2143",
                "4321\n"
        });
        assertEquals(expected, sudokuBoard.prettyString());
    }
}
