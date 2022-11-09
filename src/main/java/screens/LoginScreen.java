package screens;

import javax.swing.*;
import java.awt.*;

public class LoginScreen {

    public void makeGUI() {
        JFrame frame = new JFrame();
        JPanel pnl = new JPanel(new GridLayout(3,2));
        JTextField emailInput = new JTextField("Email");
        pnl.add(emailInput);
        JTextField passwordInput = new JTextField("Password");
        pnl.add(passwordInput);
        JButton loginButton = new JButton("Log in");
        pnl.add(loginButton);
        frame.add(pnl);
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
}