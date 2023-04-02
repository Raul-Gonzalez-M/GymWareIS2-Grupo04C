package controller;

import model.*;
import view.*;

public class Controller {
    private MainWindow mainWindow;
    private LoginWindow loginWindow;

    public Controller() {
        mainWindow = new MainWindow();
    	mainWindow.setVisible(true);
    }

    public void start() {

    }

    public boolean login(String user, String password) {
		return false;
    	
    }
}
