package controller;

import com.formdev.flatlaf.FlatLightLaf;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewController {

    private final ProgramController pc;
    private final JFrame frame;
    private final LoadingPanel loading;
    private LoginPanel login;
    private SignUpPanel signUp;
    private MainMenuPanel mainMenu;

    public ViewController (ProgramController pc) {
        FlatLightLaf.setup();

        this.pc = pc;
        this.frame = new JFrame();
        this.loading = new LoadingPanel();
        this.login = new LoginPanel(this, pc);
        this.signUp = new SignUpPanel(this, pc);
        this.mainMenu = new MainMenuPanel(this, pc);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(loading.getPane());
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(800,500));
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    private YearInfoPanel[] getYearPanels() {
        ArrayList<YearInfoPanel> result = new ArrayList<>();
        //Get all topics, with subject and year
        pc.getDbc().executeStatement("""
                SELECT Jahrgang, Fach, Name
                FROM FLAN_Themen
                ORDER BY Jahrgang ASC, Fach
                """);
        var dbcData = pc.getDbc().getCurrentQueryResult().getData();
        String currentYear = dbcData[0][0];
        ArrayList<String[]> subjectList = new ArrayList<>();
        //For all topics
        for(String[] data : dbcData) {
            if(!currentYear.equals(data[0])) {
                result.add(new YearInfoPanel(data[0],subjectList.toArray(new String[0][]))); //Create a new YearInfoPanel for every distinct year
                currentYear = data[1]; //Update the year
                subjectList = new ArrayList<>(); //Recreate the arrayList with the subjects and topics
            }
            String[] tmp = {
                    data[1],
                    data[2]
            };
            subjectList.add(tmp); //Subject und data als StringArray in die ArrayList hinzufügen (Um sie dann als 2D StringArray and YearInfoPanel weiterzugeben)
        }
        return result.toArray(new YearInfoPanel[0]);
    }


    public LoadingPanel getLoading() {
        return loading;
    }

    public SignUpPanel getSignUp() {
        return signUp;
    }

    public LoginPanel getLogin() {
        return login;
    }

    public void setLoginPanel() {
        login = new LoginPanel(this, pc);
        frame.setContentPane(login.getPanel());
        frame.setVisible(true);
    }

    public void setSignUpPanel() {
        signUp = new SignUpPanel(this, pc);
        frame.setContentPane(signUp.getPanel());
        frame.setVisible(true);
    }

    public void setMainMenu() {
        frame.setContentPane(mainMenu.getPanel());
        mainMenu.updateLabel();
        frame.setVisible(true);
    }

    public void loadYearInfo() {
        mainMenu.insertYearInfo(getYearPanels());
        mainMenu.getPanel().revalidate();
    }
}
