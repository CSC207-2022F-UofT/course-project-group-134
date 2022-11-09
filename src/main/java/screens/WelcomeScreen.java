package screens;

import javax.swing.*;

public class WelcomeScreen {

    public static void makeGUI() {
        JFrame gui = new JFrame("Welcome");

        gui.setSize(500, 500);
        gui.setLocationRelativeTo(null);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gui.setVisible(true);
    }
}
