package no.uib.inf101.sudoku.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.*;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.sudoku.controller.SudokuController;
import no.uib.inf101.sudoku.dao.ScoreUtils;
import no.uib.inf101.sudoku.model.Difficulty;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.model.Score;
import no.uib.inf101.sudoku.model.SudokuModel;
import no.uib.inf101.sudoku.view.colorthemes.ColorTheme;
import no.uib.inf101.sudoku.view.colorthemes.DefaultColorTheme;
import no.uib.inf101.sudoku.view.colorthemes.LightColorTheme;
import no.uib.inf101.sudoku.view.tools.CellPositionToPixelConverter;
import no.uib.inf101.sudoku.view.tools.Inf101Graphics;

public class GameView extends JFrame {

    // constant values
    private static final String FONT = "Helvetica Neue";
    public static final int INNER_MARGIN = 3;
    private static final int OUTER_MARGIN = 30;
    private static final int HEADER_HEIGHT = 150;
    private static final int BOARD_WIDTH = 500;
    protected static final Dimension GAME_DIMENSION = new Dimension(BOARD_WIDTH + OUTER_MARGIN * 2,
            BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);

    // instance variables
    private SudokuModel model;
    CardLayout cardLayout;
    JPanel cardPanel;
    private ColorTheme colorTheme;
    private String name;
    private Timer timer;
    private int secondsPassed;
    boolean isDefaultColorTheme = false;
    private Stack<String> cardHistory = new Stack<>();

    // database
    private ScoreUtils scoreQueries = new ScoreUtils();
    private List<Score> allScores, userScores;

    SudokuController controller;

    JButton startButton = new JButton("Start"),

            easyButton = new JButton("Lett"),
            mediumButton = new JButton("Middels"),
            hardButton = new JButton("Vanskeleg"),

            pauseButton = new JButton("Pause"),

            resumeButton = new JButton("Fortsett"),
            exitButton = new JButton("Avslutt"),
            myScoresButton = new JButton("Mine resultat"),
            leaderboardButton = new JButton("Topplista"),
            toggleLightModeButton = new JButton("🌞"),

            backButton = new JButton("Tilbake"),
            saveButton = new JButton("Lagra"),
            logoutButton = new JButton("Logg ut");

    ArrayList<JButton> allButtons = new ArrayList<JButton>(
            List.of(startButton, easyButton, mediumButton, hardButton, pauseButton, resumeButton, exitButton,
                    myScoresButton, leaderboardButton, backButton, toggleLightModeButton, saveButton, logoutButton));

    JPanel welcomeScreen = new JPanel(null),
            menuScreen = new JPanel(null),
            playingScreen = new JPanel(null),
            gameoverScreen = new JPanel(null),
            myScoresScreen = new JPanel(null),
            leaderboardScreen = new JPanel(null);

    ArrayList<JPanel> allPanels = new ArrayList<JPanel>(
            List.of(welcomeScreen, playingScreen, menuScreen, gameoverScreen,
                    myScoresScreen, leaderboardScreen));

    GamePanel playingPanel = new GamePanel();
    GamePanel myScoresPanel = new GamePanel();
    GamePanel leaderboardPanel = new GamePanel();
    GamePanel menuPanel = new GamePanel();
    GamePanel welcomePanel = new GamePanel();

    ArrayList<GamePanel> allGamePanels = new ArrayList<GamePanel>(
            List.of(playingPanel, myScoresPanel, leaderboardPanel, menuPanel, welcomePanel));

    JLabel title = new JLabel();
    JLabel gif = new JLabel();
    JLabel saveText = new JLabel("Vil du lagra resultatet?");

    public GameView(String username) {

        setTitle("Sudoku");
        setSize(GAME_DIMENSION);
        setResizable(false);

        model = new SudokuModel(username);

        controller = new SudokuController(model, this);

        name = username;
        allScores = scoreQueries.getAllScores();
        userScores = scoreQueries.getScoreFromUser(username);

        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);

        toggleLightMode();
        addActionListeners();

