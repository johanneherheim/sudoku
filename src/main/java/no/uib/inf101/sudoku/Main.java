package no.uib.inf101.sudoku;

import java.awt.Toolkit;
import javax.swing.JFrame;

import no.uib.inf101.sudoku.model.Board;
import no.uib.inf101.sudoku.model.Generator;
import no.uib.inf101.sudoku.model.Model;
import no.uib.inf101.sudoku.view.View;

public class Main {
  public static final String WINDOW_TITLE = "Sudoku";

  public static void main(String[] args) {

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Generator sudoku = new Generator();
    sudoku.generateBoard();
    sudoku.printBoard();

    Board board = new Board(sudoku.getBoard());
    Model model = new Model(board);
    View view = new View(model);

    // stack overflow
    // https://stackoverflow.com/questions/21921135/using-setlocation-to-move-the-jframe-around-windows-java
    // 5. mars 2024
    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    frame.setLocation((screenWidth - 1000) / 2, (screenHeight - 800) / 2);

    frame.setContentPane(view);

    frame.pack();
    frame.setVisible(true);
  }
}
