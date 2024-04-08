package no.uib.inf101.sudoku.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.sudoku.controller.ControllableSudokuModel;
import no.uib.inf101.sudoku.view.ViewableSudokuModel;

public class SudokuModel implements ViewableSudokuModel, ControllableSudokuModel {
    private static SudokuBoard board;
    private static Generator generator;
    private static Integer currentNumber = 1;
    private static CellPosition selectedCell;
    private Integer count;
    private GameState gameState;
    private long startTimeMillis;

    public SudokuModel() {
        generator = new Generator();
        generator.generateBoard();
        board = new SudokuBoard(generator.getPlayableBoard(), generator.getSolvedBoard());
        gameState = GameState.WELCOME;
        startTimeMillis = System.currentTimeMillis();
        selectedCell = new CellPosition(0, 0);
        count = 0;
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
        // stack overflow
        // https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
        // 7. mars 2024
        try (FileWriter fw = new FileWriter("db/scores.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(getTimeElapsed());
        } catch (IOException e) {
            System.err.println("An error occurred while saving your time.");
        }
    }

    @Override
    public void restart() {
        generator = new Generator();
        generator.generateBoard();
        board = new SudokuBoard(generator.getPlayableBoard(), generator.getSolvedBoard());
        gameState = GameState.PLAYING;
        count = 0;
        startTimeMillis = System.currentTimeMillis();
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
