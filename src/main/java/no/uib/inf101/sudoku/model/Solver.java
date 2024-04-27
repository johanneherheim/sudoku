package no.uib.inf101.sudoku.model;

import java.util.stream.IntStream;

/**
 * Almost all of this is taken from here:
 * https://github.com/eugenp/tutorials/blob/master/algorithms-modules/algorithms-miscellaneous-2/src/main/java/com/baeldung/algorithms/sudoku/BacktrackingAlgorithm.java
 * 25.apr. 2024
 *
 * The Solver class is used to solve a Sudoku puzzle.
 * 
 * @author Baeldung
 */
public class Solver {

    /** Number of cells in each row and col */
    private static final int BOARD_SIZE = 9;

    /** An empty cell has the value 0 */
    private static final int EMPTY = 0;

    /**
     * Solves the Sudoku board using backtracking algorithm.
     * 
     * @param board the Sudoku board represented as a 2D array
     * @return true if the board is solvable, false otherwise
     */
    public boolean solve(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board[row][column] == EMPTY) {
                    for (int k = 1; k <= 9; k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && solve(board)) {
                            return true;
                        }
                        board[row][column] = EMPTY;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a given Sudoku board is solvable.
     *
     * @param board    the Sudoku board to be checked
     * @param solution the solution to the Sudoku board
     * @return true if the board is solvable and matches the solution, false
     *         otherwise
     */
    public boolean isSolvable(int[][] board, int[][] solution) {
        int[][] boardCopy = new int[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                boardCopy[row][column] = board[row][column];
            }
        }
        solve(boardCopy);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (boardCopy[row][column] != solution[row][column]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a given value at a specific position in the Sudoku board is valid.
     *
     * @param board  the Sudoku board represented as a 2D array
     * @param row    the row index of the position
     * @param column the column index of the position
     * @return true if the value is valid, false otherwise
     */
    private boolean isValid(int[][] board, int row, int column) {
        return (rowConstraint(board, row)
                && columnConstraint(board, column)
                && subsectionConstraint(board, row, column));
    }

    /**
     * Checks if the given row in the Sudoku board satisfies the row constraint.
     *
     * @param board The Sudoku board represented as a 2D array.
     * @param row   The row index to check.
     * @return {@code true} if the row satisfies the constraint, {@code false}
     *         otherwise.
     */
    private boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(0, BOARD_SIZE)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    /**
     * Checks if a column in the Sudoku board satisfies the constraint.
     *
     * @param board  the Sudoku board represented as a 2D array
     * @param column the column index to check
     * @return true if the column satisfies the constraint, false otherwise
     */
    private boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(0, BOARD_SIZE)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    /**
     * Checks if the given subsection of the Sudoku board satisfies the constraint.
     * A subsection is a 3x3 grid within the Sudoku board.
     *
     * @param board  the Sudoku board represented as a 2D array
     * @param row    the row index of the subsection
     * @param column the column index of the subsection
     * @return true if the subsection satisfies the constraint, false otherwise
     */
    private boolean subsectionConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        int subsectionRowStart = (row / 3) * 3;
        int subsectionRowEnd = subsectionRowStart + 3;

        int subsectionColumnStart = (column / 3) * 3;
        int subsectionColumnEnd = subsectionColumnStart + 3;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c))
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks the constraint for a given cell in the Sudoku board.
     * 
     * @param board      The Sudoku board represented as a 2D array.
     * @param row        The row index of the cell.
     * @param constraint An array representing the constraint for each number (1-9).
     * @param column     The column index of the cell.
     * @return True if the constraint is satisfied for the cell, false otherwise.
     */
    private boolean checkConstraint(
            int[][] board,
            int row,
            boolean[] constraint,
            int column) {
        if (board[row][column] != EMPTY) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}
