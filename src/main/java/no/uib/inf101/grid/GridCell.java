package no.uib.inf101.grid;

/**
 * A GridCell consists of a position, a number, a boolean indicating if the
 * number is correct and a boolean indicating if the number is given.
 *
 * @param pos       the position of the cell
 * @param number    the number in the cell
 * @param isCorrect true if the number is correct, false otherwise
 * @param isGiven   true if the number is given, false otherwise
 */
public record GridCell(CellPosition pos, int number, boolean isCorrect, boolean isGiven) {
}
