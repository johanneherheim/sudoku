package no.uib.inf101.sudoku.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.geom.Rectangle2D;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.model.SudokuBoard;
import no.uib.inf101.sudoku.view.tools.CellPositionToPixelConverter;

public class CellPositionToPixelTest {

    // empty boards for testing
    int[][] board = { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
    int[][] solution = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

    @Test
    public void sanityTest() {
        GridDimension gd = new SudokuBoard(board, solution);
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                new Rectangle2D.Double(29, 29, 340, 240), gd, 30);
        Rectangle2D expected = new Rectangle2D.Double(214, 129, 47.5, 40);
        assertEquals(expected, converter.getBoundsForCell(new CellPosition(1, 2)));
    }
}
