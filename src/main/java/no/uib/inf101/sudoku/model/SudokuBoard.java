package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

public class SudokuBoard extends Grid {

    public SudokuBoard(int[][] playableBoard, int[][] solution) {
        super(playableBoard.length, playableBoard[0].length, playableBoard, solution);
    }

    /**
     * Method for displaying the values of the tetris board as a string.
     * 
     * @return a string representation of the board
     */
    String prettyString() {
        String board = "";
        Integer count = 1;
        for (GridCell cell : this) {
            board += cell.number();
            if (count % this.getCols() == 0) {
                board += "\n";
            }
            count += 1;
        }
        return board.substring(0, board.length() - 1);
    }
}