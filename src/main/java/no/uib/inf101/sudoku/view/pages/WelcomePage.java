package no.uib.inf101.sudoku.view.pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WelcomePage implements ActionListener {
    JFrame frame = new JFrame();
    JButton logoutButton = new JButton("Logg ut");

    WelcomePage(String username) {

        logoutButton.setBounds(495, 10, 100, 25);
        logoutButton.addActionListener(this);

        frame.add(logoutButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            frame.dispose();

            @SuppressWarnings("unused")
            LoginPage loginPage = new LoginPage();
        }
    }
}
