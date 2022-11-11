package screens;

import user_access_use_case.UserRequestController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignupScreen extends JFrame {

    private JTextField usernameInput = new JTextField(15);
    private JTextField emailInput = new JTextField(15);
    private JPasswordField passwordInput = new JPasswordField(15);
    private JPasswordField confirmInput = new JPasswordField(15);
    private UserRequestController signupController;

    private void signupClicked(ActionEvent actionEvent) {
        String passwordString = String.valueOf(passwordInput.getPassword());
        String confirmString = String.valueOf(confirmInput.getPassword());

        if (passwordString.equals(confirmString)) {
            signupController.create(usernameInput.getText(), emailInput.getText(),
                    passwordString, null, null);
            // TODO: fix null and make user able to add userType
        } else {
            JOptionPane.showMessageDialog(null,
                    "Try again. Passwords do not match.",
                    "Try again.",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public SignupScreen(UserRequestController signupController) {
        this.signupController = signupController;
        JPanel pnl = new JPanel(new GridLayout(5,2));
        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Choose username"), usernameInput);
        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel("Choose email"), emailInput);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Choose password"), passwordInput);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel("Enter password again"), confirmInput);
        pnl.add(usernameInfo);
        pnl.add(emailInfo);
        pnl.add(passwordInfo);
        pnl.add(repeatPasswordInfo);
        JButton signupButton = new JButton("Sign up");
        signupButton.addActionListener(this::signupClicked);
        pnl.add(signupButton);
        this.add(pnl);
        this.setTitle("Sign up");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(200, 200);
        this.setVisible(true);
    }
}