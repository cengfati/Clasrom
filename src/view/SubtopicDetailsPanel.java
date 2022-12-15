package view;

import javax.swing.*;

public class SubtopicDetailsPanel {
    private JPanel panel1;
    private JTextPane detailsTextPane;
    private JCheckBox abgeschlossenCheckBox;

    public SubtopicDetailsPanel(String topic) {
        detailsTextPane.setEditable(false);
    }

    public JPanel getPanel() {
        return panel1;
    }
}
