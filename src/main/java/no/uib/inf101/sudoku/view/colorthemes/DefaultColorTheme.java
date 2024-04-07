package no.uib.inf101.sudoku.view.colorthemes;

import java.awt.Color;

import no.uib.inf101.grid.CellPosition;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(boolean isCorrect, boolean isGiven, Integer number, CellPosition cellPosition,
            CellPosition selectedCell) {
        if (!isCorrect) {
            return new Color(204, 76, 97);
        }
        if (cellPosition.row() == selectedCell.row() && cellPosition.col() == selectedCell.col()) {
            return new Color(31, 31, 31);
        } else if (cellPosition.col() < 3 && cellPosition.row() < 3 || cellPosition.col() > 5 && cellPosition.row() < 3
                || cellPosition.col() < 3 && cellPosition.row() > 5
                || cellPosition.col() > 5 && cellPosition.row() > 5
                || cellPosition.col() > 2 && cellPosition.col() < 6 && cellPosition.row() > 2
                        && cellPosition.row() < 6) {
            return new Color(51, 51, 51);
        } else {
            return new Color(41, 41, 41);
        }
    }

    @Override
    public Color getGridColor() {
        return Color.BLACK;
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(26, 26, 26);
    }

    @Override
    public Color getTextColor() {
        return Color.LIGHT_GRAY;
    }

    @Override
    public Color getButtonColor(Integer number, Integer currentNumber) {
        return (number + 1 == currentNumber ? new Color(31, 31, 31) : new Color(51, 51, 51));
    }

}
