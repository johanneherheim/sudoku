package no.uib.inf101.sudoku.view.colorthemes;

import java.awt.Color;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

public interface ColorTheme {

    Color getCellColor(GridCell cell, CellPosition selectedCell);

    Color getBackgroundColor();

    Color getTextColor();

}