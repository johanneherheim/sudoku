package no.uib.inf101.sudoku.view.pages;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.sudoku.controller.SudokuController;
import no.uib.inf101.sudoku.model.Difficulty;
import no.uib.inf101.sudoku.model.GameState;
import no.uib.inf101.sudoku.model.SudokuModel;
import no.uib.inf101.sudoku.view.colorthemes.ColorTheme;
import no.uib.inf101.sudoku.view.colorthemes.DefaultColorTheme;
import no.uib.inf101.sudoku.view.tools.CellPositionToPixelConverter;
import no.uib.inf101.sudoku.view.tools.Inf101Graphics;

public class GameView extends JFrame implements ActionListener {

    private static final String FONT = "Helvetica Neue";
    private static final int INNER_MARGIN = 3;
    private static final int OUTER_MARGIN = 30;
    private static final int HEADER_HEIGHT = 150;
    private static final int BOARD_WIDTH = 500;

    protected static final Dimension GAME_SIZE = new Dimension(BOARD_WIDTH + OUTER_MARGIN * 2,
            BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);

    private CardLayout cardLayout;
    private JPanel cardPanel;

    private SudokuModel model;
    private ColorTheme colorTheme;

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

    public GameView(String username) {
        this.model = new SudokuModel(username);
        this.controller = new SudokuController(model, this);
        setTitle("Sudoku");
        setSize(GAME_SIZE);
        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        colorTheme = new DefaultColorTheme();

        JPanel welcomeScreen = new JPanel(null);
        JPanel playingScreen = new JPanel(null);
        JPanel pauseScreen = new JPanel(null);
        JPanel gameoverScreen = new JPanel(null);
        JPanel highScoresScreen = new JPanel(null);
        JPanel leaderboardScreen = new JPanel(null);

        welcomeScreen.setBackground(colorTheme.getBackgroundColor());
        playingScreen.setBackground(colorTheme.getBackgroundColor());
        pauseScreen.setBackground(colorTheme.getBackgroundColor());
        gameoverScreen.setBackground(colorTheme.getBackgroundColor());
        highScoresScreen.setBackground(colorTheme.getBackgroundColor());
        leaderboardScreen.setBackground(colorTheme.getBackgroundColor());

        JLabel welcomeText = new JLabel("Velg vanskelighetsgrad!");
        JLabel pauseText = new JLabel("pause");
        JLabel gameoverText = new JLabel("gameover");
        JLabel highScoresText = new JLabel("Dine resultater");
        JLabel leaderboardText = new JLabel("Leaderboard");
        welcomeText.setForeground(colorTheme.getTextColor());
        pauseText.setForeground(colorTheme.getTextColor());
        gameoverText.setForeground(colorTheme.getTextColor());
        highScoresText.setForeground(colorTheme.getTextColor());
        leaderboardText.setForeground(colorTheme.getTextColor());
        welcomeText.setFont(new Font(FONT, Font.BOLD, 20));
        pauseText.setFont(new Font(FONT, Font.BOLD, 20));
        gameoverText.setFont(new Font(FONT, Font.BOLD, 20));
        highScoresText.setFont(new Font(FONT, Font.BOLD, 20));
        leaderboardText.setFont(new Font(FONT, Font.BOLD, 20));

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

        welcomeText.setBounds(50, 50, 300, 30);
        easyButton.setBounds(50, 100, 100, 30);
        mediumButton.setBounds(50, 150, 100, 30);
        hardButton.setBounds(50, 200, 100, 30);
        welcomeScreen.add(welcomeText);
        welcomeScreen.add(easyButton);
        welcomeScreen.add(mediumButton);
        welcomeScreen.add(hardButton);

        pauseButton.setBounds(10, 10, 70, 30);
        playingScreen.add(pauseButton);

        restartButton.setBounds(125, 200, 150, 25);
        exitButton.setBounds(125, 250, 150, 25);
        myScoresButton.setBounds(125, 300, 150, 25);
        highScoresButton.setBounds(125, 350, 150, 25);
        pauseText.setBounds(50, 50, 300, 30);
        pauseScreen.add(pauseText);
        pauseScreen.add(restartButton);
        pauseScreen.add(exitButton);
        pauseScreen.add(myScoresButton);
        pauseScreen.add(highScoresButton);
        pauseScreen.add(resumeButton);

        gameoverText.setBounds(50, 50, 300, 30);
        backFromGameoverButton.setBounds(50, 100, 100, 30);
        gameoverScreen.add(gameoverText);
        gameoverScreen.add(backFromGameoverButton);

        backFromScoresButton.setBounds(5, 5, 100, 25);
        highScoresText.setBounds(50, 50, 300, 30);
        highScoresScreen.add(highScoresText);
        highScoresScreen.add(backFromScoresButton);

        backFromLeaderboardButton.setBounds(5, 5, 100, 25);
        leaderboardText.setBounds(50, 50, 300, 30);
        leaderboardScreen.add(leaderboardText);
        leaderboardScreen.add(backFromLeaderboardButton);

        cardPanel.add(welcomeScreen, "1");
        cardPanel.add(playingScreen, "2");
        cardPanel.add(pauseScreen, "3");
        cardPanel.add(gameoverScreen, "4");
        cardPanel.add(highScoresScreen, "5");
        cardPanel.add(leaderboardScreen, "6");
        playingScreen.setFocusable(true);

        GamePanel gamePanel = new GamePanel();
        gamePanel.setBounds(0, 0, BOARD_WIDTH + OUTER_MARGIN * 2,
                BOARD_WIDTH + OUTER_MARGIN * 2 + HEADER_HEIGHT + OUTER_MARGIN);
        playingScreen.add(gamePanel);
        gamePanel.setBackground(colorTheme.getBackgroundColor());
        getContentPane().add(cardPanel, BorderLayout.CENTER);

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
        }
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
                getBoardCanvas(), model.getDimension(), INNER_MARGIN);
        header(g2);
        for (GridCell cell : model.getAllTiles()) {
            drawCell(g2, cell, converter);
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
        double x0 = getWidth() / 2 - BOARD_WIDTH / 2;
        double y0 = HEADER_HEIGHT + OUTER_MARGIN;
        return new Rectangle2D.Double(x0, y0, BOARD_WIDTH, BOARD_WIDTH);
    }

    private void header(Graphics2D g2) {
        g2.setColor(colorTheme.getTextColor());
        g2.setFont(new Font(FONT, Font.BOLD, Math.min(getWidth() / 10, getHeight() / 10)));
        Inf101Graphics.drawCenteredString(g2, "SUDOKU", 0, 0, getWidth(),
                HEADER_HEIGHT + OUTER_MARGIN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easyButton) {
            model.setDifficulty(Difficulty.EASY);
            cardLayout.show(cardPanel, "2");
            System.out.println("Easy");
        } else if (e.getSource() == mediumButton) {
            model.setDifficulty(Difficulty.MEDIUM);
            cardLayout.show(cardPanel, "2");
            System.out.println("Medium");
        } else if (e.getSource() == hardButton) {
            model.setDifficulty(Difficulty.HARD);
            cardLayout.show(cardPanel, "2");
            System.out.println("Hard");
        } else if (e.getSource() == pauseButton) {
            cardLayout.show(cardPanel, "3");
            System.out.println("Pause");
        } else if (e.getSource() == resumeButton) {
            cardLayout.show(cardPanel, "2");
            System.out.println("Resume");
        } else if (e.getSource() == restartButton) {
            cardLayout.show(cardPanel, "1");
            System.out.println("Restart");
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == myScoresButton) {
            cardLayout.show(cardPanel, "5");
            System.out.println("My Scores");
        } else if (e.getSource() == highScoresButton) {
            cardLayout.show(cardPanel, "6");
            System.out.println("Leaderboard");
        } else if (e.getSource() == backFromScoresButton) {
            cardLayout.show(cardPanel, "3");
            System.out.println("Back from Scores");
        } else if (e.getSource() == backFromLeaderboardButton) {
            cardLayout.show(cardPanel, "3");
            System.out.println("Back from Leaderboard");
        } else if (e.getSource() == backFromGameoverButton) {
            cardLayout.show(cardPanel, "1");
            System.out.println("Back from Gameover");
            model.restart();
        }
    }
}
