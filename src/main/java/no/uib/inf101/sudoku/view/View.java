package no.uib.inf101.sudoku.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.view.colorThemes.DefaultColorTheme;
import no.uib.inf101.sudoku.view.colorThemes.IColorTheme;
import no.uib.inf101.sudoku.view.tools.CellPositionToPixelConverter;
import no.uib.inf101.sudoku.view.tools.Inf101Graphics;

public class View extends JPanel {

    private final IColorTheme colorTheme;

    private final ViewableModel model;

    private static final String FONT = "Helvetica Neue";

    private static final double GRID_WIDTH = 3;

    private static final double UPPER_MARGIN = 100;

    private static final double SIDE_MARGIN = 15;

    public View(ViewableModel model) {
        this.model = model;
        colorTheme = new DefaultColorTheme();
        setFocusable(true);
        setPreferredSize(new Dimension(700, 800));
        setBackground(colorTheme.getBackgroundColor());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    private void drawGame(Graphics2D g2) {
        model.checkIfSolved();
        if (model.getGameState() == GameState.WIN) {
            h1(g2, "SOLVED");
            h3(g2, "Press ENTER to restart");
        } else if (model.getGameState() == GameState.WELCOME) {
            h1(g2, "WELCOME");
            h2(g2, "Press ENTER to start");
        } else if (model.getGameState() == GameState.PLAYING) {
            CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                    getBoardCanvas(), model.getDimension(), GRID_WIDTH);
            header(g2);
            for (GridCell cell : model.getAllTiles()) {
                drawCell(g2, cell, converter);
            }
        }
    }

    private void drawCell(Graphics2D g2, GridCell cell, CellPositionToPixelConverter converter) {
        Rectangle2D cellBounds = converter.getBoundsForCell(cell.pos());
        if (model.getSelectedCell().equals(cell.pos())) {
            g2.setColor(colorTheme.getTextColor());
            g2.draw(cellBounds);
        }
        g2.setColor(colorTheme.getCellColor(cell.isCorrect(), cell.isGiven(), cell.number(), cell.pos(),
                model.getSelectedCell()));
        g2.fill(cellBounds);
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font(FONT, Font.BOLD, Math.min(getWidth() / 30, getHeight() / 30)));
        if (cell.number() != 0) {
            Inf101Graphics.drawCenteredString(g2, Integer.toString(cell.number()), cellBounds.getMinX(),
                    cellBounds.getMinY(),
                    cellBounds.getWidth(), cellBounds.getHeight());
        }
    }

    private Rectangle2D getBoardCanvas() {
        double maxSize = Math.min(getWidth() - 3 * SIDE_MARGIN, getHeight() - 2 * UPPER_MARGIN);
        double x0 = getWidth() / 2 - maxSize / 2;
        double y0 = UPPER_MARGIN * 3 / 2;
        return new Rectangle2D.Double(x0, y0, maxSize, maxSize);
    }

    private void header(Graphics2D g2) {
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font(FONT, Font.BOLD, Math.min(getWidth() / 10, getHeight() / 10)));
        Inf101Graphics.drawCenteredString(g2, "SUDOKU", 0, 0, getWidth(), UPPER_MARGIN * 3 / 2);
    }

    private void h1(Graphics2D g2, String text) {
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font(FONT, Font.BOLD, Math.min(getWidth() / 10, getHeight() / 10)));
        Inf101Graphics.drawCenteredString(g2, text, 0, 0, getWidth(), getHeight() - (int) UPPER_MARGIN);
    }

    private void h2(Graphics2D g2, String text) {
        g2.setFont(new Font(FONT, Font.BOLD, Math.min(getWidth() / 20, getHeight() / 20)));
        Inf101Graphics.drawCenteredString(g2, text, 0, 0, getWidth(), getHeight() + (int) UPPER_MARGIN);
    }

    private void h3(Graphics2D g2, String text) {
        g2.setFont(new Font(FONT, Font.PLAIN, Math.min(getWidth() / 30, getHeight() / 30)));
        Inf101Graphics.drawCenteredString(g2, text, 0, 0, getWidth(), getHeight() * 2 - (int) UPPER_MARGIN);
    }

}
