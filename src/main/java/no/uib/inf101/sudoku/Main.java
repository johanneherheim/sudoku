package no.uib.inf101.sudoku;

import java.awt.Toolkit;
import javax.swing.JFrame;

import no.uib.inf101.sudoku.controller.Controller;
import no.uib.inf101.sudoku.model.Model;
import no.uib.inf101.sudoku.view.View;

public class Main {
  public static final String WINDOW_TITLE = "Sudoku";

  public static void main(String[] args) {
    // stack overflow
    // https://stackoverflow.com/questions/21921135/using-setlocation-to-move-the-jframe-around-windows-java
    // 5. mars 2024
    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Model model = new Model();
    View view = new View(model);

    @SuppressWarnings("unused")
    Controller controller = new Controller(model, view);

    frame.setLocation((screenWidth - 700) / 2, (screenHeight - 800) / 2);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
