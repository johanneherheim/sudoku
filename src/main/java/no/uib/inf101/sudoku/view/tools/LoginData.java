package no.uib.inf101.sudoku.view.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.codec.digest.DigestUtils;

public class LoginData {
    HashMap<String, String> loginData = new HashMap<String, String>();

    public LoginData() {
        readCSV("db/user.csv");
    }

    private void readCSV(String csvFilePath) {
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                // i use trim to remove whitespace
                String username = data[0].trim();
                String password = data[1].trim();
                loginData.put(username, password);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage());
        }
    }

    public void addNewUser(String username, String password) {
        String hashedPassword = DigestUtils.sha256Hex(password);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("db/user.csv", true))) {
            bw.write(username + "," + hashedPassword + "\n");
            loginData.put(username, hashedPassword);
        } catch (Exception e) {
            throw new RuntimeException("Error adding new user: " + e.getMessage());
        }
    }

    public HashMap<String, String> getData() {
        return loginData;
    }
}
