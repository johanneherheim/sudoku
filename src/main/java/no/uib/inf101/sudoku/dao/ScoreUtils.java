package no.uib.inf101.sudoku.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.sudoku.model.Score;
//username, score, finishedAt, timeUsed, lifesUsed, hintsUsed, startBoard, solvedBoard, difficulty

public class ScoreUtils {
    public List<Score> getAllScores() {
        String csvFilePath = "src/main/resources/db/score.csv";
        String line;
        String csvSplitBy = ",";
        List<Score> scoreData = new ArrayList<Score>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                // i use trim to remove whitespace
                String username = data[0].trim();
                Integer score = Integer.parseInt(data[1].trim());
                LocalDateTime finishedAt = LocalDateTime.parse(data[2].trim());
                long timeUsed = Long.parseLong(data[3].trim());
                Integer lifesUsed = Integer.parseInt(data[4].trim());
                Integer hintsUsed = Integer.parseInt(data[5].trim());
                String startBoard = data[6].trim();
                String solvedBoard = data[7].trim();
                Integer difficulty = Integer.parseInt(data[8].trim());
                scoreData.add(new Score(username, score, finishedAt, timeUsed, lifesUsed,
                        hintsUsed, startBoard, solvedBoard, difficulty));
            }
            return scoreData;
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage());
        }
    }

    public List<Integer> getScoreFromUser(String username) {
        String csvFilePath = "src/main/resources/db/score.csv";
        String line;
        String csvSplitBy = ",";
        List<Integer> scoreData = new ArrayList<Integer>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                // i use trim to remove whitespace
                String user = data[0].trim();
                String score = data[1].trim();
                if (user.equals(username)) {
                    scoreData.add(Integer.parseInt(score));
                }
            }
            return scoreData;
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage());
        }
    }

    public void insertScore(String username, Integer score, long elapsedTime, Integer lifesUsed, Integer hintsUsed,
            int[][] startBoard, int[][] solvedBoard, Integer difficulty) {
        // stackoverflow
        // https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha-256-in-java
        // 06.04.2024
        String startBoardString = "";
        String solvedBoardString = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                startBoardString += startBoard[i][j];
                solvedBoardString += solvedBoard[i][j];
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/db/score.csv", true))) {
            bw.write(username + "," + score + "," + LocalDateTime.now() + "," + elapsedTime + "," + lifesUsed + ","
                    + hintsUsed + "," + startBoardString + "," + solvedBoardString + "," + difficulty + "\n");
        } catch (Exception e) {
            throw new RuntimeException("Error adding new user: " + e.getMessage());
        }
    }
}
