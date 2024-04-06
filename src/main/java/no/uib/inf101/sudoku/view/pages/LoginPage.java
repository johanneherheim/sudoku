package no.uib.inf101.sudoku.view.pages;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.codec.digest.DigestUtils;

import no.uib.inf101.sudoku.view.tools.LoginData;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginPage implements ActionListener, KeyListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Logg inn");
    JButton signupButton = new JButton("Ny brukar");
    JTextField usernameInput = new JTextField();
    JPasswordField passwordInput = new JPasswordField();
    JLabel usernameLabel = new JLabel("Brukarnavn:");
    JLabel passwordLabel = new JLabel("Passord:");
    JLabel messageLabel = new JLabel();

    LoginData loginInfo = new LoginData();

    public LoginPage(LoginData loginInfo) {
        this.loginInfo = loginInfo;
        usernameInput.addKeyListener(this);
        passwordInput.addKeyListener(this);
        loginButton.addKeyListener(this);

        usernameLabel.setBounds(50, 100, 75, 25);
        passwordLabel.setBounds(50, 150, 75, 25);

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setForeground(Color.RED);

        usernameInput.setBounds(125, 100, 200, 25);
        passwordInput.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);

        signupButton.setBounds(225, 200, 100, 25);
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
        frame.setSize(600, 800);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {

            String username = usernameInput.getText();
            String password = String.valueOf(passwordInput.getPassword());

            if (loginInfo.getData().containsKey(username)) {
                if (loginInfo.getData().get(username).equals(DigestUtils.sha256Hex(password))) {
                    frame.dispose();
                    @SuppressWarnings("unused")
                    WelcomePage welcomePage = new WelcomePage(username);
                } else {
                    messageLabel.setText("Feil passord");
                    usernameInput.setText("");
                    passwordInput.setText("");
                }
            } else {
                messageLabel.setText("Feil brukarnavn");
                usernameInput.setText("");
                passwordInput.setText("");
            }
        }
        if (e.getSource() == signupButton) {
            if (usernameInput.getText().equals("") || String.valueOf(passwordInput.getPassword()).equals("")) {
                messageLabel.setText("Fyll ut alle felt, takk");
            } else if (loginInfo.getData().containsKey(usernameInput.getText())) {
                messageLabel.setText("Brukarnamnet er allereie i bruk");
            } else {
                loginInfo.addNewUser(usernameInput.getText(), String.valueOf(passwordInput.getPassword()));
                frame.dispose();
                @SuppressWarnings("unused")
                WelcomePage welcomePage = new WelcomePage(usernameInput.getText());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            loginButton.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
