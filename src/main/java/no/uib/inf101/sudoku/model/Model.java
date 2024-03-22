package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.IGridDimension;
import no.uib.inf101.sudoku.controller.IControllableModel;
import no.uib.inf101.sudoku.view.ViewableModel;

public class Model implements ViewableModel, IControllableModel {
    private Board board;
    private GameState gameState;
    Integer currentNumber = 0;

    public Model(Board board) {
        this.board = board;
        gameState = GameState.WELCOME;

    }

    @Override
    public IGridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell> getAllTiles() {
        return board;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void setCurrentNumber(Integer number) {
        currentNumber = number;
    }

    @Override
    public Integer getCurrentNumber() {
        return currentNumber;
    }

}
