package controller;

import com.mysql.cj.QueryResult;
import land.DatabaseController;
import model.Schueler;
import model.Tuple;
import view.YearOverviewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramController {

    private final controller.ViewController vc;
    private final DatabaseController dbc;
    private Schueler schueler;

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
            dbc.executeStatement("SELECT SID, Password, Vorname, Name FROM FLAN_Schueler");
            boolean dataCorrect = false;
            var resultData = dbc.getCurrentQueryResult().getData();
            for (int i = 0; i < dbc.getCurrentQueryResult().getData().length; i++) {
                if(user.equals(resultData[i][0]) && String.valueOf(password).equals(resultData[i][1])) {
                    dataCorrect = true;
                    schueler = new Schueler(resultData[i][2],resultData[i][3],resultData[i][0]);
                }
            }
            if(dataCorrect) {
                vc.setMainMenu();
            } else {
                vc.getLogin().getErrorLabel().setText("Wrong username or password");
            }
        }
    }

    public void signUp(String firstName, String lastName, char[] password) {
        if(isStringValid(firstName) && isStringValid(lastName)) {
            String sid = String.copyValueOf(lastName.toLowerCase().toCharArray(),0,Math.min(lastName.length(),4)) +
                    String.copyValueOf(firstName.toLowerCase().toCharArray(),0,Math.min(firstName.length(),4));

            dbc.executeStatement("SELECT SID FROM FLAN_Schueler WHERE SID = '" + sid + "';");
            if(dbc.getCurrentQueryResult().getRowCount() == 0) {
                dbc.executeStatement("INSERT INTO `FLAN_Schueler`(`Name`, `Vorname`, `SID`, `Password`) VALUES ('"
                        + lastName + "','" + firstName + "','" + sid + "','" + String.valueOf(password) +"')");
                schueler = new Schueler(firstName,lastName,sid);
                vc.setMainMenu();
            } else {
                vc.getSignUp().getErrorLabel().setText("You're already registered.");
            }
        }
    }

    public YearOverviewPanel[] getYears() {
        String reqTemplate = """
        SELECT Name, Fach
        FROM FLAN_Themen
        WHERE Jahrgang = %s
        ORDER BY Fach
        """;

        dbc.executeStatement("SELECT DISTINCT Jahrgang FROM FLAN_Themen");
        var years = dbc.getCurrentQueryResult().getData();
        YearOverviewPanel[] yearPanels = new YearOverviewPanel[years.length];
        for (int i = 0; i < years.length; i++) {
            String year = years[i][0];

            dbc.executeStatement(String.format(reqTemplate, year));
            var data = dbc.getCurrentQueryResult().getData();

            //dbc.executeStatement("SELECT DISTINCT Fach FROM FLAN_Themen");

            List<Tuple<String, List<String>>> dataList = new ArrayList<>();
            String currentSubject = null;
            for (String[] datum : data) {
                if (!datum[1].equals(currentSubject)) {
                    currentSubject = datum[1];
                    dataList.add(new Tuple<>(datum[1], new ArrayList<>()));
                }
                dataList.get(dataList.size() - 1).right().add(datum[0]);
            }

            yearPanels[i] = new YearOverviewPanel(year, dataList);
        }

        return yearPanels;
    }

    public boolean isStringValid(String s) {
        return s.matches("[a-zA-Z0-9]+");
    }

    public void resetSchueler() {
        schueler = null;
    }

    public Schueler getSchueler() {
        return schueler;
    }
}
