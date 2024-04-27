package no.uib.inf101.sudoku.view.colorthemes;

import java.awt.Color;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

/**
 * The DefaultColorTheme class represents the default color theme for the Sudoku
 * game. The default color theme is a dark theme with red cells for incorrect
 */
public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(GridCell cell, CellPosition selectedCell) {
        if (!cell.isCorrect()) {
            return new Color(204, 76, 97);
        }
        if (cell.pos().row() == selectedCell.row() && cell.pos().col() == selectedCell.col()) {
            return new Color(31, 31, 31);
        } else if (cell.pos().col() < 3 && cell.pos().row() < 3 || cell.pos().col() > 5 && cell.pos().row() < 3
                || cell.pos().col() < 3 && cell.pos().row() > 5
                || cell.pos().col() > 5 && cell.pos().row() > 5
                || cell.pos().col() > 2 && cell.pos().col() < 6 && cell.pos().row() > 2
                        && cell.pos().row() < 6) {
            return new Color(45, 45, 45);
        } else {
            return new Color(35, 35, 35);
        }
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(26, 26, 26);
    }

    @Override
    public Color getTextColor() {
        return Color.LIGHT_GRAY;
    }

}
