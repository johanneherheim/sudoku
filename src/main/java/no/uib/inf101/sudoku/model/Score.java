package no.uib.inf101.sudoku.model;

import java.time.LocalDateTime;

//username, score, finishedAt, timeUsed, lifesUsed, hintsUsed, startBoard, solvedBoard, difficulty
public record Score(String username, Integer score, LocalDateTime finishedAt, int timeUsed, Integer lifesUsed,
        Integer hintsUsed, String startBoard, String solvedBoard, Integer difficulty) {

}
