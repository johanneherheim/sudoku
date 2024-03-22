package no.uib.inf101.sudoku.view;

import java.awt.Color;

public interface IColorTheme {

    Color getCellColor(boolean isCorrect, boolean isGiven, Integer number);

    Color getGridColor();

    Color getBackgroundColor();

    Color getTextColor();

    Color getButtonColor(boolean isClicked);
}
