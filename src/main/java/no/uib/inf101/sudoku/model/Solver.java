package no.uib.inf101.sudoku.model;

public class Solver {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    public boolean solve(int[][] board) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find the first empty cell
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // If there is no empty cell, the board is solved
        if (isEmpty) {
            return true;
        }

        // Try numbers from 1 to 9 in the empty cell
        for (int num = 1; num <= SIZE; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;

                // Recursively solve the remaining board
                if (solve(board)) {
                    return true;
                }

                // If not successful, backtrack
                board[row][col] = EMPTY;
            }
        }

        // No solution found
        return false;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        // Check if the number is already in the row or column
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check if the number is already in the 3x3 box
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
