package no.uib.inf101.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GridTest {

    @Test
    void gridInitializationTest() {
        int[][] startValues = {
                { 0, 2, 0 },
                { 0, 0, 3 },
                { 1, 0, 0 }
        };

        int[][] solution = {
                { 3, 2, 1 },
                { 1, 3, 3 },
                { 1, 2, 3 }
        };

        Grid grid = new Grid(3, 3, startValues, solution);

        assertEquals(3, grid.getRows());
        assertEquals(3, grid.getCols());

        // Check if the grid is initialized correctly
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                assertEquals(startValues[row][col], grid.getNumber(row, col));
                assertEquals(solution[row][col] != 0, grid.isCorrect(row, col));
            }
        }
    }

    @Test
    void gridSetNumberTest() {
        int[][] startValues = {
                { 0, 2, 0 },
                { 0, 0, 3 },
                { 1, 0, 0 }
        };

        int[][] solution = {
                { 3, 2, 1 },
                { 1, 3, 3 },
                { 1, 2, 3 }
        };

        Grid grid = new Grid(3, 3, startValues, solution);

        // Set a number in the grid
        grid.setNumber(new CellPosition(0, 0), 3);

        // Check if the number is set correctly
        assertEquals(3, grid.getNumber(0, 0));
        assertTrue(grid.isCorrect(0, 0));

        // Try setting a number in a cell that is given
        grid.setNumber(new CellPosition(2, 0), 2);

        // Check if the number is not set since the cell is given
        assertNotEquals(2, grid.getNumber(2, 0));
    }

    @Test
    void gridIteratorTest() {
        int[][] startValues = { { 1, 2 }, { 3, 4 } };
        int[][] solution = { { 1, 2 }, { 3, 4 } };
        Grid grid = new Grid(2, 2, startValues, solution);

        int count = 0;
        for (GridCell cell : grid) {
            assertNotNull(cell);
            count++;
        }

        assertEquals(grid.getRows() * grid.getCols(), count);
    }

    @Test
    void gridSetNumberGivenCellTest() {
        int[][] startValues = { { 1, 2 }, { 3, 0 } };
        int[][] solution = { { 1, 2 }, { 3, 4 } };
        Grid grid = new Grid(2, 2, startValues, solution);

        grid.setNumber(new CellPosition(0, 0), 0);
        assertEquals(1, grid.getNumber(0, 0));
        assertTrue(grid.isCorrect(0, 0));
        assertTrue(grid.isGiven(0, 0));

        grid.setNumber(new CellPosition(1, 1), 5);
        assertEquals(5, grid.getNumber(1, 1));
        assertFalse(grid.isCorrect(1, 1));
        assertTrue(!grid.isGiven(1, 1));
    }

}
