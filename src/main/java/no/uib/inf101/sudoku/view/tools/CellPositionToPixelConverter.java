package no.uib.inf101.sudoku.view.tools;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

/** Class with methods for getting the pixels to a cell in a grid. */
public class CellPositionToPixelConverter {

    /** The rectangle that the grid is drawn in. */
    private Rectangle2D box;

    /** The grid dimension of the grid (number of rows and cols). */
    private GridDimension gd;

    /** The width of the grid lines. */
    private double MARGIN;

    /**
     * Constructor for the CellPositionToPixelConverter
     * 
     * @param box    the rectangle that the grid is drawn in
     * @param gd     the grid dimension of the grid (number of rows and cols)
     * @param margin the width of the grid lines
     */
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box;
        this.gd = gd;
        this.MARGIN = margin;
    }

    /**
     * Method for getting the bounds for a cell
     * 
     * @param cp the position of the cell
     * @return the cell as a Rectangle2D object
     */

    public Rectangle2D getBoundsForCell(CellPosition cp) {
        double y0 = this.box.getY();
        double x0 = this.box.getX();
        double boxWidth = this.box.getWidth();
        double boxHeight = this.box.getHeight();
        double row = cp.row();
        double col = cp.col();
        double numberOfRows = this.gd.getRows();
        double numberOfCols = this.gd.getCols();

        double cellHeight = (boxHeight - (numberOfRows + 1) * MARGIN) / numberOfRows;
        double cellWidth = (boxWidth - (numberOfCols + 1) * MARGIN) / numberOfCols;

        double x = x0 + MARGIN * (col + 1) + cellWidth * col;
        double y = y0 + MARGIN * (row + 1) + cellHeight * row;
        return new Rectangle2D.Double(x, y, cellWidth, cellHeight);
    }

    /**
     * Method for getting the row from a y coordinate
     * 
     * @param y the y coordinate
     * @return the row
     */
    public int getRowFromYCoordinate(int y) {
        double y0 = this.box.getY();
        double boxHeight = this.box.getHeight();
        double numberOfRows = this.gd.getRows();
        double cellHeight = (boxHeight - (numberOfRows + 1) * MARGIN) / numberOfRows;
        return (int) ((y - y0) / (cellHeight + MARGIN));
    }

    /**
     * Method for getting the column from an x coordinate
     * 
     * @param x the x coordinate
     * @return the column
     */
    public int getColumnFromXCoordinate(int x) {
        double x0 = this.box.getX();
        double boxWidth = this.box.getWidth();
        double numberOfCols = this.gd.getCols();
        double cellWidth = (boxWidth - (numberOfCols + 1) * MARGIN) / numberOfCols;
        return (int) ((x - x0) / (cellWidth + MARGIN));
    }
}
