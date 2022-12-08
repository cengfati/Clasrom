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

    public LoginPanel(ViewController vc, ProgramController pc) {

        ActionListener loginListener = e -> {
            pc.login(usernameTextField.getText(), passwordPasswordField.getPassword());
        };

        loginButton.addActionListener(loginListener);
        passwordPasswordField.addActionListener(loginListener);
        usernameTextField.addActionListener(loginListener);

        signInButton.addActionListener(e -> vc.setSignInPanel());
    }

    public Container getPanel() {
        return panel;
    }
}
