package view;

import model.Tuple;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class YearOverviewPanel {
    private JButton openButton;
    private JLabel yearLabel;
    private JPanel panel;
    private JPanel innerPanel;

    public YearOverviewPanel(String year, List<Tuple<String, List<String>>> data) {
        yearLabel.setText(year);
        yearLabel.setFont(new Font("Jokerman", Font.PLAIN, 40));
        panel.setBorder(new LineBorder(Color.GRAY));

        innerPanel.setLayout(new FlowLayout());
        for (Tuple<String, List<String>> subject : data) {
            var panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            var tmp = new JLabel(subject.left());
            tmp.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
            panel.add(tmp);
            for (String s : subject.right()) {
                panel.add(new JLabel(s));
            }
            innerPanel.add(panel);
        }
    }

    public JPanel getPanel() {
        return panel;
    }

}
