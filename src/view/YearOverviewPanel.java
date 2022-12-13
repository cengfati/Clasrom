package view;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class YearOverviewPanel {
    private JButton openButton;
    private JLabel yearLabel;
    private JPanel panel;

    public YearOverviewPanel(String year, String[] subjects) {
        yearLabel.setText(year);
    }

    public JPanel getPanel() {
        return panel;
    }

}
