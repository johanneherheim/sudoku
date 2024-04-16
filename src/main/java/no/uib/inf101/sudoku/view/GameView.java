package no.uib.inf101.sudoku.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
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

public class GameView extends JFrame implements ActionListener {

    private static final String FONT = "Helvetica Neue";
    public static final int INNER_MARGIN = 3;
    private static final int OUTER_MARGIN = 30;
    private static final int HEADER_HEIGHT = 150;
    private static final int BOARD_WIDTH = 500;

    protected static final Dimension GAME_SIZE = new Dimension(BOARD_WIDTH + OUTER_MARGIN * 2,
            BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);

    private CardLayout cardLayout;
    private JPanel cardPanel;

    private SudokuModel model;
    private ColorTheme colorTheme;
    private boolean isDefaultColorTheme;
    private String username;

    private Timer timer;
    private int secondsPassed;

    private ScoreUtils scoreQueries = new ScoreUtils();
    private List<Score> allScores;
    private List<Score> userScores;

    @SuppressWarnings("unused")
    private SudokuController controller;

    JButton easyButton = new JButton("Lett");
    JButton mediumButton = new JButton("Middels");
    JButton hardButton = new JButton("Vanskelig");
    JButton pauseButton = new JButton("Pause");
    JButton resumeButton = new JButton("Fortsett");
    JButton restartButton = new JButton("Nytt spel");
    JButton exitButton = new JButton("Avslutt");
    JButton myScoresButton = new JButton("Mine resultat");
    JButton highScoresButton = new JButton("Topplista");
    JButton backFromScoresButton = new JButton("Tilbake");
    JButton backFromLeaderboardButton = new JButton("Tilbake");
    JButton backFromGameoverButton = new JButton("Nytt spill");
    JButton toggleLightModeButton = new JButton("🌞");

    JPanel welcomeScreen = new JPanel(null);
    JPanel playingScreen = new JPanel(null);
    JPanel pauseScreen = new JPanel(null);
    JPanel gameoverScreen = new JPanel(null);
    JPanel highScoresScreen = new JPanel(null);
    JPanel leaderboardScreen = new JPanel(null);

    GamePanel gamePanel = new GamePanel();
    GamePanel higscorePanel = new GamePanel();
    GamePanel leaderboardPanel = new GamePanel();

