package no.uib.inf101.sudoku.dao;

import java.util.Comparator;

import no.uib.inf101.sudoku.model.Score;

class ScoreTimeComparator implements Comparator<Score> {
    @Override
    public int compare(Score s1, Score s2) {
        // Compare by time used (elapsed time)
        return Integer.compare(s1.getSeconds(), s2.getSeconds());
    }
}
