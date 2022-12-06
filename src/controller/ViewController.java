package controller;

import view.LoadingPanel;
import view.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class ViewController {

    private JFrame frame;
    private LoadingPanel loading;
    private MainMenuPanel mainMenu;

    public ViewController () {
        this.frame = new JFrame();

        this.loading = new LoadingPanel();
        this.mainMenu = new MainMenuPanel();

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

    public MainMenuPanel getMainMenu() {
        return mainMenu;
    }
}
