package no.uib.inf101.sudoku.view;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.IGridDimension;
import no.uib.inf101.sudoku.model.Board;
import no.uib.inf101.sudoku.model.GameState;

public interface ViewableModel {

    IGridDimension getDimension();

    Iterable<GridCell> getAllTiles();

    Board getBoard();

    GameState getGameState();

    void setGameState(GameState gameState);

    Integer getCurrentNumber();

    void giveNumberToCell(Integer number);

    CellPosition getSelectedCell();

    void setSelectedCell(CellPosition cell);

    Integer getCount();

    Integer getDelay();

    void checkIfSolved();

    void restart();

    long getTimeElapsed();

}
