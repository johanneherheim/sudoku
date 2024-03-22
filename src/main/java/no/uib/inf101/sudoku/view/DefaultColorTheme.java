package no.uib.inf101.sudoku.view;

import java.awt.Color;

public class DefaultColorTheme implements IColorTheme {

    @Override
    public Color getCellColor(boolean isCorrect, boolean isGiven, Integer number) {
        return Color.DARK_GRAY;
    }

    @Override
    public Color getGridColor() {
        return Color.BLACK;
    }

    @Override
    public Color getBackgroundColor() {
        return new Color(31, 31, 31);
    }

    @Override
    public Color getTextColor() {
        return Color.LIGHT_GRAY;
    }

    @Override
    public Color getButtonColor(boolean isClicked) {
        return (isClicked ? new Color(31, 31, 31) : new Color(51, 51, 51));
    }

}
