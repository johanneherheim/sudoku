package no.uib.inf101.sudoku.controller;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.model.SudokuBoard;

public interface ControllableSudokuModel {

    GridDimension getDimension();

    SudokuBoard getBoard();

    GameState getGameState();

    void setGameState(GameState gameState);

    Integer getCurrentNumber();

    void giveNumberToCell(Integer number);

    CellPosition getSelectedCell();

    void setSelectedCell(CellPosition cell);

    boolean isSolved();

    void restart();
}
