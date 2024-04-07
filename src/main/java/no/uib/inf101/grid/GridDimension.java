package no.uib.inf101.grid;

/**
 * Interface for classes that represent the dimensions of a grid.
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