    public GameView(String username) {
        this.username = username;
        model = new SudokuModel(username);
        controller = new SudokuController(model, this);
        allScores = scoreQueries.getAllScores();
        userScores = scoreQueries.getScoreFromUser(username);
        setTitle("Sudoku");
        setSize(GAME_SIZE);
        setResizable(false);
        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        colorTheme = new DefaultColorTheme();
        isDefaultColorTheme = true;

        welcomeScreen.setBackground(colorTheme.getBackgroundColor());
        playingScreen.setBackground(colorTheme.getBackgroundColor());
        pauseScreen.setBackground(colorTheme.getBackgroundColor());
        gameoverScreen.setBackground(colorTheme.getBackgroundColor());
        highScoresScreen.setBackground(colorTheme.getBackgroundColor());
        leaderboardScreen.setBackground(colorTheme.getBackgroundColor());

        JLabel welcomeText = new JLabel("VANSKELEGHEITSGRAD:");
        JLabel pauseText = new JLabel("PAUSE");
        JLabel gameoverText = new JLabel("BRA JOBBA!");
        welcomeText.setForeground(colorTheme.getTextColor());
        pauseText.setForeground(colorTheme.getTextColor());
        gameoverText.setForeground(colorTheme.getTextColor());
        welcomeText.setFont(new Font(FONT, Font.BOLD, 30));
        pauseText.setFont(new Font(FONT, Font.BOLD, 50));
        gameoverText.setFont(new Font(FONT, Font.BOLD, 30));

        easyButton.addActionListener(this);
        mediumButton.addActionListener(this);
        hardButton.addActionListener(this);
        pauseButton.addActionListener(this);
        resumeButton.addActionListener(this);
        restartButton.addActionListener(this);
        exitButton.addActionListener(this);
        myScoresButton.addActionListener(this);
        highScoresButton.addActionListener(this);
        backFromScoresButton.addActionListener(this);
        backFromLeaderboardButton.addActionListener(this);
        backFromGameoverButton.addActionListener(this);
        toggleLightModeButton.addActionListener(this);

        welcomeText.setBounds(getWidth() / 2 - 180, 50, 400, 50);
        easyButton.setBounds(getWidth() / 2 - 75, 200, 150, 50);
        mediumButton.setBounds(getWidth() / 2 - 75, 275, 150, 50);
        hardButton.setBounds(getWidth() / 2 - 75, 350, 150, 50);
        welcomeScreen.add(welcomeText);
        welcomeScreen.add(easyButton);
        welcomeScreen.add(mediumButton);
        welcomeScreen.add(hardButton);

        pauseButton.setBounds(10, 10, 70, 30);
        playingScreen.add(pauseButton);

        toggleLightModeButton.setBounds(getWidth() - 50, 10, 40, 30);
        pauseText.setBounds(getWidth() / 2 - 80, 50, 200, 50);
        restartButton.setBounds(getWidth() / 2 - 75, 200, 150, 50);
        highScoresButton.setBounds(getWidth() / 2 - 75, 275, 150, 50);
        myScoresButton.setBounds(getWidth() / 2 - 75, 350, 150, 50);
        exitButton.setForeground(Color.RED);
        exitButton.setBounds(getWidth() / 2 - 75, 425, 150, 50);
        resumeButton.setBounds(10, 10, 90, 30);

        pauseScreen.add(pauseText);
        pauseScreen.add(restartButton);
        pauseScreen.add(exitButton);
        pauseScreen.add(myScoresButton);
        pauseScreen.add(highScoresButton);
        pauseScreen.add(resumeButton);
        pauseScreen.add(toggleLightModeButton);

        gameoverText.setBounds(getWidth() / 2 - 85, 50, 200, 50);
        backFromGameoverButton.setBounds(getWidth() / 2 - 75, 275, 150, 50);
        gameoverScreen.add(gameoverText);
        gameoverScreen.add(backFromGameoverButton);

        backFromScoresButton.setBounds(10, 10, 100, 25);
        highScoresScreen.add(backFromScoresButton);

        backFromLeaderboardButton.setBounds(10, 10, 100, 25);
        leaderboardScreen.add(backFromLeaderboardButton);

        cardPanel.add(welcomeScreen, "1");
        cardPanel.add(playingScreen, "2");
        cardPanel.add(pauseScreen, "3");
        cardPanel.add(gameoverScreen, "4");
        cardPanel.add(highScoresScreen, "5");
        cardPanel.add(leaderboardScreen, "6");
        playingScreen.setFocusable(true);

        gamePanel.setBounds(0, 0, BOARD_WIDTH + OUTER_MARGIN * 2,
                BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);
        playingScreen.add(gamePanel);
        gamePanel.setBackground(colorTheme.getBackgroundColor());

        higscorePanel.setBounds(0, 0, BOARD_WIDTH + OUTER_MARGIN * 2,
                BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);
        highScoresScreen.add(higscorePanel);
        higscorePanel.setBackground(colorTheme.getBackgroundColor());

        leaderboardPanel.setBounds(0, 0, BOARD_WIDTH + OUTER_MARGIN * 2,
                BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);
        leaderboardScreen.add(leaderboardPanel);
        leaderboardPanel.setBackground(colorTheme.getBackgroundColor());

        getContentPane().add(cardPanel, BorderLayout.CENTER);

        startTimer();
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
            cardLayout.show(cardPanel, "4");
            formatTime(secondsPassed);
        }

        if (model.getGameState() == GameState.MY_SCORES) {
            cardLayout.show(cardPanel, "5");
            header(g2, "Dine resultater");
            updateQuereies();
            drawScores(g2, userScores);
        }

        if (model.getGameState() == GameState.LEADERBOARD) {
            cardLayout.show(cardPanel, "6");
            updateQuereies();
            header(g2, "Topplista");
            drawScores(g2, allScores);
        }

