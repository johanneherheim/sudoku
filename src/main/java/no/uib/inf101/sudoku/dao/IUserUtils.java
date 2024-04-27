package no.uib.inf101.sudoku.dao;

import java.util.List;

import no.uib.inf101.sudoku.model.User;

/**
 * This interface provides methods for interacting with user data in the
 * database.
 */
public interface IUserUtils {

    /**
     * Retrieves a list of all users from the database.
     * 
     * @return A list of all users.
     */
    List<User> getAllUsers();

    /**
     * Retrieves a specific user from the database based on the given username.
     * 
     * @param username The username of the user to retrieve.
     * @return The User object representing the retrieved user.
     */
    User getUser(String username);

    /**
     * Inserts a new user into the database with the given username and password.
     * 
     * @param username The username of the new user.
     * @param password The password of the new user.
     */
    void insertUser(String username, String password);

    /**
     * Deletes a user from the database based on the given username.
     * 
     * @param username The username of the user to delete.
     */
    void removeUser(String username);
}
