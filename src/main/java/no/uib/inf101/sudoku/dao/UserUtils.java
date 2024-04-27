package no.uib.inf101.sudoku.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.sudoku.model.User;

public class UserUtils implements IUserUtils {

    @Override
    public List<User> getAllUsers() {
        String csvFilePath = "src/main/resources/db/user.csv";
        String line;
        String csvSplitBy = ",";
        List<User> loginData = new ArrayList<User>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                // i use trim to remove whitespace
                String username = data[0].trim();
                String password = data[1].trim();
                loginData.add(new User(username, password));
            }
            return loginData;
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage());
        }
    }

    @Override
    public void insertUser(String username, String password) {
        // stackoverflow
        // https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha-256-in-java
        // 06.04.2024
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/db/user.csv", true))) {
            bw.write(username + "," + password + "\n");
        } catch (Exception e) {
            throw new RuntimeException("Error adding new user: " + e.getMessage());
        }
    }

    @Override
    public User getUser(String username) {
        for (User user : getAllUsers()) {
            if (user.username().equals(username)) {
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }
}