        welcomePanel.setBounds(0, 0, BOARD_WIDTH + OUTER_MARGIN * 2,
                BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);
        welcomePanel.setBackground(colorTheme.getBackgroundColor());

        // adding startbutton to welcome screen
        startButton.setBounds(getWidth() / 2 - 75, 400, 150, 50);
        startButton.setLayout(null);
        startButton.setContentAreaFilled(false);
        welcomeScreen.add(startButton);
        welcomeScreen.add(welcomePanel);

        cardPanel.add(welcomeScreen, "1");
        cardPanel.add(menuScreen, "2");
        cardPanel.add(playingScreen, "3");
        cardPanel.add(gameoverScreen, "4");
        cardPanel.add(myScoresScreen, "5");
        cardPanel.add(leaderboardScreen, "6");
        playingScreen.setFocusable(true);

        playingPanel.setBackground(colorTheme.getBackgroundColor());

        getContentPane().add(cardPanel, BorderLayout.CENTER);

        startTimer();
    }

    private void addActionListeners() {
        for (JButton button : allButtons) {
            button.addActionListener(controller);
            button.setFocusable(false);
        }
    }

    // Override the method to change the card layout
    private void changeCard(String newCard) {
        cardHistory.push(newCard); // Push the current card to history
        cardLayout.show(cardPanel, newCard);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getGameState() == GameState.PLAYING) {
                    secondsPassed++;
                    repaint();
                }
            }
        });
        timer.start();
    }

    private class GamePanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            drawGame(g2);
        }

    }

    private void drawGame(Graphics2D g2) {
        if (model.getGameState() == GameState.FINISHED) {
            finished();
        } else if (model.getGameState() == GameState.MY_SCORES) {
            cardLayout.show(cardPanel, "5");
            updateQueries();
            drawHeader(g2, "Dine resultat");
            drawScores(g2, userScores, true);
        } else if (model.getGameState() == GameState.LEADERBOARD) {
            cardLayout.show(cardPanel, "6");
            updateQueries();
            drawHeader(g2, "Topplista");
            drawScores(g2, allScores, false);
        } else if (model.getGameState() == GameState.PLAYING) {
            cardLayout.show(cardPanel, "3");
            CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                    getBoardCanvas(), model.getDimension(), INNER_MARGIN);
            drawHeader(g2, "SUDOKU");
            drawTimer(g2);
            drawLifes(g2, model.getLifes());
            for (GridCell cell : model.getAllTiles()) {
                drawCell(g2, cell, converter);
            }
        } else if (model.getGameState() == GameState.MENU) {
            cardLayout.show(cardPanel, "2");
            drawHeader(g2, "MENY");
        } else if (model.getGameState() == GameState.WELCOME) {
            cardLayout.show(cardPanel, "1");
            drawHeader(g2, "SUDOKU");
            drawText(g2, "Velkomen, " + name + "!");

        }
        model.setTime(secondsPassed);
    }

    private void drawCell(Graphics2D g2, GridCell cell, CellPositionToPixelConverter converter) {
        Rectangle2D cellBounds = converter.getBoundsForCell(cell.pos());
        if (model.getSelectedCell().equals(cell.pos())) {
            g2.setColor(colorTheme.getTextColor());
            g2.draw(cellBounds);
        }
        g2.setColor(colorTheme.getCellColor(cell,
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

    public Rectangle2D getBoardCanvas() {
        double x0 = getWidth() / 2 - BOARD_WIDTH / 2;
        double y0 = HEADER_HEIGHT + OUTER_MARGIN;
        return new Rectangle2D.Double(x0, y0, BOARD_WIDTH, BOARD_WIDTH);
    }

    private void drawHeader(Graphics2D g2, String text) {
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font(FONT, Font.BOLD, 70));
        Inf101Graphics.drawCenteredString(g2, text, 0, 0, getWidth(),
                HEADER_HEIGHT + OUTER_MARGIN);
    }

    private void drawText(Graphics2D g2, String text) {
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font(FONT, Font.BOLD, 20));
        Inf101Graphics.drawCenteredString(g2, text, 0, 0, getWidth(), 350);
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d ", minutes, remainingSeconds);
    }

    private String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM HH:mm");
        return date.format(formatter);
    }

    private void drawTimer(Graphics2D g2) {
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font(FONT, Font.BOLD, 20));
        // stack overflow
        // https://stackoverflow.com/questions/19582502/how-to-get-the-correct-string-width-from-fontmetrics-in-java
        // 17. mars 2024
        g2.setFont(new Font(FONT, Font.PLAIN, Math.min(getWidth() / 30, getHeight() / 30)));
        FontMetrics metrics = g2
                .getFontMetrics(new Font(FONT, Font.PLAIN, Math.min(getWidth() / 30, getHeight() / 30)));
        int stringLength = metrics.stringWidth(formatTime(secondsPassed));
        g2.drawString(formatTime(secondsPassed), OUTER_MARGIN + BOARD_WIDTH - stringLength,
                OUTER_MARGIN + HEADER_HEIGHT - 10);
    }

    private void drawLifes(Graphics2D g2, int lifes) {
        g2.setFont(new Font(FONT, Font.PLAIN, Math.min(getWidth() / 30, getHeight() / 30)));
        g2.drawString(lifeToLifeString(lifes), getWidth() / 2 - BOARD_WIDTH / 2,
                OUTER_MARGIN + HEADER_HEIGHT - 10);
    }

    String difficultyNumberToString(int number) {
        switch (number) {
            case 20:
                return "EASY";
            case 40:
                return "MEDIUM";
            case 55:
                return "HARD";
            default:
                return "UNKNOWN DIFFICULTY";
        }
    }

    String lifeToLifeString(int lifes) {
        switch (lifes) {
            case 2:
                return " 💚 💚 💔 ";
            case 1:
                return " 💚 💔 💔 ";
            case 0:
                return " 💔 💔 💔 ";
            default:
                return " 💚 💚 💚 ";
        }
    }

    private void drawScores(Graphics2D g2, List<Score> scores, boolean isUserScores) {

        scores = scoreQueries.sortScores(scores);
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font(FONT, Font.BOLD, 15));

        Rectangle2D tableBounds = getBoardCanvas();

        int x = (int) tableBounds.getX() + INNER_MARGIN;
        int y = (int) tableBounds.getY() + INNER_MARGIN;
        int cellWidth = (int) tableBounds.getWidth() / (isUserScores ? 4 : 5); // Adjust cell width for the new
                                                                               // column
        int cellHeight = 30;

        g2.setFont(new Font(FONT, Font.BOLD, 15));
        if (isUserScores) {
            g2.drawString("Nivå", x, y);
            g2.drawString("Tid", x + cellWidth, y);
            g2.drawString("Liv", x + 2 * cellWidth, y);
            g2.drawString("Dato", x + 3 * cellWidth, y); // Add column for date
        } else {
            g2.drawString("Brukar", x, y);
            g2.drawString("Nivå", x + cellWidth, y);
            g2.drawString("Tid", x + 2 * cellWidth, y);
            g2.drawString("Liv", x + 3 * cellWidth, y);
            g2.drawString("Dato", x + 4 * cellWidth, y); // Add column for date
        }

        g2.drawLine(x, y + INNER_MARGIN * 2, x + (int) tableBounds.getWidth(), y + INNER_MARGIN * 2);

        g2.setFont(new Font(FONT, Font.PLAIN, 15));

        y += cellHeight + INNER_MARGIN;

        if (scores.isEmpty()) {
            g2.drawString("Ingen data ... ", x, y);
            ImageIcon gifIcon = new ImageIcon("src/main/resources/gif/awkward.gif");
            g2.drawImage(gifIcon.getImage(), x, y + 20, this);
        } else {
            for (Score score : scores) {
                if (isUserScores) {
                    g2.drawString(difficultyNumberToString(score.difficulty()), x, y);
                    g2.drawString(formatTime(score.timeUsed()), x + cellWidth, y);
                    g2.drawString(lifeToLifeString(score.lifesUsed()), x + 2 * cellWidth, y);
                    g2.drawString(formatDate(score.finishedAt()), x + 3 * cellWidth, y); // Draw date in the new
                                                                                         // column
                } else {
                    g2.drawString(score.username(), x, y);
                    g2.drawString(difficultyNumberToString(score.difficulty()), x + cellWidth, y);
                    g2.drawString(formatTime(score.timeUsed()), x + 2 * cellWidth, y);
                    g2.drawString(lifeToLifeString(score.lifesUsed()), x + 3 * cellWidth, y);
                    g2.drawString(formatDate(score.finishedAt()), x + 4 * cellWidth, y); // Draw date in the new column
                }
                y += cellHeight + INNER_MARGIN;
            }
        }
    }

    // BUTTON ACTIONS
    public void goToMenu() {
        // adding title for buttons
        JLabel difficultyText = new JLabel(" Nytt spel: ");
        difficultyText.setBounds(100, 310, 300, 30);
        difficultyText.setFont(new Font(FONT, Font.BOLD, 15));
        difficultyText.setForeground(colorTheme.getTextColor());

        JLabel resultText = new JLabel(" Resultatlister:");
        resultText.setBounds(100, 410, 300, 30);
        resultText.setFont(new Font(FONT, Font.BOLD, 15));
        resultText.setForeground(colorTheme.getTextColor());

        menuPanel.setBounds(0, 0, BOARD_WIDTH + OUTER_MARGIN * 2,
                BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);
        menuPanel.setBackground(colorTheme.getBackgroundColor());

        // butting the buttons where i want
        backButton.setText("Tilbake");
        backButton.setForeground(getForeground());
        menuScreen.add(difficultyText);
        menuScreen.add(resultText);
        easyButton.setBounds(100, 350, 100, 30);
        mediumButton.setBounds(230, 350, 100, 30);
        hardButton.setBounds(360, 350, 100, 30);
        resumeButton.setBounds(10, 10, 75, 30);
        myScoresButton.setBounds(100, 450, 120, 30);
        leaderboardButton.setBounds(250, 450, 120, 30);
        toggleLightModeButton.setBounds(510, 10, 40, 30);
        exitButton.setBounds(450, 670, 100, 30);
        logoutButton.setBounds(10, 670, 100, 30);
        exitButton.setForeground(Color.RED);
        menuScreen.add(easyButton);
        menuScreen.add(mediumButton);
        menuScreen.add(hardButton);
        menuScreen.add(myScoresButton);
        menuScreen.add(leaderboardButton);
        menuScreen.add(toggleLightModeButton);
        menuScreen.add(exitButton);
        menuScreen.add(logoutButton);

        menuScreen.add(menuPanel);
        model.setGameState(GameState.MENU);
        changeCard("2");
    }

    public void start(Difficulty difficulty) {
        menuScreen.remove(menuPanel);
        // initialize pause button
        pauseButton.setBounds(10, 10, 70, 30);
        playingScreen.add(pauseButton);
        // initialize graphics panel
        playingPanel.setBounds(0, 0, BOARD_WIDTH + OUTER_MARGIN * 2,
                BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);
        playingScreen.add(playingPanel);

        model.startGame(difficulty);
        model.setGameState(GameState.PLAYING);
        secondsPassed = 0;
        changeCard("3");
        System.out.println("Starting new game with difficulty " + difficulty + " ...");
        System.out.println("Game state: " + model.getGameState());
    }

    public void pause() {
        menuScreen.add(resumeButton);
        menuScreen.add(menuPanel);
        model.setGameState(GameState.MENU);
        changeCard("2");
        System.out.println("Pausing game ...");
        System.out.println("Game state: " + model.getGameState());
    }

    public void resume() {
        menuScreen.remove(resumeButton);
        model.setGameState(GameState.PLAYING);
        changeCard("3");
        System.out.println("Resuming game ...");
        System.out.println("Game state: " + model.getGameState());
    }

    public void myScores() {
        backButton.setBounds(10, 10, 100, 30);
        myScoresScreen.add(backButton);
        myScoresPanel.setBounds(0, 0, BOARD_WIDTH + OUTER_MARGIN * 2,
                BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);
        myScoresScreen.add(myScoresPanel);
        myScoresPanel.setBackground(colorTheme.getBackgroundColor());
        model.setGameState(GameState.MY_SCORES);
        changeCard("5");
        System.out.println("Game state: " + model.getGameState());
    }

    public void leaderBoard() {
        backButton.setBounds(10, 10, 100, 30);
        leaderboardScreen.add(backButton);
        leaderboardPanel.setBounds(0, 0, BOARD_WIDTH + OUTER_MARGIN * 2,
                BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);
        leaderboardScreen.add(leaderboardPanel);
        leaderboardPanel.setBackground(colorTheme.getBackgroundColor());
        model.setGameState(GameState.LEADERBOARD);
        changeCard("6");
        System.out.println("Game state: " + model.getGameState());
    }

    public void finished() {
        cardLayout.show(cardPanel, "4");
        if (model.getLifes() == 0) {
            title.setText("Du tapte!");
            ImageIcon losingIcon = new ImageIcon("src/main/resources/gif/looser.gif");
            gif.setIcon(losingIcon);
        } else {
            title.setText("Gratulera!");
            ImageIcon winningIcon = new ImageIcon("src/main/resources/gif/winning.gif");
            gif.setIcon(winningIcon);
        }
        title.setForeground(colorTheme.getTextColor());
        title.setFont(new Font(FONT, Font.BOLD, 30));
        title.setBounds(30, 100, 200, 50);
        gif.setBounds(30, 150, 500, 400);
        saveText.setForeground(colorTheme.getTextColor());
        saveText.setFont(new Font(FONT, Font.BOLD, 20));
        saveText.setBounds(30, 550, 400, 30);
        saveButton.setBounds(getWidth() / 2 - 115, 600, 100, 30);
        saveButton.setForeground(new Color(14, 161, 86));
        backButton.setBounds(getWidth() / 2 + 15, 600, 100, 30);
        backButton.setForeground(Color.RED);
        backButton.setText("Ikkje lagra");
        gameoverScreen.add(title);
        gameoverScreen.add(saveText);
        gameoverScreen.add(gif);
        gameoverScreen.add(saveButton);
        gameoverScreen.add(backButton);
        model.setGameState(GameState.FINISHED);
        System.out.println("Game state: " + model.getGameState());

    }

    public void save() {
        model.saveGame();
        System.out.println("Saving score ...");
        System.out.println("Game state: " + model.getGameState());
        goToMenu();
    }

    public void logout() {
        System.out.println("Logging out ...");
        dispose();
        new LoginPage();
    }

    public void updateQueries() {
        scoreQueries = new ScoreUtils();
        allScores = scoreQueries.getAllScores();
        userScores = scoreQueries.getScoreFromUser(name);
    }

    public void toggleLightMode() {
        colorTheme = isDefaultColorTheme ? new LightColorTheme() : new DefaultColorTheme();
        for (JPanel panel : allPanels) {
            panel.setBackground(colorTheme.getBackgroundColor());
            for (Component component : panel.getComponents()) {
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    label.setForeground(colorTheme.getTextColor());
                }
            }
        }
        for (GamePanel gamePanel : allGamePanels) {
            gamePanel.setBackground(colorTheme.getBackgroundColor());
        }
        toggleLightModeButton.setText(isDefaultColorTheme ? "🌙" : "🌞");
        isDefaultColorTheme = !isDefaultColorTheme;
    }

    // GETTERS FOR BUTTONS

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getEasyButton() {
        return easyButton;
    }

    public JButton getMediumButton() {
        return mediumButton;
    }

    public JButton getHardButton() {
        return hardButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getMyScoresButton() {
        return myScoresButton;
    }

    public JButton getLeaderboardButton() {
        return leaderboardButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getToggleLightModeButton() {
        return toggleLightModeButton;
    }

    public JButton getResumeButton() {
        return resumeButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public SudokuModel getModel() {
        return model;
    }
}
