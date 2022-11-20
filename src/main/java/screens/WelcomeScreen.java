package screens;

import user_access_use_case.*;
import user_login_use_case.LoginMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class WelcomeScreen extends JFrame {

    private void loginClicked(ActionEvent event) {
        LoginMain.create();
        this.dispose();
    }

    private void signupClicked(ActionEvent event) throws IOException {
        SignUpMain.create();
        this.dispose();
    }

    public WelcomeScreen() throws IOException {
        JPanel pnl = new JPanel(new GridLayout(2,2));
        JButton signupButton = new JButton("Sign up");

        signupButton.addActionListener(event -> {
            try {
                signupClicked(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pnl.add(signupButton);
        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(this::loginClicked);
        pnl.add(loginButton);
        this.add(pnl);
        this.setTitle("Welcome");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(500, 100);
        this.setVisible(true);
    }

}
