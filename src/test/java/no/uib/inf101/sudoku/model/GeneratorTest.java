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
        for (int i = 0; i < 9; i++) {
            // all numbers only appear once
            List<Integer> possibleNumInRow = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
            for (int j = 0; j < 9; j++) {
                assertTrue(board[i][j] >= 0 && board[i][j] <= 9);
                assertTrue(possibleNumInRow.contains(board[i][j]));
                // Remove the number from the possibleNum-list
                for (int k = 0; k < possibleNumInRow.size(); k++) {
                    if (possibleNumInRow.get(k) == board[i][j]) {
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

        int[][] board = generator.getPlayableBoard();

        for (int i = 0; i < 9; i++) {
            // all numbers only appear once
            List<Integer> possibleNumInCol = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
            for (int j = 0; j < 9; j++) {
                assertTrue(board[j][i] >= 0 && board[j][i] <= 9);
                assertTrue(possibleNumInCol.contains(board[j][i]));
                // Remove the number from the possibleNum-list
                for (int k = 0; k < possibleNumInCol.size(); k++) {
                    if (possibleNumInCol.get(k) == board[j][i]) {
                        possibleNumInCol.set(k, 0);
                    }
                }
            }
        }
    }

    @Test
    public void testDifficultyEnumToId() {
        Generator generator = new Generator(Difficulty.EASY);
        assertEquals(Integer.valueOf(1), generator.difficultyEnumToId(Difficulty.EASY));
        assertEquals(Integer.valueOf(40), generator.difficultyEnumToId(Difficulty.MEDIUM));
        assertEquals(Integer.valueOf(55), generator.difficultyEnumToId(Difficulty.HARD));
    }
}
