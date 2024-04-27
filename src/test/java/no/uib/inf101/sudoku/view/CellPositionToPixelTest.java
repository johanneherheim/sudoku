package no.uib.inf101.sudoku.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.geom.Rectangle2D;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.model.SudokuBoard;
import no.uib.inf101.sudoku.view.tools.CellPositionToPixelConverter;

public class CellPositionToPixelTest {

    int[][] unsolvedBoard = {
            { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
            { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
            { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
            { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
            { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
            { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
            { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
            { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
            { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
    };

    int[][] solvedBoard = {
            { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
            { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
            { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
            { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
            { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
            { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
            { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
            { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
            { 3, 4, 5, 2, 8, 6, 1, 7, 9 }
    };

    @Test
    public void sanityTest() {
        GridDimension gd = new SudokuBoard(unsolvedBoard, solvedBoard);
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                new Rectangle2D.Double(0, 0, 90, 90), gd, 0);
        Rectangle2D expected = new Rectangle2D.Double(0, 0, 10, 10);
        assertEquals(expected, converter.getBoundsForCell(new CellPosition(0, 0)));
    }

    @Test
    public void testGetBoundsForCell() {
        Rectangle2D box = new Rectangle2D.Double(0, 0, 90, 90);
        GridDimension gd = new SudokuBoard(unsolvedBoard, solvedBoard);
        double margin = 0;
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(box, gd, margin);

        CellPosition cp = new CellPosition(0, 0);
        Rectangle2D bounds = converter.getBoundsForCell(cp);
        assertEquals(bounds.getX(), 0);
        assertEquals(bounds.getY(), 0);

        cp = new CellPosition(1, 1);
        bounds = converter.getBoundsForCell(cp);
        assertEquals(bounds.getX(), 10);
        assertEquals(bounds.getY(), 10);
        assertEquals(bounds.getWidth(), 10);
        assertEquals(bounds.getHeight(), 10);
    }

    @Test
    public void testGetRowFromYCoordinate() {
        Rectangle2D box = new Rectangle2D.Double(0, 0, 100, 100);
        GridDimension gd = new SudokuBoard(unsolvedBoard, solvedBoard);
        double margin = 1;
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(box, gd, margin);

        int row = converter.getRowFromYCoordinate(11);
        assertEquals(row, 0);

        row = converter.getRowFromYCoordinate(34);
        assertEquals(row, 2);
    }

    @Test
    public void testGetColumnFromXCoordinate() {
        Rectangle2D box = new Rectangle2D.Double(0, 0, 100, 100);
        GridDimension gd = new SudokuBoard(unsolvedBoard, solvedBoard);
        double margin = 1;
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(box, gd, margin);

        int column = converter.getColumnFromXCoordinate(11);
        assertEquals(column, 1);

        column = converter.getColumnFromXCoordinate(34);
        assertEquals(column, 3);
    }
}
