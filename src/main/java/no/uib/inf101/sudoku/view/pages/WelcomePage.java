package no.uib.inf101.sudoku.view.pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WelcomePage implements ActionListener {
    JFrame frame = new JFrame();
    JButton logoutButton = new JButton("Logg ut");
    JButton playButton = new JButton("Start");
    String username;

    WelcomePage(String username) {
        this.username = username;

        logoutButton.setBounds(495, 10, 100, 25);
        logoutButton.addActionListener(this);

        playButton.setBounds(250, 200, 100, 25);
        playButton.addActionListener(this);

        frame.add(logoutButton);
        frame.add(playButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            @SuppressWarnings("unused")
            LoginPage loginPage = new LoginPage();
            frame.dispose();
        }
        if (e.getSource() == playButton) {
            @SuppressWarnings("unused")
            GamePage gamePage = new GamePage(username);
            frame.dispose();
        }
    }
}
