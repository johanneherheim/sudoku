package no.uib.inf101.grid;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains unit tests for the GridCell class.
 * 
 * Modified from semesteroppgave 1 (Tetris), INF101 spring 2024:
 * https://git.app.uib.no/ii/inf101/24v/assignments/Johanne.Herheim_tetris
 * 
 */
public class GridCellTest {

    @Test
    void sanityTest() {
        CellPosition pos = new CellPosition(4, 2);
        Integer number = 5;
        boolean isGiven = true;
        boolean isCorrect = true;
        GridCell gridCell = new GridCell(pos, number, isGiven, isCorrect);

        assertEquals(pos, gridCell.pos());
        assertEquals(number, gridCell.number());
        assertEquals(isGiven, gridCell.isGiven());
        assertEquals(isCorrect, gridCell.isCorrect());
    }

    @Test
    public void testGridCellConstructor() {
        CellPosition pos = new CellPosition(1, 2);
        int number = 5;
        boolean isCorrect = true;
        boolean isGiven = false;

        GridCell cell = new GridCell(pos, number, isCorrect, isGiven);

        assertEquals(pos, cell.pos());
        assertEquals(number, cell.number());
        assertTrue(cell.isCorrect());
        assertFalse(cell.isGiven());
    }

    @Test
    public void gridCellEqualityAndHashCodeTest() {
        CellPosition pos1 = new CellPosition(1, 2);
        CellPosition pos2 = new CellPosition(1, 2);
        int number = 5;
        boolean isCorrect = true;
        boolean isGiven = false;

        GridCell cell1 = new GridCell(pos1, number, isCorrect, isGiven);
        GridCell cell2 = new GridCell(pos2, number, isCorrect, isGiven);

        assertEquals(cell1, cell2);
        assertTrue(Objects.equals(cell1, cell2));
        assertTrue(cell1.hashCode() == cell2.hashCode());
    }

    @Test
    public void testGridCellInequality() {
        CellPosition pos1 = new CellPosition(1, 2);
        CellPosition pos2 = new CellPosition(3, 4);
        int number1 = 5;
        int number2 = 6;
        boolean isCorrect = true;
        boolean isGiven = false;

        GridCell cell1 = new GridCell(pos1, number1, isCorrect, isGiven);
        GridCell cell2 = new GridCell(pos2, number2, isCorrect, isGiven);

        assertFalse(cell1.equals(cell2));
    }

    @Test
    void gridCellInequalityTest() {
        Integer number1 = 2;
        CellPosition pos = new CellPosition(4, 2);
        GridCell gridCell = new GridCell(pos, number1, false, false);

        Integer number2 = 3;
        CellPosition pos2 = new CellPosition(2, 4);

        GridCell gridCell2 = new GridCell(pos2, number1, false, false);
        GridCell gridCell3 = new GridCell(pos, number2, false, false);

        assertFalse(gridCell.equals(gridCell2));
        assertFalse(gridCell.equals(gridCell3));
        assertFalse(gridCell2.equals(gridCell3));

        assertFalse(Objects.equals(gridCell, gridCell2));
        assertFalse(Objects.equals(gridCell, gridCell3));
        assertFalse(Objects.equals(gridCell2, gridCell3));
    }
}
