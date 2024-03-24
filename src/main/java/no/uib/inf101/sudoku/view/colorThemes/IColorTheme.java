package no.uib.inf101.sudoku.view.colorThemes;

import java.awt.Color;

import no.uib.inf101.grid.CellPosition;

public interface IColorTheme {

    Color getCellColor(boolean isCorrect, boolean isGiven, Integer number, CellPosition cellPosition,
            CellPosition selectedCell);

    Color getGridColor();

    Color getBackgroundColor();

    Color getTextColor();

    Color getButtonColor(Integer number, Integer currentNumber);
}
