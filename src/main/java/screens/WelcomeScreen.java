package screens;

import user_access_use_case.*;
import user_login_use_case.LoginMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

// Frameworks/Drivers layer

/**
 * The screen for the welcome page. The user is prompted with two options.
 * The first option is to sign up and create a new user. The second option
 * is to login with already existing credentials.
 */
public class WelcomeScreen extends JFrame {

    /**
     * This method is called when the login button is clicked. Sends the user
     * at the keyboard to the login use case.
     */
    private void loginClicked() {
        LoginMain.create();
        this.dispose();
    }

    /**
     * This method is called when the signup button is clicked. Sends the user
     * at the keyboard to the login use case.
     */
    private void signupClicked() throws IOException {
        SignUpMain.create();
        this.dispose();
    }

    /**
     * Constructor for WelcomeScreen. Creates and sets up the JFrame for the screen.
     */
    public WelcomeScreen() throws IOException {
        JPanel pnl = new JPanel(new GridLayout(2,2));
        JButton signupButton = new JButton("Sign up");

        signupButton.addActionListener(event -> {
            try {
                signupClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pnl.add(signupButton);
        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(actionEvent -> {
            loginClicked();
        });
        pnl.add(loginButton);
        this.add(pnl);
        this.setTitle("Welcome");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(500, 100);
        this.setVisible(true);
    }

}
