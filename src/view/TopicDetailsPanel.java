package view;

import controller.ViewController;

import javax.swing.*;
import java.awt.*;

public class TopicDetailsPanel {
    private JButton backButton;
    private JPanel panel1;
    private JPanel scrollPanel;
    private JLabel headerLabel;

    public TopicDetailsPanel(String topic, String[][] subtopics, ViewController vc) {
        scrollPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.insets = new Insets(20,0,20,0);

        headerLabel.setText("Details für " + topic);

        for(String[] data : subtopics) {
            scrollPanel.add(new SubtopicDetailsPanel(data[0],data[1],vc).getPanel(),c);
        }

        backButton.addActionListener(e -> vc.setMainMenu());
    }
    public JPanel getPanel() {
        return panel1;
    }
}
