package view;

import controller.ProgramController;
import controller.ViewController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;

public class MainMenuPanel {
    private final ProgramController pc;
    private JPanel panel1;
    private JButton logOutButton;
    private JLabel loggedInAsLabel;
    private JPanel scrollPanel;
    private JPanel iPanel;

    public MainMenuPanel(ViewController vc, ProgramController pc) {
        this.pc = pc;

        logOutButton.addActionListener(e -> {
            pc.resetSchueler();
            vc.setLoginPanel();
        });
    }

    public void updateLabel() {
        loggedInAsLabel.setText("  Logged in as " + pc.getSchueler().getFirstName() + " " + pc.getSchueler().getLastName() + " (" + pc.getSchueler().getSid() + ")");
    }

    public JPanel getPanel() {
        return panel1;
    }

    public void insertYearInfo(YearInfoPanel[] yearPanels) {
        scrollPanel.setLayout(new BoxLayout(scrollPanel,BoxLayout.PAGE_AXIS));
        for (YearInfoPanel panel : yearPanels) {
            scrollPanel.add(panel.getPanel());
        }
    }
}
