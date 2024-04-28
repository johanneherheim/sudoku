package no.uib.inf101.sudoku.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import no.uib.inf101.sudoku.model.User;

import java.util.List;

import org.junit.jupiter.api.Test;

public class UserUtilsTest {
    @Test
    public void testGetAllUsers() {
        UserUtils userUtils = new UserUtils();
        userUtils.insertUser("testUser", "testPassword");
        // Check if the list is not null and contains at least one user
        List<User> users = userUtils.getAllUsers();
        assertTrue(users != null && !users.isEmpty());
        userUtils.removeUser("testUser");
    }

    @Test
    public void testInsertUser() {
        UserUtils userUtils = new UserUtils();
        userUtils.insertUser("testUser", "testPassword");

        List<User> users = userUtils.getAllUsers();
        boolean userFound = false;
        // Check if the newly inserted user exists in the list
        for (User user : users) {
            if (user.username().equals("testUser") && user.password().equals("testPassword")) {
                userFound = true;
                break;
            }
        }
        assertTrue(userFound);
        userUtils.removeUser("testUser");
    }

    @Test
    public void testGetUser() {
        UserUtils userUtils = new UserUtils();
        // Get an existing user
        userUtils.insertUser("testUser", "testPassword");
        User user = userUtils.getUser("testUser");
        assertEquals("testUser", user.username());

        // Try to get a non-existing user
        assertThrows(RuntimeException.class, () -> userUtils.getUser("nonExistingUser"));
        userUtils.removeUser("testUser");
    }
}
