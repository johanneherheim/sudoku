package no.uib.inf101.sudoku.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import no.uib.inf101.sudoku.dao.UserUtils;
import no.uib.inf101.sudoku.model.User;

public interface ILoginPage {
    /**
     * Returns the login button.
     *
     * @return the login button
     */
    JButton getLoginButton();

    /**
     * Returns the signup button.
     *
     * @return the signup button
     */
    JButton getSignupButton();

    /**
     * Returns the username input field.
     *
     * @return the username input field
     */
    String getUsername();

    /**
     * Returns the password input field.
     *
     * @return the password input field
     */
    String getPassword();

    /**
     * Sets the message label text.
     *
     * @param message the message to be displayed
     */
    void setMessage(String message);

    /**
     * Clears the input fields.
     *
     * @param username whether to clear the username field
     * @param password whether to clear the password field
     */
    void clearFields(boolean username, boolean password);

    /**
     * Returns a list of all users.
     *
     * @return a list of all users
     */
    List<User> getAllUsers();

    /**
     * Returns the user queries object.
     *
     * @return the user queries object
     */
    UserUtils getUserQueries();

    /**
     * Returns the hashed password of the specified user.
     *
     * @param username the username of the user
     * @return the password of the user
     */
    String getPasswordToUser(String username);

    /**
     * Launches the application with the specified username.
     *
     * @param username the username of the player
     */
    void launchApplication(String username);

    /**
     * Returns the username input field.
     *
     * @return the username input field
     */
    JTextField getUsernameInput();

    /**
     * Returns the password input field.
     *
     * @return the password input field
     */
    JPasswordField getPasswordInput();

}
