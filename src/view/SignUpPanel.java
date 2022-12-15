package view;

import controller.ProgramController;
import controller.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SignUpPanel {

    private JPanel panel1;
    private JTextField firstNameTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel errorLabel;
    private JButton goBackButton;
    private JButton confirmButton;
    private JTextField lastNameTextField;

    public SignUpPanel(ViewController vc, ProgramController pc) {
        errorLabel.setForeground(Color.RED);

        ActionListener signUpListener = e -> {
            if (!firstNameTextField.getText().isBlank() && !lastNameTextField.getText().isBlank() && passwordField1.getPassword().length != 0 && passwordField2.getPassword().length != 0) {
                if (Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword())) {
                    if (new String(passwordField1.getPassword()).matches("[a-zA-Z0-9]+")) {
                        if (passwordField1.getPassword().length > 4) {
                            errorLabel.setText("");
                            pc.signUp(firstNameTextField.getText(), lastNameTextField.getText(), passwordField1.getPassword());
                        } else {
                            errorLabel.setText("Dein Passwort muss mindestens fünf Zeichen enthalten");
                        }
                    } else {
                        errorLabel.setText("Ungültiges Passwort (Benutze Buchstaben zwischen A-z und Zahlen)");
                    }
                } else {
                    errorLabel.setText("Die Passwörter stimmen nicht überein");
                }
            } else {
                errorLabel.setText("Bitte gebe deinen vollen Namen und ein Passwort ein");
            }
        };

        confirmButton.addActionListener(signUpListener);
        passwordField1.addActionListener(signUpListener);
        passwordField2.addActionListener(signUpListener);
        firstNameTextField.addActionListener(signUpListener);
        lastNameTextField.addActionListener(signUpListener);

        goBackButton.addActionListener(e -> vc.setLoginPanel());
    }

    public JPanel getPanel() {
        return panel1;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

}
