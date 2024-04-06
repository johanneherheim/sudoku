package no.uib.inf101.sudoku;

import no.uib.inf101.sudoku.view.pages.LoginPage;
import no.uib.inf101.sudoku.view.tools.LoginData;

/**
 * Hello world!
 */
public class Main {

  public static void main(String[] args) {
    LoginData idandPasswords = new LoginData();

    @SuppressWarnings("unused")
    LoginPage loginPage = new LoginPage(idandPasswords);
  }
}
