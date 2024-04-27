package no.uib.inf101.sudoku.view.colorthemes;

import java.awt.Color;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

/**
 * The ColorTheme interface represents a color theme for the Sudoku game.
 */
public interface ColorTheme {

    /**
     * Returns the color of the cell at the specified position.
     * 
     * @return the color of the cell
     */
    Color getCellColor(GridCell cell, CellPosition selectedCell);

    /**
     * Returns the color of the background.
     * 
     * @return the color of the background
     */
    Color getBackgroundColor();

    /**
     * Returns the color of the text.
     * 
     * @return the color of the text
     */
    Color getTextColor();

}