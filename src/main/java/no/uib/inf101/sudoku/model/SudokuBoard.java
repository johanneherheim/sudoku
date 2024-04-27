package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

/**
 * Represents a Sudoku board, which is a type of grid.
 */
public class SudokuBoard extends Grid {

    /**
     * Constructs a SudokuBoard object with the specified playable board and
     * solution.
     * 
     * @param playableBoard the playable board of the Sudoku game
     * @param solution      the solution board of the Sudoku game
     */
    public SudokuBoard(int[][] playableBoard, int[][] solution) {
        super(playableBoard.length, playableBoard[0].length, playableBoard, solution);
    }

    /**
     * Returns a string representation of the Sudoku board.
     * 
     * @return a string representation of the board
     */
    public String prettyString() {
        StringBuilder board = new StringBuilder();
        Integer count = 1;
        for (GridCell cell : this) {
            board.append(cell.number());
            if (count % this.getCols() == 0) {
                board.append("\n");
            }
            count++;
        }
        return board.toString();
    }
}