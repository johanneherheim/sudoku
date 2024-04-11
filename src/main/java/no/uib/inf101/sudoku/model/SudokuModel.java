package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.controller.ControllableSudokuModel;
import no.uib.inf101.sudoku.dao.ScoreUtils;
import no.uib.inf101.sudoku.view.ViewableSudokuModel;

public class SudokuModel implements ViewableSudokuModel, ControllableSudokuModel {
    private static SudokuBoard board;
    private static Generator generator;
    private static Integer currentNumber = 1;
    private static CellPosition selectedCell;
    private GameState gameState;
    private ScoreUtils scoreQueries = new ScoreUtils();
    private String username;
    Difficulty difficulty;

    public SudokuModel(String username) {
        this.difficulty = Difficulty.EASY;
        this.username = username;
        generator = new Generator(difficulty);
        generator.generateBoard();
        board = new SudokuBoard(generator.getPlayableBoard(), generator.getSolvedBoard());
        selectedCell = new CellPosition(0, 0);
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Iterable<GridCell> getAllTiles() {
        return board;
    }

    @Override
    public SudokuBoard getBoard() {
        return board;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState gameState) {
        if (gameState == GameState.PLAYING) {
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
            // do something
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
    public boolean isSolved() {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                if (generator.getSolvedBoard()[row][col] != board.getNumber(row, col)) {
                    return false;
                }
            }
        }

        scoreQueries.insertScore(username, 0, 0, 0, 0, generator.getPlayableBoard(),
                generator.getSolvedBoard(), generator.getDifficulty(difficulty));

        gameState = GameState.FINISHED;
        return true;
    }

    @Override
    public void restart() {

        gameState = GameState.PLAYING;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        generator = new Generator(difficulty);
        generator.generateBoard();
        board = new SudokuBoard(generator.getPlayableBoard(), generator.getSolvedBoard());
    }
}
