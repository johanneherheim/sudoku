package no.uib.inf101.sudoku.view.colorthemes;

import java.awt.Color;

import no.uib.inf101.grid.CellPosition;

public interface ColorTheme {

    Color getCellColor(boolean isCorrect, boolean isGiven, Integer number, CellPosition cellPosition,
            CellPosition selectedCell);

    Color getGridColor();

    Color getBackgroundColor();

    Color getTextColor();

    Color getButtonColor(Integer number, Integer currentNumber);
}