package no.uib.inf101.grid;

/**
 * Interface for classes that represent a grid.
 * Modified IGrid from semesteroppgave 1 (Tetris), INF101 spring 2024:
 * https://git.app.uib.no/ii/inf101/24v/assignments/Johanne.Herheim_tetris
 */
public interface IGrid extends GridDimension, Iterable<GridCell> {

    /**
     * Set the number in the cell at the given position.
     * 
     * @param row    the row the cell is located in
     * @param col    the column the cell is located in
     * @param number the new number
     */
    void setNumber(CellPosition cellPosition, int number);

    /**
     * Get the number in the cell at the given position.
     * 
     * @param row the row the cell is located in
     * @param col the column the cell is located in
     * @return the number in the cell
     */
    Integer getNumber(int row, int col);

    /**
     * Check if the number in the cell at the given position is correct.
     * 
     * @param row the row the cell is located in
     * @param col the column the cell is located in
     * @return true if the number is correct, false otherwise
     */
    boolean isCorrect(int row, int col);

    /**
     * Check if the number in the cell at the given position is given.
     * 
     * @param row the row the cell is located in
     * @param col the column the cell is located in
     * @return true if the number is given, false otherwise
     */
    boolean isGiven(int row, int col);

}
