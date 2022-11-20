package screens;

import entities.UserType;
import entities.ResidenceType;
import user_access_use_case.SignUpFailed;
import user_access_use_case.SignUpRequestController;
import user_access_use_case.SignUpResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 */
public class SignupScreen extends JFrame {

    private JTextField usernameInput = new JTextField(15);
    private JTextField emailInput = new JTextField(15);
    private JPasswordField passwordInput = new JPasswordField(15);
    private JPasswordField confirmInput = new JPasswordField(15);
    private JTextField mealPlanInput = new JTextField(15);
    private JComboBox<String> userTypeDropdown;
    private JComboBox<String> residenceDropdown;
    private SignUpRequestController signupController;

    private void signupClicked(ActionEvent actionEvent) throws IOException {
        String passwordString = String.valueOf(passwordInput.getPassword());
        String confirmString = String.valueOf(confirmInput.getPassword());

        if (passwordString.equals(confirmString)) {
            try {
                Object inputUserType = userTypeDropdown.getSelectedItem();
                Object inputResidence = residenceDropdown.getSelectedItem();
                SignUpResponseModel response = signupController.create(usernameInput.getText(),
                        emailInput.getText(), passwordString, inputUserType.toString(), inputResidence.toString(),
                        mealPlanInput.getText());

                this.dispose();
                WelcomeScreen screen = new WelcomeScreen();
                JOptionPane.showMessageDialog(null,
                        "Login with " + response.getName() + ".",
                        "Signup succeeded.",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (NumberFormatException ex) {
                // When user types in something that is not number in balance.
                JOptionPane.showMessageDialog(null,
                        mealPlanInput.getText() + " is not a valid balance.", //TODO: Perhaps the presenter should handle this?
                        "Signup failed.", JOptionPane.WARNING_MESSAGE);
            } catch (SignUpFailed ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Signup failed.", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Try again. Passwords do not match.",
                    "Try again.",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public SignupScreen(SignUpRequestController signupController) {
        this.signupController = signupController;
        JPanel pnl = new JPanel(new GridLayout(8,1));
        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Choose username"), usernameInput);
        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel("Choose email"), emailInput);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Choose password"), passwordInput);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel("Enter password again"), confirmInput);
        LabelTextPanel balanceInfo = new LabelTextPanel(
                new JLabel("Enter meal plan balance"), mealPlanInput);
        balanceInfo.setVisible(false);

        // Usertype dropdown
        // converting enum to string
        UserType[] userStates = UserType.values();
        String[] userTypeList = new String[userStates.length];

        for (int i = 0; i < userStates.length; i++) {
            userTypeList[i] = userStates[i].toString();
        }

        ResidenceType[] residenceStates = ResidenceType.values();
        String[] residenceTypeList = new String[residenceStates.length];

        for (int i = 0; i < residenceStates.length; i++) {
            residenceTypeList[i] = residenceStates[i].toString();
        }

        userTypeDropdown = new JComboBox<>(userTypeList);
        LabelComboboxPanel userTypePanel = new LabelComboboxPanel(
                new JLabel("Select one user type:"), userTypeDropdown);

        // College dropdown
        residenceDropdown = new JComboBox<>(residenceTypeList);
        LabelComboboxPanel residencePanel = new LabelComboboxPanel(
                new JLabel("Select your residence:"), residenceDropdown);

        residencePanel.setVisible(false);

        pnl.add(userTypePanel);
        pnl.add(usernameInfo);
        pnl.add(emailInfo);
        pnl.add(passwordInfo);
        pnl.add(repeatPasswordInfo);
        pnl.add(residencePanel);
        pnl.add(balanceInfo);
        userTypeDropdown.addActionListener(
                new ActionListener(){
                    // show balance box based on userType selected
                    public void actionPerformed(ActionEvent e){
                        if (userTypeDropdown.getSelectedItem().equals(UserType.SELLER.toString())){
                            balanceInfo.setVisible(true);
                            residencePanel.setVisible(true);
                        } else {
                            balanceInfo.setVisible(false);
                            residencePanel.setVisible(false);
                        }
                    }
                }
        );

        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));

        JButton signupButton = new JButton("Sign up");
        signupButton.addActionListener(actionEvent -> {
            try {
                signupClicked(actionEvent);
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
        buttonsPanel.add(signupButton);

        pnl.add(buttonsPanel);
        this.add(pnl);
        this.setTitle("Sign up");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);
    }
}