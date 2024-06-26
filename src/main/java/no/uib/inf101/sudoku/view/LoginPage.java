package no.uib.inf101.sudoku.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import no.uib.inf101.sudoku.controller.LoginController;
import no.uib.inf101.sudoku.dao.UserUtils;
import no.uib.inf101.sudoku.model.User;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The LoginPage class represents the login page of the application.
 * It provides functionality for user authentication and registration.
 */
public class LoginPage implements ILoginPage {

    // login window
    private final JFrame loginFrame = new JFrame();

    // buttons
    private final JButton loginButton = new JButton("Logg inn");
    private final JButton signupButton = new JButton("Ny brukar");

    // input fields
    private final JTextField usernameInput = new JTextField();
    private final JPasswordField passwordInput = new JPasswordField();

    // labels
    private final JLabel usernameLabel = new JLabel("Brukarnavn:");
    private final JLabel passwordLabel = new JLabel("Passord:");
    private final JLabel messageLabel = new JLabel();

    // user data
    private final UserUtils userQueries = new UserUtils();
    private List<User> users = new ArrayList<User>();

    /**
     * Constructor for the LoginPage class. Initializes the login page.
     * It sets up the login window and adds the necessary components.
     * It also sets up the event listeners for the login and signup buttons.
     * The login page is responsible for authenticating users and launching the
     * game. It also provides functionality for user registration.
     */
    public LoginPage() {
        LoginController controller = new LoginController(this);

        loginButton.addActionListener(controller);
        signupButton.addActionListener(controller);
        usernameInput.addKeyListener(controller);
        passwordInput.addKeyListener(controller);

        users = userQueries.getAllUsers();

        usernameLabel.setBounds(50, 50, 75, 25);
        passwordLabel.setBounds(50, 100, 75, 25);

        messageLabel.setBounds(125, 175, 250, 35);
        messageLabel.setForeground(Color.RED);

        usernameInput.setBounds(125, 50, 200, 25);
        passwordInput.setBounds(125, 100, 200, 25);

        loginButton.setBounds(125, 150, 100, 25);
        loginButton.setFocusable(false);

        signupButton.setBounds(225, 150, 100, 25);
        signupButton.setFocusable(false);

        loginFrame.add(usernameLabel);
        loginFrame.add(passwordLabel);
        loginFrame.add(messageLabel);
        loginFrame.add(usernameInput);
        loginFrame.add(passwordInput);
        loginFrame.add(loginButton);
        loginFrame.add(signupButton);

        loginFrame.setTitle("Logg inn");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 250);
        loginFrame.setResizable(false);
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
    }

    @Override
    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    @Override
    public void clearFields(boolean username, boolean password) {
        if (username) {
            usernameInput.setText("");
        }
        if (password) {
            passwordInput.setText("");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public UserUtils getUserQueries() {
        return userQueries;
    }

    @Override
    public String getPasswordToUser(String username) {
        return userQueries.getUser(username).password();
    }

    @Override
    public void launchApplication(String username) {
        GameView gameView = new GameView(username);
        System.out.println("Logging inn as " + username + " ...");
        gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameView.setVisible(true);
        loginFrame.dispose();
    }

    @Override
    public JButton getLoginButton() {
        return loginButton;
    }

    @Override
    public JButton getSignupButton() {
        return signupButton;
    }

    @Override
    public String getUsername() {
        return usernameInput.getText();
    }

    @Override
    public String getPassword() {
        return new String(passwordInput.getPassword());
    }

    @Override
    public JTextField getUsernameInput() {
        return usernameInput;
    }

    @Override
    public JPasswordField getPasswordInput() {
        return passwordInput;
    }
}
