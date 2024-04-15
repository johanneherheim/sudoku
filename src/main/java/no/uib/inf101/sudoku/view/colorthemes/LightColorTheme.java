package no.uib.inf101.sudoku.view.colorthemes;

import java.awt.Color;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

public class LightColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(GridCell cell, CellPosition selectedCell) {
        if (!cell.isCorrect()) {
            return new Color(255, 180, 180); // Light red
        }
        if (cell.pos().row() == selectedCell.row() && cell.pos().col() == selectedCell.col()) {
            return new Color(200, 200, 200); // Light blue
        } else if (cell.pos().col() < 3 && cell.pos().row() < 3 || cell.pos().col() > 5 && cell.pos().row() < 3
                || cell.pos().col() < 3 && cell.pos().row() > 5
                || cell.pos().col() > 5 && cell.pos().row() > 5
                || cell.pos().col() > 2 && cell.pos().col() < 6 && cell.pos().row() > 2
                        && cell.pos().row() < 6) {
            return new Color(240, 240, 240); // Light gray
        } else {
            return new Color(220, 220, 220); // Lighter gray
        }
    }

    @Override
    public Color getBackgroundColor() {
        return Color.WHITE; // White background
    }

    @Override
    public Color getTextColor() {
        return Color.DARK_GRAY; // Dark gray text
    }

}
