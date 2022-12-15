package view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import controller.ProgramController;
import controller.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel {
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JButton signInButton;
    private JPanel panel;
    private JButton loginButton;
    private JLabel errorLabel;
    private JCheckBox lightThemeCheckBox;

    public LoginPanel(ViewController vc, ProgramController pc) {
        errorLabel.setForeground(Color.RED);

        ActionListener loginListener = e -> {
            errorLabel.setText("");
            pc.login(usernameTextField.getText(), passwordPasswordField.getPassword());
        };

        lightThemeCheckBox.addActionListener(e -> {
            if(lightThemeCheckBox.isSelected()) {
                FlatDarkLaf.setup();
            } else {
                FlatLightLaf.setup();
            }
            SwingUtilities.updateComponentTreeUI(vc.getFrame());
        });

        loginButton.addActionListener(loginListener);
        passwordPasswordField.addActionListener(loginListener);
        usernameTextField.addActionListener(loginListener);

        signInButton.addActionListener(e -> vc.setSignUpPanel());
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

}
