package no.uib.inf101.sudoku.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.commons.codec.digest.DigestUtils;

import no.uib.inf101.sudoku.model.User;
import no.uib.inf101.sudoku.view.LoginPage;

/**
 * The LoginController class is responsible for handling user interactions and
 * logic related to the login page.
 * It implements the KeyListener and ActionListener interfaces.
 */
public class LoginController implements KeyListener, ActionListener {

    /** The login page that the controller is connected to */
    private final LoginPage loginPage;

    /**
     * Constructor for the LoginController
     * 
     * @param loginPage the login page that the controller is connected to
     */
    public LoginController(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    /**
     * Hashes a plaintext string using the SHA-256 algorithm.
     *
     * @param plaintext the string to be hashed
     * @return the hashed string
     */
    private String hash(String plaintext) {
        return DigestUtils.sha256Hex(plaintext);
    }

    /**
     * Performs the login operation.
     * Retrieves the username and password from the login page,
     * checks if the password matches the hashed password stored for the username,
     * and launches the game if the login is successful.
     * If the login fails, appropriate error messages are displayed and the fields
     * are cleared.
     * If the username does not exist, an error message is displayed and the fields
     * are cleared.
     */
    public void login() {
        String username = loginPage.getUsername();
        String password = loginPage.getPassword();

        if (loginPage.getPasswordToUser(username).equals(hash(password))) {
            loginPage.launchGame(username);
        } else {
            loginPage.setMessage("Feil passord");
            loginPage.clearFields(false, true);
        }

        if (!usernameExists(username)) {
            loginPage.setMessage("Brukarnamnet finst ikkje");
            loginPage.clearFields(true, true);
        }
    }

    /**
     * Performs the sign up operation.
     * Retrieves the username and password from the login page,
     * checks if the input is valid and does not contain special characters,
     * checks if the input is empty,
     * checks if the username already exists,
     * and inserts the new user into the database if the sign up is successful.
     * If the sign up fails, appropriate error messages are displayed.
     */
    public void signUp() {
        String newUsername = loginPage.getUsername();
        String newPassword = loginPage.getPassword();

        if (!isInputValid(newUsername, newPassword)) {
            loginPage.setMessage("Ikkje bruk spesialteikn");
        }

        else if (isInputEmpty(newUsername, newPassword)) {
            loginPage.setMessage("Fyll ut alle felt, takk");
        }

        else if (usernameExists(newUsername)) {
            loginPage.setMessage("Brukarnamnet er allereie i bruk");
        }

        else {
            loginPage.getUserQueries().insertUser(newUsername, hash(newPassword));
            loginPage.launchGame(newUsername);
        }
    }

    /**
     * Checks if the given username is valid.
     *
     * @param username the username to be checked
     * @return true if the username is valid, false otherwise
     */
    private boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9]*$");
    }

    /**
     * Checks if a password is valid.
     *
     * @param password the password to be checked
     * @return true if the password is valid, false otherwise
     */
    private boolean isPasswordValid(String password) {
        return password.matches("^[a-zA-Z0-9]*$");
    }

    /**
     * Checks if the input username and password are valid.
     *
     * @param username the username to be validated
     * @param password the password to be validated
     * @return true if both the username and password are valid, false otherwise
     */
    private boolean isInputValid(String username, String password) {
        return isUsernameValid(username) && isPasswordValid(password);
    }

    /**
     * Checks if the input fields for username and password are empty.
     *
     * @param username the username input
     * @param password the password input
     * @return true if either the username or password is empty, false otherwise
     */
    private boolean isInputEmpty(String username, String password) {
        return username.equals("") || password.equals("");
    }

    /**
     * Checks if a username already exists in the list of users.
     *
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    private boolean usernameExists(String username) {
        for (User user : loginPage.getAllUsers()) {
            if (user.username().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginPage.getLoginButton()) {
            login();
        }
        if (e.getSource() == loginPage.getSignupButton()) {
            signUp();
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
