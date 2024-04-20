package no.uib.inf101.sudoku.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.commons.codec.digest.DigestUtils;

import no.uib.inf101.sudoku.model.User;
import no.uib.inf101.sudoku.view.LoginPage;

public class LoginController implements KeyListener, ActionListener {

    private final LoginPage loginPage;

    public LoginController(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    /**
     * Hashes a message using SHA-256
     * 
     * @param plaintext
     * @return hashed message
     */
    private String hash(String plaintext) {
        return DigestUtils.sha256Hex(plaintext);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginPage.getLoginButton()) {
            String username = loginPage.getUsername();
            String password = loginPage.getPassword();
            boolean userNotFound = true;

            for (User user : loginPage.getAllUsers()) {
                if (user.username().equals(username)) {
                    userNotFound = false;
                    if (user.password().equals(hash(password))) {
                        loginPage.launchGame(username);
                    } else {
                        loginPage.setMessage("Feil passord");
                        loginPage.clearFields(true);
                    }
                    break;
                }
            }
            if (userNotFound) {
                loginPage.setMessage("Brukarnamnet finst ikkje");
                loginPage.clearFields(false);
            }
        }
        if (e.getSource() == loginPage.getSignupButton()) {
            String newUsername = loginPage.getUsername();
            String newPassword = loginPage.getPassword();
            boolean usernameExists = false;

            for (User user : loginPage.getAllUsers()) {
                if (user.username().equals(newUsername)) {
                    usernameExists = true;
                    break;
                }
            }
            if (newUsername.equals("") || newPassword.equals("")) {
                loginPage.setMessage("Fyll ut alle felt, takk");
            } else if (usernameExists) {
                loginPage.setMessage("Brukarnamnet er allerede i bruk");
            } else {
                loginPage.getUserQueries().insertUser(newUsername, hash(newPassword));
                loginPage.launchGame(newUsername);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // can press login button by pressing enter
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            loginPage.getLoginButton().doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
