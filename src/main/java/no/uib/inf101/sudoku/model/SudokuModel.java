package no.uib.inf101.sudoku.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.controller.ControllableSudokuModel;
import no.uib.inf101.sudoku.dao.ScoreUtils;
import no.uib.inf101.sudoku.view.ViewableSudokuModel;

/**
 * The SudokuModel class represents the model component of the Sudoku game.
 * It implements both the ViewableSudokuModel and ControllableSudokuModel
 * interfaces. This class is responsible for managing the game state, the Sudoku
 * board, and the player's progress.
 */
public class SudokuModel implements ViewableSudokuModel, ControllableSudokuModel {

    /** The SudokuBoard object representing the game board. */
    private SudokuBoard board;

    /** The Generator object used to generate the Sudoku board. */
    private Generator generator;

    /** The currently selected cell position. */
    private CellPosition selectedCell;

    /** The current state of the game. */
    private GameState gameState;

    /** The utility class for score-related queries. */
    private ScoreUtils scoreQueries = new ScoreUtils();

    /** The username of the player */
    private String username;

    /** The difficulty level of the game. */
    private Difficulty difficulty;

    /** The time taken to complete the game. */
    private int time;

    /** The number of remaining lives. */
    private int lifes;

    /**
     * Constructs a SudokuModel object with the specified username.
     * 
     * @param username the username of the player
     */
    public SudokuModel(String username) {
        this.gameState = GameState.WELCOME;
        this.difficulty = Difficulty.EASY;
        this.username = username;
        this.selectedCell = new CellPosition(0, 0);
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

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int getLifes() {
        return lifes;
    }

    @Override
    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    @Override
    public void decreaseLifes() {
        lifes--;
    }

    @Override
    public GridCell getCellFromPosition(CellPosition cell) {
        for (GridCell gridCell : board) {
            if (gridCell.pos().equals(cell)) {
                return gridCell;
            }
        }
        throw new IllegalArgumentException("This cell does not exist in the board.");
    }

    @Override
    public void saveGame() {
        scoreQueries.insertScore(username, 0, time, lifes, 0, generator.getPlayableBoard(),
                generator.getSolvedBoard(), generator.difficultyToCellsRemoved(difficulty));
    }

    @Override
    public void startGame(Difficulty difficulty) {
        this.difficulty = difficulty;
        lifes = 3;
        gameState = GameState.PLAYING;

        generator = new Generator(difficulty);
        generator.generateBoard();
        board = new SudokuBoard(generator.getPlayableBoard(), generator.getSolvedBoard());
    }
}
