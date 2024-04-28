package no.uib.inf101.sudoku.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sudoku.dao.UserUtils;

public class LoginPageTest {
    private LoginPage loginPage = new LoginPage();
    UserUtils userQueries = new UserUtils();

    @Test
    public void testSignup() {
        loginPage.getUsernameInput().setText("Test");
        loginPage.getPasswordInput().setText("test");
        loginPage.getSignupButton().doClick();
        assertEquals("Test", loginPage.getUsernameInput().getText());
        userQueries.removeUser("Test");
    }

    @Test
    public void testLogin() {
        loginPage.getUsernameInput().setText("Test");
        loginPage.getPasswordInput().setText("test");
        loginPage.getSignupButton().doClick();
        assertEquals("Test", loginPage.getUsernameInput().getText());
    }

    @Test
    public void testClearFields() {
        loginPage.getUsernameInput().setText("Test");
        loginPage.getPasswordInput().setText("test");
        loginPage.clearFields(true, true);
        assertEquals("", loginPage.getUsernameInput().getText());
    }

    @Test
    public void testGetUsername() {
        loginPage.getUsernameInput().setText("Test");
        assertEquals("Test", loginPage.getUsername());
    }

    @Test
    public void testGetPassword() {
        loginPage.getPasswordInput().setText("test");
        assertEquals("test", loginPage.getPassword());
    }

    @Test
    public void testGetUsernameInput() {
        assertEquals(loginPage.getUsernameInput(), loginPage.getUsernameInput());
    }

    @Test
    public void testGetPasswordInput() {
        assertEquals(loginPage.getPasswordInput(), loginPage.getPasswordInput());
    }

    @Test
    public void testGetLoginButton() {
        assertEquals(loginPage.getLoginButton(), loginPage.getLoginButton());
    }

}
