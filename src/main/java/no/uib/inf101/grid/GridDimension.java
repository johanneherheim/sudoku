package no.uib.inf101.grid;

/**
 * Interface for classes that represent the dimensions of a grid.
 * Modified from semesteroppgave 1 (Tetris), INF101 spring 2024:
 * https://git.app.uib.no/ii/inf101/24v/assignments/Johanne.Herheim_tetris
 */
public interface GridDimension {
    /**
     * Get the number of columns in the grid.
     *
     * @return the number of columns
     */
    int getCols();

    /**
     * Get the number of rows in the grid.
     *
     * @return the number of rows
     */
    int getRows();
}
