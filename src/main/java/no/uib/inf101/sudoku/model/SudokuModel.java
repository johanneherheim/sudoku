package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.controller.ControllableSudokuModel;
import no.uib.inf101.sudoku.dao.ScoreUtils;
import no.uib.inf101.sudoku.view.ViewableSudokuModel;

public class SudokuModel implements ViewableSudokuModel, ControllableSudokuModel {
    private SudokuBoard board;
    private Generator generator;
    private CellPosition selectedCell;
    private GameState gameState;
    private ScoreUtils scoreQueries = new ScoreUtils();
    private String username;
    Difficulty difficulty;
    int time;
    int lifes;

    public SudokuModel(String username) {
        gameState = GameState.WELCOME;
        difficulty = Difficulty.EASY;
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
        // compare the solution with the current board
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                if (board.getSolution()[row][col] != board.getNumber(row, col)) {
                    return false;
                }
            }
        }
        // If the board is solved, set the game state to finished
        gameState = GameState.FINISHED;
        return true;
    }

    public void saveGame() {
        scoreQueries.insertScore(username, 0, time, lifes, 0, generator.getPlayableBoard(),
                generator.getSolvedBoard(), generator.difficultyEnumToId(difficulty));
    }

    public void start(Difficulty difficulty) {
        this.difficulty = difficulty;
        lifes = 3;
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

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public void decreaseLifes() {
        lifes--;
    }

    public GridCell getCellFromPosition(CellPosition cell) {
        for (GridCell gridCell : board) {
            if (gridCell.pos().equals(cell)) {
                return gridCell;
            }
        }
        throw new IllegalArgumentException("This cell does not exist in the board.");
    }
}
