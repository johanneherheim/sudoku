package no.uib.inf101.sudoku.view.pages;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.codec.digest.DigestUtils;

import no.uib.inf101.sudoku.dao.UserUtils;
import no.uib.inf101.sudoku.model.User;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class LoginPage implements ActionListener, KeyListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Logg inn");
    JButton signupButton = new JButton("Ny brukar");
    JTextField usernameInput = new JTextField();
    JPasswordField passwordInput = new JPasswordField();
    JLabel usernameLabel = new JLabel("Brukarnavn:");
    JLabel passwordLabel = new JLabel("Passord:");
    JLabel messageLabel = new JLabel();

    UserUtils userQueries = new UserUtils();
    List<User> users = new ArrayList<User>();

    public LoginPage() {
        users = userQueries.getAllUsers();

        usernameInput.addKeyListener(this);
        passwordInput.addKeyListener(this);
        loginButton.addKeyListener(this);

        usernameLabel.setBounds(50, 50, 75, 25);
        passwordLabel.setBounds(50, 100, 75, 25);

        messageLabel.setBounds(125, 175, 250, 35);
        messageLabel.setForeground(Color.RED);

        usernameInput.setBounds(125, 50, 200, 25);
        passwordInput.setBounds(125, 100, 200, 25);

        loginButton.setBounds(125, 150, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);

        signupButton.setBounds(225, 150, 100, 25);
        signupButton.addActionListener(this);
        signupButton.setFocusable(false);

        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(messageLabel);
        frame.add(usernameInput);
        frame.add(passwordInput);
        frame.add(loginButton);
        frame.add(signupButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {

            String username = usernameInput.getText();
            String password = String.valueOf(passwordInput.getPassword());
            boolean userNotFound = true;

            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    userNotFound = false;
                    if (user.getPassword().equals(hash(password))) {
                        @SuppressWarnings("unused")
                        GamePage gamepage = new GamePage(username);
                        frame.dispose();
                    } else {
                        messageLabel.setText("Feil passord");
                        passwordInput.setText("");
                    }
                    break;
                }
            }
            if (userNotFound) {
                messageLabel.setText("Brukarnamnet finst ikkje");
                usernameInput.setText("");
                passwordInput.setText("");
            }
        }
        if (e.getSource() == signupButton) {

            String newUsername = usernameInput.getText();
            String newPassword = String.valueOf(passwordInput.getPassword());
            boolean usernameExists = false;

            for (User user : users) {
                if (user.getUsername().equals(newUsername)) {
                    usernameExists = true;
                    break;
                }
            }
            if (newUsername.equals("") || newPassword.equals("")) {
                messageLabel.setText("Fyll ut alle felt, takk");
            } else if (usernameExists) {
                messageLabel.setText("Brukarnamnet er allerede i bruk");
            } else {
                userQueries.insertUser(newUsername, hash(newPassword));
                @SuppressWarnings("unused")
                GamePage gamepage = new GamePage(newUsername);
                frame.dispose();
            }
        }
    }

    private String hash(String message) {
        return DigestUtils.sha256Hex(message);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            loginButton.doClick();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
