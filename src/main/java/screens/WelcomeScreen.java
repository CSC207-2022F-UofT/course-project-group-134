package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WelcomeScreen {

    private void loginClicked(ActionEvent event) {
        LoginScreen screen = new LoginScreen();
        screen.makeGUI();
    }

    public void makeGUI() {
        JFrame frame = new JFrame();
        JPanel pnl = new JPanel(new GridLayout(2,2));
        JButton signupButton = new JButton("Sign up");
        pnl.add(signupButton);
        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(this::loginClicked);
        pnl.add(loginButton);
        frame.add(pnl);
        frame.setTitle("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
}
