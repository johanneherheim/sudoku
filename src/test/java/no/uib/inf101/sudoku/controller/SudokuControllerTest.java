package no.uib.inf101.sudoku.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.model.Difficulty;
import no.uib.inf101.sudoku.model.SudokuModel;

public class SudokuControllerTest {

    SudokuModel sudokuModel = new SudokuModel("Test Testesen");

    @Test
    public void sanityTest() {
        sudokuModel.start(Difficulty.EASY);
        assertEquals(new CellPosition(0, 0), sudokuModel.getSelectedCell());
    }
}
