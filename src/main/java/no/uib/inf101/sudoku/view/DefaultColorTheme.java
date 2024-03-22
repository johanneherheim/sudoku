package no.uib.inf101.sudoku.view;

import java.awt.Color;

public class DefaultColorTheme implements IColorTheme {

    @Override
    public Color getCellColor(boolean isCorrect, boolean isGiven, Integer number) {
        if (number == 0) {
            return Color.DARK_GRAY;
        }
        if (isGiven) {
            return Color.DARK_GRAY;
        }
        if (isCorrect) {
            return new Color(0, 255, 0) /* GREEN */;
        } else if (!isCorrect) {
            return new Color(255, 76, 97) /* RED */;
        }
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

}
