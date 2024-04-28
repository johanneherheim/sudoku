package no.uib.inf101.sudoku.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GeneratorTest {

    @Test
    public void testGenerateBoardSize() {

        // since the board is randomly generated, we can't check for a specific board

        Generator generator = new Generator(Difficulty.EASY);
        generator.generateBoard();

        int[][] board = generator.getPlayableBoard();

        assertEquals(9, board.length);

        for (int i = 0; i < 9; i++) {
            assertEquals(9, board[i].length);
        }

    }

    @Test
    public void testRowsIsValid() {
        Generator generator = new Generator(Difficulty.EASY);
        generator.generateBoard();

        int[][] board = generator.getPlayableBoard();

        // for all rows
        for (int row = 0; row < 9; row++) {
            // all numbers only appear once
            List<Integer> possibleNumInRow = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
            for (int col = 0; col < 9; col++) {
                assertTrue(board[row][col] >= 0 && board[row][col] <= 9);
                assertTrue(possibleNumInRow.contains(board[row][col]) || board[row][col] == 0);
                // Remove the number from the possibleNum-list
                for (int k = 0; k < possibleNumInRow.size(); k++) {
                    if (possibleNumInRow.get(k) == board[row][col]) {
                        possibleNumInRow.set(k, 0);
                    }
                }
            }
        }
    }

    @Test
    public void testColIsValid() {
        Generator generator = new Generator(Difficulty.EASY);
        generator.generateBoard();

        int[][] board = generator.getSolvedBoard();

        // for all cols
        for (int col = 0; col < 9; col++) {
            // all numbers only appear once
            List<Integer> possibleNumInCol = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
            for (int row = 0; row < 9; row++) {
                assertTrue(board[row][col] >= 0 && board[row][col] <= 9);
                assertTrue(possibleNumInCol.contains(board[row][col]) || board[row][col] == 0);
                // Remove the number from the possibleNum-list
                for (int k = 0; k < possibleNumInCol.size(); k++) {
                    if (possibleNumInCol.get(k) == board[row][col]) {
                        possibleNumInCol.set(k, 0);
                    }
                }
            }
        }
    }

    @Test
    public void testDifficultyEnumToId() {
        Generator generator = new Generator(Difficulty.EASY);
        assertEquals(Integer.valueOf(30), generator.difficultyToCellsRemoved(Difficulty.EASY));
        assertEquals(Integer.valueOf(40), generator.difficultyToCellsRemoved(Difficulty.MEDIUM));
        assertEquals(Integer.valueOf(55), generator.difficultyToCellsRemoved(Difficulty.HARD));
    }
}
