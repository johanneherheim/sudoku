package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Modified Grid from semesteroppgave 1 (Tetris), INF101 spring 2024:
 * https://git.app.uib.no/ii/inf101/24v/assignments/Johanne.Herheim_tetris
 */
public class Grid implements IGrid {

    private final int rows;
    private final int cols;

    private ArrayList<GridCell> cells = new ArrayList<GridCell>();

    private int[][] solution;
    private int[][] startValues;

    public Grid(int rows, int cols, int[][] startValues, int[][] solution) {
        this.rows = rows;
        this.cols = cols;
        this.startValues = startValues;
        this.solution = solution;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (startValues[row][col] == 0) {
                    cells.add(new GridCell(new CellPosition(row, col), startValues[row][col], false, false));
                } else {
                    cells.add(new GridCell(new CellPosition(row, col), startValues[row][col], true, true));

                }
            }
        }
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
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
                if (!isGiven(row, col))
                    if (cellPosition.equals(new CellPosition(row, col))) {
                        cells.set(row * cols + col, new GridCell(cellPosition, number, true, false));
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
        return cells.get(row * cols + col).number() == solution[row][col] || cells.get(row * cols + col).number() == 0;
    }

    @Override
    public boolean isGiven(int row, int col) {
        return cells.get(row * cols + col).isGiven();
    }

    public int[][] getSolution() {
        return this.solution;
    }

    public int[][] getStartValues() {
        return this.startValues;
    }

}
