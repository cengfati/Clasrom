package controller;

import land.DatabaseController;

import java.util.Arrays;

public class ProgramController {

    private final controller.ViewController vc;
    private final DatabaseController dbc;

    public ProgramController() {
        vc = new controller.ViewController(this);
        dbc = new DatabaseController();
        startProgram();
    }

    private void startProgram() {
        vc.getLoading().getLoadingLabel().setText("Connecting...");
        if(dbc.connect()) {
            vc.getLoading().getLoadingLabel().setText("Connected!");
            vc.setLoginPanel();
        } else {
            vc.getLoading().getLoadingLabel().setText("Connection error");
        }
    }

    public void login(String user, char[] password) {
        if(isStringValid(user) && isStringValid(String.valueOf(password))) {
            dbc.executeStatement("SELECT SID, Password FROM FLAN_Schueler");
            boolean dataCorrect = false;
            for (int i = 0; i < dbc.getCurrentQueryResult().getData().length; i++) {
                if(user.equals(dbc.getCurrentQueryResult().getData()[i][0]) && String.valueOf(password).equals(dbc.getCurrentQueryResult().getData()[i][1])) {
                    dataCorrect = true;
                }
            }
            if(dataCorrect) {
                System.exit(-1);
                //TODO
            } else {
                vc.getLogin().getErrorLabel().setText("Wrong username or password");
            }
        }
    }

    public void signUp(String user, char[] password) {
        if(isStringValid(user)) {
            dbc.executeStatement("SELECT SID FROM FLAN_Schueler WHERE SID = '" + user + "';");
            if(dbc.getCurrentQueryResult().getRowCount() == 0) {
                //TODO Finish this
            } else {
                vc.getSignUp().getErrorLabel().setText("Username already taken.");
            }
        }
    }

    public boolean isStringValid(String s) {
        return s.matches("[a-zA-Z0-9]+");
    }
}
