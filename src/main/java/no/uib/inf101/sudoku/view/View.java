package no.uib.inf101.sudoku.view;

import java.awt.BorderLayout;
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

    private boolean[] isClicked = new boolean[10];

    private IColorTheme colorTheme;

    private static final double GRID_WIDTH = 2;

    private static final double UPPER_MARGIN = 100;

    private static final double SIDE_MARGIN = 15;

    public View(ViewableModel model) {
        this.model = model;
        this.colorTheme = new DefaultColorTheme();
        setLayout(new BorderLayout()); // Set BorderLayout for the View JPanel

        setFocusable(true);
        setPreferredSize(new Dimension(600, 800));
        setBackground(colorTheme.getBackgroundColor());

        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Check if the click is within the bounds of the board
                if (isWithinBounds(e.getX(), e.getY(), getBoardCanvas())) {
                    int row = calculateRowFromYCoordinate(e.getY(), getBoardCanvas());
                    int col = calculateColumnFromXCoordinate(e.getX(), getBoardCanvas());
                    // Handle click on the board
                    model.getBoard().setNumber(new CellPosition(row, col), model.getCurrentNumber());

                    System.out.println("clicked" + row + " " + col);

                } else if (isWithinBounds(e.getX(), e.getY(), getNumberCanvas())) {
                    int index = calculateColumnFromXCoordinate(e.getX(), getNumberCanvas());
                    // Handle click on the number buttons
                    for (int i = 0; i < 9; i++) {
                        if (i == index) {
                            isClicked[i] = !isClicked[i];
                            model.setCurrentNumber(i + 1);
                        } else {
                            isClicked[i] = false;
                        }
                    }
                    System.out.println("index: " + index);
                } else {
                    System.out.println("wrong click");
                }
                repaint();
            }
        });

        for (int i = 0; i < 9; i++) {
            isClicked[i] = false;
        }
        System.out.println(isClicked);
    }

    private int calculateRowFromYCoordinate(int y, Rectangle2D canvas) {
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(canvas,
                model.getDimension(), GRID_WIDTH);
        return converter.getRowFromYCoordinate(y);
    }

    private int calculateColumnFromXCoordinate(int x, Rectangle2D canvas) {
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(canvas,
                model.getDimension(), GRID_WIDTH);
        return converter.getColumnFromXCoordinate(x);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    private void drawGame(Graphics2D g2) {
        drawBoard(g2);
        drawNumberButtons(g2);
    }

    private void drawBoard(Graphics2D g2) {
        Rectangle2D boardCanvas = getBoardCanvas();

        g2.setColor(colorTheme.getGridColor());
        g2.fill(boardCanvas);
        CellPositionToPixelConverter tetrisBoardConverter = new CellPositionToPixelConverter(boardCanvas,
                model.getDimension(), GRID_WIDTH);

        drawCells(g2, model.getAllTiles(), tetrisBoardConverter, colorTheme);
    }

    private void drawCells(Graphics2D g2, Iterable<GridCell> cells, CellPositionToPixelConverter converter,
            IColorTheme colorTheme) {

        for (GridCell cell : cells) {

            Rectangle2D cellBounds = converter.getBoundsForCell(cell.pos());
            g2.setColor(colorTheme.getCellColor(cell.isCorrect(), cell.isGiven(), cell.number()));

            g2.fill(cellBounds);
            g2.setColor(colorTheme.getTextColor());
            g2.setFont(new Font(null, Font.BOLD, Math.min(getWidth() / 30, getHeight() / 30)));
            {
                Inf101Graphics.drawCenteredString(g2, Integer.toString(cell.number()), cellBounds.getMinX(),
                        cellBounds.getMinY(),
                        cellBounds.getWidth(), cellBounds.getHeight());
            }
        }
    }

    private void drawNumberButtons(Graphics2D g2) {
        Rectangle2D numberCanvas = getNumberCanvas();
        CellPositionToPixelConverter numberButtonConverter = new CellPositionToPixelConverter(numberCanvas,
                model.getDimension(), GRID_WIDTH * 2);
        for (int i = 0; i < 9; i++) {
            Rectangle2D cellBounds = numberButtonConverter.getBoundsForCell(new CellPosition(0, i));
            g2.setColor(colorTheme.getButtonColor(isClicked[i]));
            g2.fill(cellBounds);
            g2.setColor(colorTheme.getTextColor());
            g2.setFont(new Font(null, Font.BOLD, Math.min(getWidth() / 30, getHeight() / 30)));
            Inf101Graphics.drawCenteredString(g2, Integer.toString(i + 1), cellBounds.getMinX(), cellBounds.getMinY(),
                    cellBounds.getWidth(), cellBounds.getHeight());
        }
    }

    private boolean isWithinBounds(int x, int y, Rectangle2D canvas) {
        return x >= canvas.getMinX() && x <= canvas.getMaxX() && y >= canvas.getMinY() && y <= canvas.getMaxY();
    }

    /**
     * Returns the canvas for the board.
     * 
     * @return The canvas for the board
     */
    private Rectangle2D getBoardCanvas() {
        double maxSize = Math.min(getWidth() - 2 * SIDE_MARGIN, getHeight() - 2 * UPPER_MARGIN);
        double x0 = getWidth() / 2 - maxSize / 2;
        double y0 = UPPER_MARGIN;
        return new Rectangle2D.Double(x0, y0, maxSize, maxSize);
    }

    /**
     * Returns the canvas for the number buttons.
     * 
     * @return The canvas for the number buttons
     */
    private Rectangle2D getNumberCanvas() {
        double maxSize = Math.min(getWidth() - 2 * SIDE_MARGIN, getHeight() - 2 * UPPER_MARGIN);
        double x0 = getWidth() / 2 - maxSize / 2;
        double y0 = maxSize + SIDE_MARGIN * 2 + UPPER_MARGIN;
        return new Rectangle2D.Double(x0, y0, maxSize, maxSize);
    };
}
