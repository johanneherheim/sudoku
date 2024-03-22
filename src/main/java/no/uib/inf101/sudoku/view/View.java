package no.uib.inf101.sudoku.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

public class View extends JPanel {

    private final ViewableModel model;

    private IColorTheme colorTheme;

    private static final double INNERMARGIN = 2;

    private static final double OUTERMARGIN = 15;

    public View(ViewableModel model) {
        this.model = model;
        this.colorTheme = new DefaultColorTheme();
        setFocusable(true);
        setPreferredSize(new Dimension(800, 800));
        setBackground(colorTheme.getBackgroundColor());

        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked");
                int row = calculateRowFromYCoordinate(e.getY());
                int col = calculateColumnFromXCoordinate(e.getX());
                System.out.println("Row: " + row + " Col: " + col);

                int currentNumber = model.getBoard().getNumber(row, col);

                int newNumber = (currentNumber + 1) % 10;

                model.getBoard().setNumber(new CellPosition(row, col), newNumber);

                repaint();
            }
        });
    }

    private int calculateRowFromYCoordinate(int y) {
        Rectangle2D boardCanvas = getBoardCanvas();
        CellPositionToPixelConverter tetrisBoardConverter = new CellPositionToPixelConverter(boardCanvas,
                model.getDimension(), INNERMARGIN);
        return tetrisBoardConverter.getRowFromYCoordinate(y);
    }

    private int calculateColumnFromXCoordinate(int x) {
        Rectangle2D boardCanvas = getBoardCanvas();
        CellPositionToPixelConverter tetrisBoardConverter = new CellPositionToPixelConverter(boardCanvas,
                model.getDimension(), INNERMARGIN);
        return tetrisBoardConverter.getColumnFromXCoordinate(x);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    private void drawGame(Graphics2D g2) {
        drawBoard(g2);
    }

    private void drawBoard(Graphics2D g2) {
        Rectangle2D boardCanvas = getBoardCanvas();

        g2.setColor(colorTheme.getGridColor());
        g2.fill(boardCanvas);
        CellPositionToPixelConverter tetrisBoardConverter = new CellPositionToPixelConverter(boardCanvas,
                model.getDimension(), INNERMARGIN);

        drawCells(g2, model.getAllTiles(), tetrisBoardConverter, colorTheme);
    }

    private void drawCells(Graphics2D g2, Iterable<GridCell> cells, CellPositionToPixelConverter converter,
            IColorTheme colorTheme) {

        for (GridCell cell : cells) {

            Rectangle2D cellBounds = converter.getBoundsForCell(cell.pos());
            g2.setColor(colorTheme.getCellColor(cell.isCorrect(), cell.isGiven(), cell.number()));

            g2.fill(cellBounds);
            g2.setColor(colorTheme.getTextColor());
            g2.setFont(new Font(null, Font.BOLD, getWidth() / 30));
            {
                Inf101Graphics.drawCenteredString(g2, Integer.toString(cell.number()), cellBounds.getMinX(),
                        cellBounds.getMinY(),
                        cellBounds.getWidth(), cellBounds.getHeight());
            }
        }
    }

    /**
     * Returns the canvas for the board.
     * 
     * @return The canvas for the board
     */
    private Rectangle2D getBoardCanvas() {
        double maxSize = Math.min(this.getWidth() - 2 * OUTERMARGIN, this.getHeight() - 2 * OUTERMARGIN);
        double x0 = this.getWidth() / 2 - maxSize / 2;
        double y0 = OUTERMARGIN;
        return new Rectangle2D.Double(x0, y0, maxSize, maxSize);
    }
}
