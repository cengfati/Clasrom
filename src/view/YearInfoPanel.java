package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class YearInfoPanel {
    private JPanel panel1;
    private JPanel iPanel;
    private JLabel headerLabel;

    public YearInfoPanel(String year, String[][] data) {
        //Change header font and set text, set border colour for visibility
        headerLabel.setFont(new Font("", Font.PLAIN, 20));
        headerLabel.setText(" Themen Klasse " + year);
        panel1.setBorder(new LineBorder(Color.LIGHT_GRAY));

        //Make the layout a GridLayout
        panel1.setLayout(new GridBagLayout());
        //iPanel.setLayout(new GridBagLayout());

        //Create GridBagConstraints for the buttons
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.ipady = 5;
        c.insets = new Insets(5,5,5,5);

        //ActionListener for all the topic buttons
        ActionListener a = e -> {
            JButton button = (JButton) e.getSource();
            //TODO Show topic details for the topic that is the source's button text
        };


        String currentSubject = "";
        for (String[] loopData : data) {
            //Check whether the subject is new
            if (!currentSubject.equals(loopData[0])) { //If not
                currentSubject = loopData[0]; //Set subject to the next one
                var newLabel = new Label(currentSubject);
                newLabel.setFont(new Font("", Font.PLAIN, 15));
                newLabel.setForeground(Color.GRAY);
                panel1.add(newLabel,c); //Add a new header with the new subject
            }
            //Add a button for every topic, with the ActionListener a
            var newButton = new JButton(loopData[1]);
            newButton.addActionListener(a);
            panel1.add(newButton,c);
        }
    }

    public JPanel getPanel() {
        return panel1;
    }
}
