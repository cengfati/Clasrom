package view;

import controller.ViewController;

import javax.swing.*;
import java.awt.*;

public class SubtopicDetailsPanel {
    private JPanel panel1;
    private JTextPane detailsTextPane;
    private JCheckBox abgeschlossenCheckBox;
    private JLabel subtopicLabel;

    public SubtopicDetailsPanel(String topic, String details, ViewController vc) {
        subtopicLabel.setText(topic);
        subtopicLabel.setFont(new Font("", Font.BOLD,15));
        detailsTextPane.setEditable(false);
        detailsTextPane.setText(details);
        detailsTextPane.setMinimumSize(new Dimension(700,0));
        detailsTextPane.setPreferredSize(new Dimension(vc.getFrame().getWidth(),0));
    }

    public JPanel getPanel() {
        return panel1;
    }
}
