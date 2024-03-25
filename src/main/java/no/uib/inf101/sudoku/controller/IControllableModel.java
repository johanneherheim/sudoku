package no.uib.inf101.sudoku.controller;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.IGridDimension;
import no.uib.inf101.sudoku.model.Board;
import no.uib.inf101.sudoku.model.GameState;

public interface IControllableModel {

    IGridDimension getDimension();

    Board getBoard();

    GameState getGameState();

    void setGameState(GameState gameState);

    Integer getCurrentNumber();

    void giveNumberToCell(Integer number);

    CellPosition getSelectedCell();

    void setSelectedCell(CellPosition cell);

    Integer getDelay();

    void checkIfSolved();

    void restart();
}
