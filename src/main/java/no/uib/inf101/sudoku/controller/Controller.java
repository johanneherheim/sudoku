package no.uib.inf101.sudoku.controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.view.View;

public class Controller implements java.awt.event.KeyListener {

    private IControllableModel model;

    private View view;

    public Controller(IControllableModel model, View view) {
        this.model = model;
        this.view = view;
        view.addKeyListener(this);
        view.setFocusable(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameState() == GameState.WELCOME) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                model.setGameState(GameState.PLAYING);
            }
        } else if (model.getGameState() == GameState.PLAYING) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (model.getSelectedCell().row() > 0) {
                    model.setSelectedCell(
                            new CellPosition(model.getSelectedCell().row() - 1, model.getSelectedCell().col()));
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (model.getSelectedCell().row() < model.getDimension().getRows() - 1) {
                    model.setSelectedCell(
                            new CellPosition(model.getSelectedCell().row() + 1, model.getSelectedCell().col()));
                }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (model.getSelectedCell().col() > 0) {
                    model.setSelectedCell(
                            new CellPosition(model.getSelectedCell().row(), model.getSelectedCell().col() - 1));
                }
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (model.getSelectedCell().col() < model.getDimension().getCols() - 1) {
                    model.setSelectedCell(
                            new CellPosition(model.getSelectedCell().row(), model.getSelectedCell().col() + 1));
                }

            } else {
                if (e.getKeyCode() == KeyEvent.VK_1) {
                    model.setCurrentNumber(1);
                } else if (e.getKeyCode() == KeyEvent.VK_2) {
                    model.setCurrentNumber(2);
                } else if (e.getKeyCode() == KeyEvent.VK_3) {
                    model.setCurrentNumber(3);
                } else if (e.getKeyCode() == KeyEvent.VK_4) {
                    model.setCurrentNumber(4);
                } else if (e.getKeyCode() == KeyEvent.VK_5) {
                    model.setCurrentNumber(5);
                } else if (e.getKeyCode() == KeyEvent.VK_6) {
                    model.setCurrentNumber(6);
                } else if (e.getKeyCode() == KeyEvent.VK_7) {
                    model.setCurrentNumber(7);
                } else if (e.getKeyCode() == KeyEvent.VK_8) {
                    model.setCurrentNumber(8);
                } else if (e.getKeyCode() == KeyEvent.VK_9) {
                    model.setCurrentNumber(9);
                } else if (e.getKeyCode() == KeyEvent.VK_0) {
                    model.setCurrentNumber(0);
                }
                model.getBoard().setNumber(model.getSelectedCell(), model.getCurrentNumber());
            }
        } else if (model.getGameState() == GameState.WIN)

        {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                model.restart();
            }
        }
        view.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
