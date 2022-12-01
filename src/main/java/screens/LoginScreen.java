package screens;

import entities.Seller;
import entities.User;
import entities.UserType;
import order_use_case.BuyerMain;
import order_use_case.DoesNotExistException;
import selling_use_case.SellerMain;
import user_login_use_case.LoginController;
import user_login_use_case.LoginFailed;
import user_login_use_case.LoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// Frameworks/Drivers layer

/**
 * The screen for the login page. The user is prompted to fill in its
 * email and password. If the credentials match a user, the user is logged in to the program
 * depending on if the user is a buyer or a seller. If the credentials do not match any user,
 * the user at the keyboard is prompted to try logging in again.
 */
public class LoginScreen extends JFrame {

    private final JTextField emailInput = new JTextField(15);;
    private final JPasswordField passwordInput = new JPasswordField(15);;
    private final LoginController loginController;

    /**
     * This method is called when the login button is clicked. Sends the data to the controller,
     * and verifies if the login is successful.
     */
    private void loginClicked() throws IOException {
        try {
            LoginResponseModel response = loginController.create(emailInput.getText(), passwordInput.getText());
            User user = response.getUser();
            if (response.getUser().getUserType() == UserType.SELLER){
                SellerMain.create(user.getEmail(), ((Seller)user).getMealPlan().getResidence());
            }
            else {
                BuyerMain.create(user.getUsername(), user.getEmail());
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
        } catch (DoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor for LoginScreen. Creates and sets up the JFrame for the screen.
     *
     * @param loginController the controller for the login use case.
     */
    public LoginScreen(LoginController loginController) {
        this.loginController = loginController;
        JPanel pnl = new JPanel(new GridLayout(3,1));

        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel("Enter email"), emailInput);
        pnl.add(emailInfo);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Enter password"), passwordInput);
        pnl.add(passwordInfo);

        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(actionEvent -> {
            try {
                loginClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(actionEvent -> {
            this.dispose();
            try {
                new WelcomeScreen();
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