package view;

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

    public LoginPanel(ViewController vc, ProgramController pc) {
        errorLabel.setForeground(Color.RED);

        ActionListener loginListener = e -> {
            errorLabel.setText("");
            pc.login(usernameTextField.getText(), passwordPasswordField.getPassword());
        };

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