        if (model.getGameState() == GameState.PLAYING) {
            cardLayout.show(cardPanel, "2");
            CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                    getBoardCanvas(), model.getDimension(), INNER_MARGIN);
            header(g2, "SUDOKU");
            timer(g2);
            drawLifes(g2, lifeToLifeString(model.getLifes()));
            for (GridCell cell : model.getAllTiles()) {
                drawCell(g2, cell, converter);
            }
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

    private void header(Graphics2D g2, String text) {
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font(FONT, Font.BOLD, Math.min(getWidth() / 10, getHeight() / 10)));
        Inf101Graphics.drawCenteredString(g2, text, 0, 0, getWidth(),
                HEADER_HEIGHT + OUTER_MARGIN);
    }

    public String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d ", minutes, remainingSeconds);
    }

    private void timer(Graphics2D g2) {
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

    private String lifeToLifeString(int lifes) {
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

    private void drawLifes(Graphics2D g2, String lifes) {
        g2.setFont(new Font(FONT, Font.PLAIN, Math.min(getWidth() / 30, getHeight() / 30)));
        g2.drawString(lifes, getWidth() / 2 - BOARD_WIDTH / 2,
                OUTER_MARGIN + HEADER_HEIGHT - 10);
    }

    private String difficultyNumberToString(int number) {
        switch (number) {
            case 2:
                return "MEDIUM";
            case 3:
                return "HARD";
            default:
                return "EASY";
        }
    }

    private void drawScores(Graphics2D g2, List<Score> scores) {
        scores = scoreQueries.sortByLifesAndTime(scores);
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font(FONT, Font.BOLD, 15));

        Rectangle2D tableBounds = getBoardCanvas();

        int x = (int) tableBounds.getX() + INNER_MARGIN;
        int y = (int) tableBounds.getY() + INNER_MARGIN;
        int cellWidth = (int) tableBounds.getWidth() / (allNamesAreSame(scores) ? 3 : 4);
        int cellHeight = 30;

        g2.setFont(new Font(FONT, Font.BOLD, 15));
        if (allNamesAreSame(scores)) {
            g2.drawString("Nivå", x, y);
            g2.drawString("Tid", x + cellWidth, y);
            g2.drawString("Liv", x + 2 * cellWidth, y);
        } else {
            g2.drawString("Brukar", x, y);
            g2.drawString("Nivå", x + cellWidth, y);
            g2.drawString("Tid", x + 2 * cellWidth, y);
            g2.drawString("Liv", x + 3 * cellWidth, y);
        }

        g2.drawLine(x, y + INNER_MARGIN * 2, x + (int) tableBounds.getWidth(), y + INNER_MARGIN * 2);

        g2.setFont(new Font(FONT, Font.PLAIN, 15));

        y += cellHeight + INNER_MARGIN;
        for (Score score : scores) {
            if (allNamesAreSame(scores)) {
                g2.drawString(difficultyNumberToString(score.getDifficulty()), x, y);
                g2.drawString(formatTime(score.getSeconds()), x + cellWidth, y);
                g2.drawString(lifeToLifeString(score.getLifesUsed()), x + 2 * cellWidth, y);
            } else {
                g2.drawString(score.getUsername(), x, y);
                g2.drawString(difficultyNumberToString(score.getDifficulty()), x + cellWidth, y);
                g2.drawString(formatTime(score.getSeconds()), x + 2 * cellWidth, y);
                g2.drawString(lifeToLifeString(score.getLifesUsed()), x + 3 * cellWidth, y);
            }
            y += cellHeight + INNER_MARGIN;
        }
    }

    private boolean allNamesAreSame(List<Score> scores) {
        if (scores.isEmpty()) {
            return true;
        }
        String firstUsername = scores.get(0).getUsername();
        for (Score score : scores) {
            if (!score.getUsername().equals(firstUsername)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easyButton) {
            model.start(Difficulty.EASY);
            model.setGameState(GameState.PLAYING);
            cardLayout.show(cardPanel, "2");
            secondsPassed = 0;
            System.out.println("Easy");
        } else if (e.getSource() == mediumButton) {
            model.start(Difficulty.MEDIUM);
            model.setGameState(GameState.PLAYING);
            cardLayout.show(cardPanel, "2");
            secondsPassed = 0;
            System.out.println("Medium");
        } else if (e.getSource() == hardButton) {
            model.start(Difficulty.HARD);
            model.setGameState(GameState.PLAYING);
            cardLayout.show(cardPanel, "2");
            secondsPassed = 0;
            System.out.println("Hard");
        } else if (e.getSource() == pauseButton) {
            model.setGameState(GameState.PAUSED);
            cardLayout.show(cardPanel, "3");
            System.out.println("Pause");
        } else if (e.getSource() == resumeButton) {
            model.setGameState(GameState.PLAYING);
            cardLayout.show(cardPanel, "2");
            System.out.println("Resume");
        } else if (e.getSource() == restartButton) {
            model.setGameState(GameState.PLAYING);
            cardLayout.show(cardPanel, "1");
            System.out.println("Restart");
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == myScoresButton) {
            model.setGameState(GameState.MY_SCORES);
            cardLayout.show(cardPanel, "5");
            System.out.println("My Scores");
        } else if (e.getSource() == highScoresButton) {
            model.setGameState(GameState.LEADERBOARD);
            cardLayout.show(cardPanel, "6");
            System.out.println("Leaderboard");
        } else if (e.getSource() == backFromScoresButton) {
            model.setGameState(GameState.PAUSED);
            cardLayout.show(cardPanel, "3");
            System.out.println("Back from Scores");
        } else if (e.getSource() == backFromLeaderboardButton) {
            model.setGameState(GameState.PAUSED);
            cardLayout.show(cardPanel, "3");
            System.out.println("Back from Leaderboard");
        } else if (e.getSource() == backFromGameoverButton) {
            model.setGameState(GameState.WELCOME);
            cardLayout.show(cardPanel, "1");
            System.out.println("Back from Gameover");
        } else if (e.getSource() == toggleLightModeButton) {
            toggleLightMode();
        }
        repaint();
    }

    public void updateQuereies() {
        scoreQueries = new ScoreUtils();
        allScores = scoreQueries.getAllScores();
        userScores = scoreQueries.getScoreFromUser(username);
    }

    public void toggleLightMode() {
        if (isDefaultColorTheme) {
            colorTheme = new LightColorTheme();
            toggleLightModeButton.setText("🌙");
            welcomeScreen.setBackground(colorTheme.getBackgroundColor());
            playingScreen.setBackground(colorTheme.getBackgroundColor());
            pauseScreen.setBackground(colorTheme.getBackgroundColor());
            gameoverScreen.setBackground(colorTheme.getBackgroundColor());
            highScoresScreen.setBackground(colorTheme.getBackgroundColor());
            leaderboardScreen.setBackground(colorTheme.getBackgroundColor());
            gamePanel.setBackground(colorTheme.getBackgroundColor());
            higscorePanel.setBackground(colorTheme.getBackgroundColor());
            leaderboardPanel.setBackground(colorTheme.getBackgroundColor());
            setBackground(colorTheme.getBackgroundColor());

        } else {
            colorTheme = new DefaultColorTheme();
            toggleLightModeButton.setText("🌞");
            welcomeScreen.setBackground(colorTheme.getBackgroundColor());
            playingScreen.setBackground(colorTheme.getBackgroundColor());
            pauseScreen.setBackground(colorTheme.getBackgroundColor());
            gameoverScreen.setBackground(colorTheme.getBackgroundColor());
            highScoresScreen.setBackground(colorTheme.getBackgroundColor());
            leaderboardScreen.setBackground(colorTheme.getBackgroundColor());
            gamePanel.setBackground(colorTheme.getBackgroundColor());
            higscorePanel.setBackground(colorTheme.getBackgroundColor());
            leaderboardPanel.setBackground(colorTheme.getBackgroundColor());
            setBackground(colorTheme.getBackgroundColor());
        }
        isDefaultColorTheme = !isDefaultColorTheme;
    }
}
