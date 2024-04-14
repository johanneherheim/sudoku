package no.uib.inf101.sudoku.view;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.model.SudokuBoard;

public interface ViewableSudokuModel {

    GridDimension getDimension();

    Iterable<GridCell> getAllTiles();

    SudokuBoard getBoard();

    GameState getGameState();

    void setGameState(GameState gameState);

    void giveNumberToCell(Integer number);

    CellPosition getSelectedCell();

    void setSelectedCell(CellPosition cell);

    boolean isSolved();
}
