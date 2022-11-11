package screens;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    public LoginScreen() {
        JPanel pnl = new JPanel(new GridLayout(3,2));
        JTextField emailInput = new JTextField("Email");
        pnl.add(emailInput);
        JTextField passwordInput = new JTextField("Password");
        pnl.add(passwordInput);
        JButton loginButton = new JButton("Log in");
        pnl.add(loginButton);
        this.add(pnl);
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(200, 200);
        this.setVisible(true);
    }
}