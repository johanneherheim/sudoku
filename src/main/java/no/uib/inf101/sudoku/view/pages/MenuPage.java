package no.uib.inf101.sudoku.view.pages;

import javax.swing.JButton;
import javax.swing.JFrame;

import no.uib.inf101.sudoku.model.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPage implements ActionListener {

    String username;
    GamePage gameFrame;

    JFrame frame = new JFrame();

    JButton restartButton = new JButton("Nytt spel");
    JButton exitButton = new JButton("Avslutt");
    JButton myScoresButton = new JButton("Mine resultat");
    JButton highScoresButton = new JButton("Topplista");
    JButton backButton = new JButton("Tilbake");

    public MenuPage(String username, GamePage gameFrame) {

        this.gameFrame = gameFrame;
        this.username = username;

        restartButton.setBounds(125, 200, 150, 25);
        restartButton.addActionListener(this);
        restartButton.setFocusable(false);

        exitButton.setBounds(125, 250, 150, 25);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        myScoresButton.setBounds(125, 300, 150, 25);
        myScoresButton.addActionListener(this);
        myScoresButton.setFocusable(false);

        highScoresButton.setBounds(125, 350, 150, 25);
        highScoresButton.addActionListener(this);
        highScoresButton.setFocusable(false);

        backButton.setBounds(5, 5, 100, 25);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        frame.add(restartButton);
        frame.add(exitButton);
        frame.add(myScoresButton);
        frame.add(highScoresButton);
        frame.add(backButton);

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            gameFrame.restart();
            frame.repaint();
            frame.dispose();
        }
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
        if (e.getSource() == myScoresButton) {
            gameFrame.getModel().setGameState(GameState.MY_SCORES);
            frame.repaint();
            frame.dispose();
        }
        if (e.getSource() == highScoresButton) {
            gameFrame.getModel().setGameState(GameState.HIGHSCORES);
            frame.repaint();
            frame.dispose();
        }
        if (e.getSource() == backButton) {
            gameFrame.getModel().setGameState(GameState.PLAYING);
            frame.dispose();
        }
    }

}
