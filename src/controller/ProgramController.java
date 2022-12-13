package controller;

import com.mysql.cj.QueryResult;
import land.DatabaseController;
import model.Schueler;
import view.YearOverviewPanel;

import java.util.Arrays;

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
        SELECT FLAN_Themen.Name AS NAME
        FROM FLAN_Themen JOIN FLAN_Kursjahr 
          ON FLAN_Themen.KID = FLAN_Kursjahr.KID
        WHERE FLAN_Kursjahr.Jahrgang = %s
        """;

        String yearsReq = "SELECT DISTINCT Jahrgang FROM FLAN_Kursjahr";
        dbc.executeStatement(yearsReq);
        var yearData = dbc.getCurrentQueryResult().getData();

        YearOverviewPanel[] yearPanels = new YearOverviewPanel[dbc.getCurrentQueryResult().getRowCount()];

        for (int i = 0; i < yearData.length; i++) {
            String[] years = yearData[i];
            String year = years[0];
            var req = String.format(reqTemplate, year);
            dbc.executeStatement(req);

            if (dbc.getCurrentQueryResult().getRowCount() != 0) {
                var data = dbc.getCurrentQueryResult().getData()[0];
                yearPanels[i] = new YearOverviewPanel(year, data);
            } else {
                yearPanels[i] = new YearOverviewPanel(year, new String[]{});
            }
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
