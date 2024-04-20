package no.uib.inf101.sudoku.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import no.uib.inf101.sudoku.model.Difficulty;
import no.uib.inf101.sudoku.view.GameView;

public class ButtonController implements ActionListener {

    private GameView gameView;

    public ButtonController(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameView.getStartButton()
                || e.getSource() == gameView.getBackButton1()
                || e.getSource() == gameView.getBackButton2()
                || e.getSource() == gameView.getNoSaveButton()) {
            gameView.goToMenu();

        } else if (e.getSource() == gameView.getEasyButton()) {
            gameView.start(Difficulty.EASY);

        } else if (e.getSource() == gameView.getMediumButton()) {
            gameView.start(Difficulty.MEDIUM);

        } else if (e.getSource() == gameView.getHardButton()) {
            gameView.start(Difficulty.HARD);

        } else if (e.getSource() == gameView.getResumeButton()) {
            gameView.resume();

        } else if (e.getSource() == gameView.getPauseButton()) {
            gameView.pause();

        } else if (e.getSource() == gameView.getExitButton()) {
            System.exit(0);

        } else if (e.getSource() == gameView.getMyScoresButton()) {
            gameView.myScores();

        } else if (e.getSource() == gameView.getLeaderboardButton()) {
            gameView.leaderBoard();

        } else if (e.getSource() == gameView.getToggleLightModeButton()) {
            gameView.toggleLightMode();

        } else if (e.getSource() == gameView.getSaveButton()) {
            gameView.save();

        }
    }
}
