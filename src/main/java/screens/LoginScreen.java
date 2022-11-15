package screens;

import entities.User;
import entities.UserType;
import order_use_case.BuyerMain;
import selling_use_case.SellerMain;
import user_login_use_case.LoginController;
import user_login_use_case.LoginFailed;
import user_login_use_case.LoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginScreen extends JFrame {

    private JTextField emailInput, passwordInput;
    private LoginController loginController;

    private void loginClicked(ActionEvent actionEvent) throws IOException {
        try {
            LoginResponseModel response = loginController.create(emailInput.getText(), passwordInput.getText());
            User user = response.getUser();
            if (response.getUser().getUserType() == UserType.SELLER){
                SellerMain.create();
            }
            else {
                BuyerMain.create();
            }
            this.dispose();
            JOptionPane.showMessageDialog(null,
                    "Login succeeded\n" + user.toString() + ".",
                    "Login succeeded",
                    JOptionPane.PLAIN_MESSAGE);
        } catch (LoginFailed ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "Login failed.", JOptionPane.WARNING_MESSAGE);
        }
    }

    public LoginScreen(LoginController loginController) {
        this.loginController = loginController;
        JPanel pnl = new JPanel(new GridLayout(3,1));
        emailInput = new JTextField("Email");
        pnl.add(emailInput);
        passwordInput = new JTextField("Password");
        pnl.add(passwordInput);

        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(actionEvent -> {
            try {
                loginClicked(actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(actionEvent -> {
            this.dispose();
            try {
                WelcomeScreen screen = new WelcomeScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        buttonsPanel.add(backButton);
        buttonsPanel.add(loginButton);

        pnl.add(buttonsPanel);

        this.add(pnl);
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(500, 100);
        this.setVisible(true);
    }
}