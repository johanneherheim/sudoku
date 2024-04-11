package no.uib.inf101.sudoku.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generator {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;
    Difficulty difficulty;
    private static int[][] solvedBoard;
    private static int[][] playableBoard;

    public Generator(Difficulty difficulty) {
        this.difficulty = difficulty;
        solvedBoard = new int[SIZE][SIZE];
        playableBoard = new int[SIZE][SIZE];
    }

    public void generateBoard() {
        fillBoard(0, 0);
        makeBoardPlayable(getDifficulty(difficulty));
    }

    private boolean fillBoard(int row, int col) {
        if (row == SIZE) {
            return true;
        } else if (col == SIZE) {
            return fillBoard(row + 1, 0);
        }

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(numbers);

        for (int num : numbers) {
            if (isValid(row, col, num)) {
                solvedBoard[row][col] = num;
                if (fillBoard(row, col + 1)) {
                    return true;
                }
                solvedBoard[row][col] = EMPTY;
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (solvedBoard[row][i] == num || solvedBoard[i][col] == num
                    || solvedBoard[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }

    private void makeBoardPlayable(int difficulty) {
        for (int i = 0; i < SIZE; i++) {
            playableBoard[i] = Arrays.copyOf(solvedBoard[i], SIZE);
        }
        int count = 0;
        while (count < difficulty) {
            int row = (int) (Math.random() * SIZE);
            int col = (int) (Math.random() * SIZE);
            if (playableBoard[row][col] != EMPTY) {
                playableBoard[row][col] = EMPTY;
                count++;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(solvedBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getSolvedBoard() {
        return solvedBoard;
    }

    public int[][] getPlayableBoard() {
        return playableBoard;
    }

    public Integer getDifficulty(Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) {
            return 1;
        } else if (difficulty == Difficulty.MEDIUM) {
            return 2;
        } else {
            return 3;
        }
    }
}
