package no.uib.inf101.sudoku.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.view.GameView;
import no.uib.inf101.sudoku.view.tools.CellPositionToPixelConverter;

public class MouseController implements MouseListener {

    private ControllableSudokuModel model;
    private GameView view;

    public MouseController(ControllableSudokuModel model, GameView view) {
        this.model = model;
        this.view = view;
        view.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (model.getGameState() == GameState.PLAYING) {
            CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                    view.getBoardCanvas(), model.getDimension(), GameView.INNER_MARGIN);
            int mouseX = e.getX();
            int mouseY = e.getY();
            model.setSelectedCell(new CellPosition(converter.getRowFromYCoordinate(mouseY),
                    converter.getColumnFromXCoordinate(mouseX)));
        }
        view.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (model.getGameState() == GameState.PLAYING) {
            CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                    view.getBoardCanvas(), model.getDimension(), GameView.INNER_MARGIN);
            int mouseX = e.getX();
            int mouseY = e.getY();
            model.setSelectedCell(new CellPosition(converter.getRowFromYCoordinate(mouseY),
                    converter.getColumnFromXCoordinate(mouseX)));
        }
        view.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (model.getGameState() == GameState.PLAYING) {
            CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                    view.getBoardCanvas(), model.getDimension(), GameView.INNER_MARGIN);
            int mouseX = e.getX();
            int mouseY = e.getY();
            model.setSelectedCell(new CellPosition(converter.getRowFromYCoordinate(mouseY),
                    converter.getColumnFromXCoordinate(mouseX)));
        }
        view.repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}