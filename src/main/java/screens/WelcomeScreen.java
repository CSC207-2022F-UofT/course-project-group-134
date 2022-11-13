package screens;

import user_access_use_case.SignUpInputBoundary;
import user_access_use_case.UserRequestController;
import user_access_use_case.UserRequestModel;
import user_access_use_case.UserResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class WelcomeScreen extends JFrame {

    private UserRequestController signupController;

    private void loginClicked(ActionEvent event) {
        LoginScreen screen = new LoginScreen();
        this.dispose();
    }

    private void signupClicked(ActionEvent event) throws IOException {
        SignupScreen screen = new SignupScreen(this.signupController);
        this.dispose();
    }

    public WelcomeScreen(UserRequestController signupController) throws IOException {
        this.signupController = signupController;
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
        this.setLocation(200, 200);
        this.setVisible(true);
    }

}
