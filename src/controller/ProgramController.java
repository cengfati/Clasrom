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

    public void login(String text, char[] password) {
        System.out.printf("%s %s", text, Arrays.toString(password));
    }

    public void signIn(String user, char[] password) {
        if(isUsernameValid(user)) {
            dbc.executeStatement("SELECT SID FROM FLAN_Schueler WHERE SID = '" + user + "';");
            if(dbc.getCurrentQueryResult().getRowCount() == 0) {
                //TODO Finish this
            } else {
                //TODO and this
            }
        }
    }

    public boolean isUsernameValid(String user) {
        return user.matches("[a-zA-Z0-9]+");
    }
}
