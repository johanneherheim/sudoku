package no.uib.inf101.sudoku.model;

import java.time.LocalDateTime;

//username, score, finishedAt, timeUsed, lifesUsed, hintsUsed, startBoard, solvedBoard, difficulty
public class Score {
    private String username;
    private Integer score;
    private LocalDateTime finishedAt;
    private int timeUsed;
    private Integer lifesUsed;
    private Integer hintsUsed;
    private String startBoard;
    private String solvedBoard;
    private Integer difficulty;

    public Score(String username, Integer score, LocalDateTime finishedAt, int timeUsed, Integer lifesUsed,
            Integer hintsUsed, String startBoard, String solvedBoard, Integer difficulty) {
        this.username = username;
        this.score = score;
        this.finishedAt = finishedAt;
        this.timeUsed = timeUsed;
        this.lifesUsed = lifesUsed;
        this.hintsUsed = hintsUsed;
        this.startBoard = startBoard;
        this.solvedBoard = solvedBoard;
        this.difficulty = difficulty;
    }

    public String getUsername() {
        return username;
    }

    public Integer getScore() {
        return score;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public int getSeconds() {
        return timeUsed;
    }

    public Integer getLifesUsed() {
        return lifesUsed;
    }

    public Integer getHintsUsed() {
        return hintsUsed;
    }

    public int[][] getStartBoard() {
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = ((int) startBoard.charAt(i * 9 + j));
            }
        }
        return board;
    }

    public int[][] getSolvedBoard() {
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = ((int) solvedBoard.charAt(i * 9 + j));
            }
        }
        return board;
    }

    public Integer getDifficulty() {
        return difficulty;
    }
}
