package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;

public class Board extends Grid {

    public Board(int[][] generatedBoard) {
        super(generatedBoard.length, generatedBoard[0].length, generatedBoard);
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

    void clearBoard() {
        for (GridCell cell : this) {
            cell = new GridCell(cell.pos(), 0, false, false);
        }
    }
}
