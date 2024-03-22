package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid implements IGrid {

    private final int rows;

    private final int cols;

    private ArrayList<GridCell> cells = new ArrayList<GridCell>();

    public Grid(int rows, int cols, int[][] values) {
        this.rows = rows;
        this.cols = cols;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells.add(new GridCell(new CellPosition(row, col), values[row][col], true, true));
            }
        }
    }

    @Override
    public int getCols() {
        return rows;
    }

    @Override
    public int getRows() {
        return cols;
    }

    @Override
    public Iterator<GridCell> iterator() {
        ArrayList<GridCell> copy = new ArrayList<GridCell>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                CellPosition position = new CellPosition(row, col);
                copy.add(new GridCell(position, getNumber(row, col), isCorrect(row, col), isGiven(row, col)));
            }
        }
        return copy.iterator();
    }

    @Override
    public void setNumber(CellPosition cellPosition, int number) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (cellPosition.equals(new CellPosition(row, col))) {
                    cells.set(row * cols + col, new GridCell(cellPosition, number, false, false));
                }
            }
        }
    }

    @Override
    public Integer getNumber(int row, int col) {
        return cells.get(row * cols + col).number();
    }

    @Override
    public boolean isCorrect(int row, int col) {
        return cells.get(row * cols + col).isCorrect();
    }

    @Override
    public boolean isGiven(int row, int col) {
        return cells.get(row * cols + col).isGiven();
    }

}
