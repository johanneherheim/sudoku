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

public class LoginPage {

    /** Login window */
    private final JFrame loginFrame = new JFrame();

    /** Buttons */
    private final JButton loginButton = new JButton("Logg inn");
    private final JButton signupButton = new JButton("Ny brukar");

    /** Text fields */
    private final JTextField usernameInput = new JTextField();
    private final JPasswordField passwordInput = new JPasswordField();

    /** Labels */
    private final JLabel usernameLabel = new JLabel("Brukarnavn:");
    private final JLabel passwordLabel = new JLabel("Passord:");
    private final JLabel messageLabel = new JLabel();

    /** User utils */
    private final UserUtils userQueries = new UserUtils();
    private List<User> users = new ArrayList<User>();

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

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getSignupButton() {
        return signupButton;
    }

    public String getUsername() {
        return usernameInput.getText();
    }

    public String getPassword() {
        return new String(passwordInput.getPassword());
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public void clearFields(boolean onlyPassword) {
        if (onlyPassword) {
            passwordInput.setText("");
        } else {
            usernameInput.setText("");
            passwordInput.setText("");
        }
    }

    public List<User> getAllUsers() {
        return users;
    }

    public UserUtils getUserQueries() {
        return userQueries;
    }

    public void launchGame(String username) {
        GameView gameView = new GameView(username);
        System.out.println("Logging inn as " + username + " ...");
        gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameView.setVisible(true);
        loginFrame.dispose();
    }
}
