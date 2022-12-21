package controller;

import com.formdev.flatlaf.FlatLightLaf;
import view.*;
import view.TopicDetailsPanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
                JOIN FLAN_Faecher
                ON FLAN_Themen.FID = FLAN_Faecher.FID
                ORDER BY Jahrgang ASC, Fach
                """);
        var dbcData = pc.getDbc().getCurrentQueryResult().getData();
        String currentYear = dbcData[0][0];
        ArrayList<String[]> subjectList = new ArrayList<>();
        //For all topics
        for(String[] data : dbcData) {
            if(!currentYear.equals(data[0])) {
                result.add(new YearInfoPanel(data[0],subjectList.toArray(new String[0][]),this)); //Create a new YearInfoPanel for every distinct year
                currentYear = data[0]; //Update the year
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

    public void setTopicDetails(String topic) {
        frame.setContentPane(new TopicDetailsPanel(topic,getSubTopics(topic),this).getPanel());
        frame.revalidate();
    }

    private String[][] getSubTopics(String topic) {
        List<String[]> result = new ArrayList<>();

        pc.getDbc().executeStatement("""
                SELECT FLAN_Unterthemen.Name AS UnterName, Informationen
                FROM FLAN_Unterthemen
                JOIN FLAN_Themen
                ON FLAN_Unterthemen.TID = FLAN_Themen.TID
                WHERE FLAN_Themen.Name = \"""" + topic + """
                " ORDER BY UnterName ASC
                """);
        var dbcData = pc.getDbc().getCurrentQueryResult().getData();

        for(String[] data : dbcData) {
            String[] tmp = {data[0], data[1]};
            result.add(tmp);
        }

        return result.toArray(new String[0][0]);
    }

    public void abgeschlossen(String subtopic, int value){
        pc.getDbc().executeStatement("""
                UPDATE FLAN_Abgeschlossen
                SET FLAN_Abgeschlossen.abgeschlossen = """ + value + """
                WHERE SID = """ + pc.getSchueler().getSid() + """
                AND TID = """ + subtopic + """
                """);
    }

    public JFrame getFrame() {
        return frame;
    }
}
