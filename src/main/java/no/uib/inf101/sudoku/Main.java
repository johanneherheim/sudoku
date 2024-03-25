package no.uib.inf101.sudoku;

import javax.swing.JFrame;

import no.uib.inf101.sudoku.controller.Controller;
import no.uib.inf101.sudoku.model.Model;
import no.uib.inf101.sudoku.view.View;

public class Main {
  public static final String WINDOW_TITLE = "Sudoku";

  public static void main(String[] args) {

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Model model = new Model();
    View view = new View(model);

    @SuppressWarnings("unused")
    Controller controller = new Controller(model, view);

    frame.setResizable(false);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }
}
