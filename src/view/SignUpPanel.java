package view;

import controller.ProgramController;
import controller.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SignUpPanel {

    private JPanel panel1;
    private JTextField usernameTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel errorLabel;
    private JButton goBackButton;
    private JButton confirmButton;

    public SignUpPanel(ViewController vc, ProgramController pc) {
        errorLabel.setForeground(Color.RED);

        ActionListener signInListener = e -> {
            if(!usernameTextField.getText().isBlank() && passwordField1.getPassword().length != 0 && passwordField2.getPassword().length != 0 ) {
                if (Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword())) {
                    if (new String(passwordField1.getPassword()).matches("[a-zA-Z]+")) {
                        if (passwordField1.getPassword().length > 5) {
                            pc.signUp(usernameTextField.getText(), passwordField1.getPassword());
                        } else {
                            errorLabel.setText("Your password needs to be at least 5 letters long.");
                        }
                    } else {
                        errorLabel.setText("Invalid password. (Use letters A-z and numbers)");
                    }
                } else {
                    errorLabel.setText("The passwords do not match.");
                }
            } else {
                errorLabel.setText("Please enter a username and password");
            }
        };

        confirmButton.addActionListener(signInListener);
        passwordField1.addActionListener(signInListener);
        passwordField2.addActionListener(signInListener);
        usernameTextField.addActionListener(signInListener);

        goBackButton.addActionListener(e -> vc.setLoginPanel());
    }

    public Container getPanel() {
        return panel1;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }
}
