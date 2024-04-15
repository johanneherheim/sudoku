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
    private static CellPosition selectedCell;
    private GameState gameState;
    private ScoreUtils scoreQueries = new ScoreUtils();
    private String username;
    Difficulty difficulty;
    int time;

    public SudokuModel(String username) {
        gameState = GameState.WELCOME;
        this.difficulty = Difficulty.EASY;
        this.username = username;
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

    public void setBoard(int[][] playableBoard, int[][] solvedBoard) {
        board = new SudokuBoard(playableBoard, solvedBoard);
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void giveNumberToCell(Integer number) {
        board.setNumber(selectedCell, number);
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
                if (board.getSolution()[row][col] != board.getNumber(row, col)) {
                    return false;
                }
            }
        }
        scoreQueries.insertScore(username, 0, time, 0, 0, generator.getPlayableBoard(),
                generator.getSolvedBoard(), generator.getDifficulty(difficulty));
        gameState = GameState.FINISHED;
        return true;
    }

    public void start(Difficulty difficulty) {
        this.difficulty = difficulty;
        gameState = GameState.PLAYING;
        generator = new Generator(difficulty);
        generator.generateBoard();
        board = new SudokuBoard(generator.getPlayableBoard(), generator.getSolvedBoard());
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
