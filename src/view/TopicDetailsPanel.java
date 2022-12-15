package view;

import javax.swing.*;
import java.awt.*;

public class TopicDetailsPanel {
    private JButton backButton;
    private JPanel panel1;
    private JPanel scrollPanel;
    private JLabel headerLabel;

    public TopicDetailsPanel(String topicName) {
        scrollPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;

        headerLabel.setText("Details für " + topicName);
        scrollPanel.add(new SubtopicDetailsPanel(topicName).getPanel(),c);
    }

    public JPanel getPanel() {
        return panel1;
    }
}
