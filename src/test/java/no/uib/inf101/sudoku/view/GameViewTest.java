package no.uib.inf101.sudoku.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sudoku.dao.ScoreUtils;
import no.uib.inf101.sudoku.model.Difficulty;
import no.uib.inf101.sudoku.model.GameState;

public class GameViewTest {
    private static final int BOARD_WIDTH = 500;
    private static final int HEADER_HEIGHT = 150;
    private static final int OUTER_MARGIN = 30;
    private static final String username = "Test";
    private GameView gameView = new GameView(username);
    ScoreUtils scoreUtils = new ScoreUtils();

    @Test
    public void testInitialization() {
        assertNotNull(gameView);
        assertEquals("Sudoku", gameView.getTitle());
        assertEquals(GameView.GAME_DIMENSION.width, gameView.getWidth());
        assertEquals(GameView.GAME_DIMENSION.height, gameView.getHeight());
    }

    @Test
    public void testStartButtonAction() {
        JButton startButton = gameView.getStartButton();
        assertNotNull(startButton);

        // Simulate button click
        ActionListener[] actionListeners = startButton.getActionListeners();
        assertNotNull(actionListeners);
        assertEquals(1, actionListeners.length);

        // Invoke action event
        ActionEvent mockEvent = new ActionEvent(startButton, ActionEvent.ACTION_PERFORMED, "Start");
        actionListeners[0].actionPerformed(mockEvent);
    }

    @Test
    public void testDrawLifes() {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setPaintMode();

        assertEquals(" 💚 💚 💔 ", gameView.lifeToLifeString(2));

        assertEquals(" 💚 💔 💔 ", gameView.lifeToLifeString(1));

        assertEquals(" 💔 💔 💔 ", gameView.lifeToLifeString(0));

        assertEquals(" 💚 💚 💚 ", gameView.lifeToLifeString(3));
    }

    @Test
    public void testDifficultyNumberToString() {
        assertEquals("EASY", gameView.difficultyNumberToString(20));
        assertEquals("MEDIUM", gameView.difficultyNumberToString(40));
        assertEquals("HARD", gameView.difficultyNumberToString(55));
        assertEquals("UNKNOWN DIFFICULTY", gameView.difficultyNumberToString(100));
    }

    @Test
    public void testGetBoardCanvas() {
        gameView.setSize(GameView.GAME_DIMENSION);
        double expectedX = gameView.getWidth() / 2.0 - BOARD_WIDTH / 2.0;
        double expectedY = HEADER_HEIGHT + OUTER_MARGIN;
        double expectedWidth = BOARD_WIDTH;
        double expectedHeight = BOARD_WIDTH;

        Rectangle2D boardCanvas = gameView.getBoardCanvas();

        assertEquals(expectedX, boardCanvas.getX(), 0.01);
        assertEquals(expectedY, boardCanvas.getY(), 0.01);
        assertEquals(expectedWidth, boardCanvas.getWidth(), 0.01);
        assertEquals(expectedHeight, boardCanvas.getHeight(), 0.01);
    }

    @Test
    public void testGoToMenu() {
        gameView.goToMenu();
        assertEquals(GameState.MENU, gameView.getModel().getGameState());
        assertTrue(gameView.cardPanel.getComponent(1) instanceof JPanel);
        JPanel menuPanel = (JPanel) gameView.cardPanel.getComponent(1);
        assertEquals(11, menuPanel.getComponentCount());

        // Checking if the labels are correctly placed
        assertEquals(" Nytt spill: ", ((JLabel) menuPanel.getComponent(0)).getText());
        assertEquals(100, menuPanel.getComponent(0).getX());
        assertEquals(310, menuPanel.getComponent(0).getY());
        assertEquals(300, menuPanel.getComponent(0).getWidth());
        assertEquals(30, menuPanel.getComponent(0).getHeight());

        assertEquals(" Resultatlister:", ((JLabel) menuPanel.getComponent(1)).getText());
        assertEquals(100, menuPanel.getComponent(1).getX());
        assertEquals(410, menuPanel.getComponent(1).getY());
        assertEquals(300, menuPanel.getComponent(1).getWidth());
        assertEquals(30, menuPanel.getComponent(1).getHeight());
    }

    @Test
    public void testStart() {
        gameView.start(Difficulty.EASY);
        assertEquals(GameState.PLAYING, gameView.getModel().getGameState());
        assertFalse(gameView.playingScreen.getComponent(0) instanceof JPanel);
    }

    @Test
    public void testPause() {
        gameView.pause();
        assertEquals(GameState.MENU, gameView.getModel().getGameState());
        assertTrue(gameView.menuScreen.getComponent(0) instanceof JButton);
    }

    @Test
    public void testResume() {
        gameView.resume();
        assertEquals(GameState.PLAYING, gameView.getModel().getGameState());
    }

    @Test
    public void testMyScores() {
        gameView.myScores();
        assertEquals(GameState.MY_SCORES, gameView.getModel().getGameState());
        assertTrue(gameView.myScoresScreen.getComponent(0) instanceof JButton);
    }

    @Test
    public void testLeaderBoard() {
        gameView.leaderBoard();
        assertEquals(GameState.LEADERBOARD, gameView.getModel().getGameState());
        assertTrue(gameView.leaderboardScreen.getComponent(0) instanceof JButton);
    }

    @Test
    public void testFinished() {
        gameView.finished();
        assertEquals(GameState.FINISHED, gameView.getModel().getGameState());
    }

    @Test
    public void testSave() {
        gameView.getModel().startGame(Difficulty.EASY);
        gameView.save();
        assertEquals(GameState.MENU, gameView.getModel().getGameState());
        scoreUtils.deleteScoreToUser(username);
    }

    @Test
    public void testLogout() {
        gameView.logout();
        assertFalse(gameView.isDisplayable());
    }

    @Test
    public void testToggleLightMode() {

        gameView.toggleLightMode();
        assertFalse(gameView.isDefaultColorTheme);

        gameView.toggleLightMode();
        assertTrue(gameView.isDefaultColorTheme);

        gameView.toggleLightMode();
        assertFalse(gameView.isDefaultColorTheme);
    }

}
