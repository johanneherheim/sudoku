package no.uib.inf101.sudoku.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.sudoku.model.Difficulty;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.view.GameView;
import no.uib.inf101.sudoku.view.tools.CellPositionToPixelConverter;

public class SudokuController implements KeyListener, ActionListener, MouseListener {

    private ControllableSudokuModel model;
    private GameView view;

    public SudokuController(ControllableSudokuModel model, GameView view) {
        this.model = model;
        this.view = view;
        view.addKeyListener(this);
        view.addMouseListener(this);
        view.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (model.getGameState() == GameState.FINISHED) {
                view.save();
            } else if (model.getGameState() == GameState.MENU) {
                view.start(Difficulty.EASY);
            } else if (model.getGameState() == GameState.WELCOME) {
                view.goToMenu();
            } else if (model.getGameState() == GameState.MY_SCORES) {
                view.goToMenu();
            } else if (model.getGameState() == GameState.LEADERBOARD) {
                view.goToMenu();
            } else if (model.getGameState() == GameState.PLAYING) {
                view.pause();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
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
                model.giveNumberToCell(1);
            } else if (e.getKeyCode() == KeyEvent.VK_2) {
                model.giveNumberToCell(2);
            } else if (e.getKeyCode() == KeyEvent.VK_3) {
                model.giveNumberToCell(3);
            } else if (e.getKeyCode() == KeyEvent.VK_4) {
                model.giveNumberToCell(4);
            } else if (e.getKeyCode() == KeyEvent.VK_5) {
                model.giveNumberToCell(5);
            } else if (e.getKeyCode() == KeyEvent.VK_6) {
                model.giveNumberToCell(6);
            } else if (e.getKeyCode() == KeyEvent.VK_7) {
                model.giveNumberToCell(7);
            } else if (e.getKeyCode() == KeyEvent.VK_8) {
                model.giveNumberToCell(8);
            } else if (e.getKeyCode() == KeyEvent.VK_9) {
                model.giveNumberToCell(9);
            } else if (e.getKeyCode() == KeyEvent.VK_0) {
                model.giveNumberToCell(0);
            }
        }
        System.out.println("Key pressed: " + e.getKeyCode());
        System.out.println("Selected cell: " + model.getSelectedCell());
        if (!model.getCellFromPosition(model.getSelectedCell()).isCorrect()) {
            model.decreaseLifes();
        }
        if (model.isSolved() || model.getLifes() == 0) {
            model.setGameState(GameState.FINISHED);
        }
        view.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getStartButton()
                || e.getSource() == view.getBackButton()) {
            view.goToMenu();

        } else if (e.getSource() == view.getEasyButton()) {
            view.start(Difficulty.EASY);

        } else if (e.getSource() == view.getMediumButton()) {
            view.start(Difficulty.MEDIUM);

        } else if (e.getSource() == view.getHardButton()) {
            view.start(Difficulty.HARD);

        } else if (e.getSource() == view.getResumeButton()) {
            view.resume();

        } else if (e.getSource() == view.getPauseButton()) {
            view.pause();

        } else if (e.getSource() == view.getExitButton()) {
            System.exit(0);

        } else if (e.getSource() == view.getMyScoresButton()) {
            view.myScores();

        } else if (e.getSource() == view.getLeaderboardButton()) {
            view.leaderBoard();

        } else if (e.getSource() == view.getToggleLightModeButton()) {
            view.toggleLightMode();

        } else if (e.getSource() == view.getSaveButton()) {
            view.save();

        } else if (e.getSource() == view.getLogoutButton()) {
            view.logout();
        }
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
