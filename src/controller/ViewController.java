package controller;

import com.formdev.flatlaf.FlatLightLaf;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewController {

    private final ProgramController pc;
    private final JFrame frame;
    private final LoadingPanel loading;
    private LoginPanel login;
    private SignUpPanel signUp;
    private MainMenuPanel mainMenu;

    private YearOverviewPanel[] years;

    public ViewController (ProgramController pc) {
        FlatLightLaf.setup();

        this.pc = pc;
        this.frame = new JFrame();
        this.loading = new LoadingPanel();
        this.login = new LoginPanel(this, pc);
        this.signUp = new SignUpPanel(this, pc);
        this.mainMenu = new MainMenuPanel(this, pc);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(loading.getPane());
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(800,500));
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
        login = new LoginPanel(this, pc);
        frame.setContentPane(login.getPanel());
        frame.setVisible(true);
    }

    public void setSignUpPanel() {
        signUp = new SignUpPanel(this, pc);
        frame.setContentPane(signUp.getPanel());
        frame.setVisible(true);
    }

    public void setMainMenu() {
        mainMenu.setPanel(pc.getYears());
        frame.setContentPane(mainMenu.getPanel());
        frame.setVisible(true);
    }
}
