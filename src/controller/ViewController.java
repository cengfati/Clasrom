package controller;

import view.LoadingPanel;
import view.LoginPanel;
import view.MainMenuPanel;
import view.SignUpPanel;

import javax.swing.*;
import java.awt.*;

public class ViewController {

    private final JFrame frame;
    private final LoadingPanel loading;
    private final LoginPanel login;
    private final SignUpPanel signUp;
    private final MainMenuPanel mainMenu;

    public ViewController (ProgramController pc) {
        this.frame = new JFrame();

        this.loading = new LoadingPanel();
        this.login = new LoginPanel(this, pc);
        this.signUp = new SignUpPanel(this, pc);
        this.mainMenu = new MainMenuPanel();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(loading.getPane());
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(800,500));
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    public LoadingPanel getLoading() {
        return loading;
    }

    public SignUpPanel getSignUp() {
        return signUp;
    }

    public LoginPanel getLogin() {
        return login;
    }

    public void setLoginPanel() {
        frame.setContentPane(login.getPanel());
        frame.setVisible(true);
    }

    public void setSignUpPanel() {
        frame.setContentPane(signUp.getPanel());
        frame.setVisible(true);
    }

    public void setMainMenu() {
        frame.setContentPane(mainMenu.getPanel());
        frame.setVisible(true);
    }
}
