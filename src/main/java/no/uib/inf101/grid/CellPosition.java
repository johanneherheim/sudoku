package no.uib.inf101.grid;

/**
 * A CellPosition consists of a row and a column.
 * 
 * Taken from semesteroppgave 1 (Tetris), INF101 spring 2024:
 * https://git.app.uib.no/ii/inf101/24v/assignments/Johanne.Herheim_tetris
 *
 * @param row what row the cell is located in
 * @param col what column the cell is located in
 */
public record CellPosition(int row, int col) {
}
