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

// Frameworks/Drivers layer

/**
 * The screen for the signup page. The user at the keyboard is prompted
 * to fill out credential information in order to create a new user in the
 * program. The information the user needs to fill in depends on if the user
 * is signing up as a buyer or a seller. If the sigup succeeds, the program
 * creates a new user with the credentials provided. If the signup fails,
 * the program prompts the user at the keyboard to try again.
 */
public class SignupScreen extends JFrame {

    private final JTextField usernameInput = new JTextField(15);
    private final JTextField emailInput = new JTextField(15);
    private final JPasswordField passwordInput = new JPasswordField(15);
    private final JPasswordField confirmInput = new JPasswordField(15);
    private final JTextField mealPlanInput = new JTextField(15);
    private final JComboBox<String> userTypeDropdown;
    private final JComboBox<String> residenceDropdown;
    private final SignUpRequestController signupController;

    /**
     * This method is called when the signup button is clicked. Sends the data to the controller,
     * and verifies if the signup is successful. The signup is successful if the email inputted
     * is unique and if all the information provided is valid.
     */
    private void signupClicked() throws IOException {
        String passwordString = String.valueOf(passwordInput.getPassword());
        String confirmString = String.valueOf(confirmInput.getPassword());

        if (passwordString.equals(confirmString)) {
            try {
                Object inputUserType = userTypeDropdown.getSelectedItem();
                Object inputResidence = residenceDropdown.getSelectedItem();
                assert inputUserType != null;
                assert inputResidence != null;
                SignUpResponseModel response = signupController.create(usernameInput.getText(),
                        emailInput.getText(), passwordString, inputUserType.toString(), inputResidence.toString(),
                        mealPlanInput.getText());

                this.dispose();
                new WelcomeScreen();
                JOptionPane.showMessageDialog(null,
                        "Login with " + response.getName() + ".",
                        "Signup succeeded.",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (NumberFormatException ex) {
                // When user types in something that is not number in balance.
                JOptionPane.showMessageDialog(null,
                        mealPlanInput.getText() + " is not a valid balance.",
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

    /**
     * Constructor for signup screen. Creates and sets up the JFrame for the screen.
     *
     * @param signupController the controller for the signup use case.
     */
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
                signupClicked();
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