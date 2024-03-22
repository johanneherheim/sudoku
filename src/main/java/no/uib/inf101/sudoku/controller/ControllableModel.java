package no.uib.inf101.sudoku.controller;

import no.uib.inf101.sudoku.view.View;

public class ControllableModel implements IControllableModel {

    ControllableModel model;

    View view;

    public ControllableModel(ControllableModel model, View view) {
        this.model = model;
        this.view = view;

    }

}
