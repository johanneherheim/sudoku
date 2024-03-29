package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.IGridDimension;
import no.uib.inf101.sudoku.controller.IControllableModel;
import no.uib.inf101.sudoku.view.ViewableModel;

public class Model implements ViewableModel, IControllableModel {
    private static Board board;
    private static Generator generator;
    private static Integer currentNumber = 1;
    private static CellPosition selectedCell;
    private Integer count;
    private GameState gameState;
    private long startTimeMillis;

    public Model() {
        generator = new Generator();
        generator.generateBoard();
        board = new Board(generator.getPlayableBoard(), generator.getSolvedBoard());
        gameState = GameState.WELCOME;
        startTimeMillis = System.currentTimeMillis();
        selectedCell = new CellPosition(0, 0);
        count = 0;
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
    public Board getBoard() {
        return board;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState gameState) {
        if (gameState == GameState.PLAYING) {
            startTimeMillis = System.currentTimeMillis();
        }
        this.gameState = gameState;
    }

    @Override
    public Integer getCurrentNumber() {
        return currentNumber;
    }

    @Override
    public void giveNumberToCell(Integer number) {
        currentNumber = number;
        board.setNumber(selectedCell, number);
        if (!board.isGiven(selectedCell.row(), selectedCell.col())) {
            count++;
        }
    }

    @Override
    public CellPosition getSelectedCell() {
        return selectedCell;
    }

    @Override
    public void setSelectedCell(CellPosition cell) {
        selectedCell = cell;
    }

    @Override
    public void checkIfSolved() {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                if (generator.getSolvedBoard()[row][col] != board.getNumber(row, col)) {
                    return;
                }
            }
        }
        gameState = GameState.WIN;
    }

    @Override
    public void restart() {
        generator.generateBoard();
        board = new Board(generator.getPlayableBoard(), generator.getSolvedBoard());
        gameState = GameState.PLAYING;
        count = 0;
    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public Integer getDelay() {
        return 1000;
    }

    @Override
    public long getTimeElapsed() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis - startTimeMillis;
    }

}
