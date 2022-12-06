package controller;

import land.DatabaseController;

public class ProgramController {

    private final controller.ViewController vc;
    private final DatabaseController dbc;

    public ProgramController() {
        vc = new controller.ViewController();
        dbc = new DatabaseController();
        startProgram();
    }

    private void startProgram() {
        vc.getLoading().getLoadingLabel().setText("Connecting...");
        if(dbc.connect())
            vc.getLoading().getLoadingLabel().setText("Connected!");
        else
            vc.getLoading().getLoadingLabel().setText("Connection error");
    }
}
