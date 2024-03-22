package no.uib.inf101.sudoku.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.IGridDimension;
import no.uib.inf101.sudoku.model.Board;
import no.uib.inf101.sudoku.model.GameState;

public interface ViewableModel {

    IGridDimension getDimension();

    Iterable<GridCell> getAllTiles();

    GameState getGameState();

    Board getBoard();

    void setCurrentNumber(Integer number);

    Integer getCurrentNumber();

}
