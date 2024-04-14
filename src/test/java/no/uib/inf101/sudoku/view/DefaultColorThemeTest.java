package no.uib.inf101.sudoku.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.sudoku.view.colorthemes.DefaultColorTheme;

public class DefaultColorThemeTest {

    @Test
    public void sanityDefaultColorThemeTest() {

        DefaultColorTheme theme = new DefaultColorTheme();
        CellPosition selectedCell = new CellPosition(1, 1);
        GridCell wrongCell = new GridCell(new CellPosition(0, 0), 1, false, false);
        GridCell cornerCell = new GridCell(new CellPosition(0, 0), 1, true, false);
        GridCell middleCell = new GridCell(new CellPosition(4, 2), 1, true, false);

        assertEquals(new Color(204, 76, 97), theme.getCellColor(wrongCell, selectedCell));

        assertEquals(new Color(45, 45, 45), theme.getCellColor(cornerCell, selectedCell));

        assertEquals(new Color(35, 35, 35), theme.getCellColor(middleCell, selectedCell));

        assertEquals(new Color(26, 26, 26), theme.getBackgroundColor());

        assertEquals(Color.LIGHT_GRAY, theme.getTextColor());
    }
}
