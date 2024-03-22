package no.uib.inf101.sudoku.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generator {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;
    private int[][] board;

    public Generator() {
        board = new int[SIZE][SIZE];
    }

    public void generateBoard() {
        if (fillBoard(0, 0)) {
            shuffleBoard();
        } else {
            System.out.println("No solution exists for the current board configuration.");
        }
    }

    private boolean fillBoard(int row, int col) {
        if (row == SIZE) {
            return true;
        }

        if (col == SIZE) {
            return fillBoard(row + 1, 0);
        }

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(numbers);

        for (int num : numbers) {
            if (isValid(row, col, num)) {
                board[row][col] = num;
                if (fillBoard(row, col + 1)) {
                    return true;
                }
                board[row][col] = EMPTY;
            }
        }

        return false;
    }

    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num
                    || board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }

    private void shuffleBoard() {
        // Implement shuffling logic to randomize the board
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean isSolvable(int[][] board) {
        Solver solver = new Solver();
        return solver.solve(board);
    }
}
