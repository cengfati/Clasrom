package controller;

import view.LoadingPanel;
import view.LoginPanel;
import view.SignInPanel;

import javax.swing.*;
import java.awt.*;

public class ViewController {

    private final JFrame frame;
    private final LoadingPanel loading;
    private final LoginPanel login;
    private final SignInPanel signIn;

    private final ProgramController pc;

    public ViewController (ProgramController pc) {
        this.pc = pc;

        this.frame = new JFrame();

        this.loading = new LoadingPanel();
        this.login = new LoginPanel(this, pc);
        this.signIn = new SignInPanel(this, pc);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(loading.getPane());
        frame.pack();
        frame.setVisible(true);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    public LoadingPanel getLoading() {
        return loading;
    }

    public void setLoginPanel() {
        frame.setContentPane(login.getPanel());
        frame.setVisible(true);
    }

    public void setSignInPanel() {
        frame.setContentPane(signIn.getPanel());
        frame.setVisible(true);
    }
}
