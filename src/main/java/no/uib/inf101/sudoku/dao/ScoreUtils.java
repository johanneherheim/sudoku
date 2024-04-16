package no.uib.inf101.sudoku.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
                int timeUsed = Integer.parseInt(data[3].trim());
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

    public List<Score> getScoreFromUser(String username) {
        List<Score> scoreData = new ArrayList<Score>();
        for (Score score : getAllScores()) {
            if (score.getUsername().equals(username)) {
                scoreData.add(score);
            }
        }
        return scoreData;

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

    public List<Score> sortByTime(List<Score> scores) {
        Comparator<Score> timeComparator = new Comparator<Score>() {
            @Override
            public int compare(Score s1, Score s2) {
                // Compare by time used (elapsed time)
                return Integer.compare(s1.getSeconds(), s2.getSeconds());
            }
        };

        Collections.sort(scores, timeComparator);

        return scores;
    }

    public List<Score> sortByLifesAndTime(List<Score> scores) {
        Comparator<Score> lifesAndTimeComparator = new Comparator<Score>() {
            @Override
            public int compare(Score s1, Score s2) {
                // Compare by lifes used in descending order
                int lifesComparison = Integer.compare(s2.getLifesUsed(), s1.getLifesUsed());
                // If the lifes used are the same, compare by time used
                if (lifesComparison == 0) {
                    return Integer.compare(s1.getSeconds(), s2.getSeconds());
                }
                return lifesComparison;
            }
        };
        Collections.sort(scores, lifesAndTimeComparator);
        if (scores.size() > 15) {
            return scores.subList(0, 15);
        }
        return scores;
    }
}
