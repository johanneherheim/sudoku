package no.uib.inf101.sudoku.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Generates a Sudoku board with a given difficulty.
 */
public class Generator {

    // Number of cells removed
    private static final int EASY_CELLS_REMOVED = 1;
    private static final int MEDIUM_CELLS_REMOVED = 40;
    private static final int HARD_CELLS_REMOVED = 55;

    /** num cells per row and col */
    private static final int SIZE = 9;

    /** an empty cell has the number 0 */
    private static final int EMPTY = 0;

    /** used to check if a board is solvable */
    private static final Solver solver = new Solver();

    /** difficulty to the current game */
    private final Difficulty difficulty;

    /** the solution */
    private final int[][] solvedBoard;

    /** the board that the user sees on the screen */
    private final int[][] playableBoard;

    /**
     * Constructor for the Generator class.
     * 
     * @param difficulty the difficulty of the game
     */
    public Generator(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.solvedBoard = new int[SIZE][SIZE];
        this.playableBoard = new int[SIZE][SIZE];
    }

    /**
     * Generates a new Sudoku board by filling the board with numbers and then
     * removing cells to make the board playable.
     */
    public void generateBoard() {
        fillBoard(0, 0);
        makeBoardPlayable(difficultyToCellsRemoved(difficulty));
    }

    /**
     * Fills the board with numbers.
     * 
     * @param row the row
     * @param col the column
     * @return true if the board is filled, false otherwise
     */
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

    /**
     * Checks if a number is valid in a given row, column and 3x3 square.
     * 
     * @param row the row
     * @param col the column
     * @param num the number
     * @return true if the number is valid, false otherwise
     */
    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (solvedBoard[row][i] == num || solvedBoard[i][col] == num
                    || solvedBoard[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * Makes the board playable by removing cells. If the board is unsolvable, a new
     * board is generated.
     * 
     * @param difficulty the difficulty of the game
     */
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
        int[][] tempBoard = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            tempBoard[i] = Arrays.copyOf(playableBoard[i], SIZE);
        }

        // Generating a new board if the board is unsolvable
        if (!solver.isSolvable(tempBoard, solvedBoard)) {
            System.out.println("Unsolvable board, generating new board ...");
            makeBoardPlayable(difficulty);
        } else {
            System.out.println("Board generated successfully!");
        }
    }

    /**
     * Returns the solved board.
     * 
     * @return the solved board
     */
    public int[][] getSolvedBoard() {
        return solvedBoard;
    }

    /**
     * Returns the playable board.
     * 
     * @return the playable board
     */
    public int[][] getPlayableBoard() {
        return playableBoard;
    }

    /**
     * Returns the number of cells removed based on the difficulty.
     * 
     * @param difficulty
     * @return the number of cells removed
     */
    public Integer difficultyToCellsRemoved(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return EASY_CELLS_REMOVED;
            case MEDIUM:
                return MEDIUM_CELLS_REMOVED;
            case HARD:
                return HARD_CELLS_REMOVED;
            default:
                throw new IllegalArgumentException("Unsupported difficulty: " + difficulty);
        }
    }
}
