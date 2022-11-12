package screens;

import entities.MealPlan;
import entities.UserType;
import user_access_use_case.SignUpFailed;
import user_access_use_case.UserRequestController;
import user_access_use_case.UserResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignupScreen extends JFrame {

    private JTextField usernameInput = new JTextField(15);
    private JTextField emailInput = new JTextField(15);
    private JPasswordField passwordInput = new JPasswordField(15);
    private JPasswordField confirmInput = new JPasswordField(15);
    private JTextField mealPlanInput = new JTextField(15);
    private JComboBox<String> userTypeDropdown;
    private JComboBox<String> residenceDropdown;
    private UserRequestController signupController;

    private void signupClicked(ActionEvent actionEvent) throws IOException {
        String passwordString = String.valueOf(passwordInput.getPassword());
        String confirmString = String.valueOf(confirmInput.getPassword());

        if (passwordString.equals(confirmString)) {
            try {
                UserType userType = null;
                MealPlan mealPlan = null;
                Object inputUserType = userTypeDropdown.getSelectedItem();
                Object inputMealPlan = residenceDropdown.getSelectedItem();
                if (inputUserType != null){
                    if (inputUserType.equals("Buyer")){
                        userType = UserType.BUYER;
                    } else {
                        userType = UserType.SELLER;
                        if (inputMealPlan != null){
                            mealPlan = new MealPlan(inputMealPlan.toString(),
                                    Double.parseDouble(mealPlanInput.getText()));
                        }
                    }
                }
                UserResponseModel response = signupController.create(usernameInput.getText(), emailInput.getText(),
                        passwordString, userType, mealPlan);

                this.dispose();
                WelcomeScreen screen = new WelcomeScreen(this.signupController);
                JOptionPane.showMessageDialog(null,
                        "Login with " + response.getName() + ".",
                        "Login with credentials.",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (SignUpFailed ex) {
                JOptionPane.showMessageDialog(null,
                        "The sign up failed. Try again.",
                        "Try again.",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Try again. Passwords do not match.",
                    "Try again.",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public SignupScreen(UserRequestController signupController) throws IOException {
        this.signupController = signupController;
        JPanel pnl = new JPanel(new GridLayout(6,1));
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
        JLabel userTypeLabel = new JLabel("Select one user type:");
        userTypeLabel.setVisible(true);

        pnl.add(userTypeLabel);

        String[] userTypeChoices = {"Buyer","Seller"};

        userTypeDropdown = new JComboBox<String>(userTypeChoices);

        // College dropdown
        JLabel residenceLabel = new JLabel("Select your residence:");
        residenceLabel.setVisible(false);


        pnl.add(residenceLabel);

        String[] residenceChoices = {"Chelsea","Chestnut", "Innis College", "New College", "St. Michael's College",
                "Trinity College", "University College", "Victoria College", "Woodsworth College"};

        residenceDropdown = new JComboBox<String>(residenceChoices);
        residenceDropdown.setVisible(false);

        pnl.add(userTypeDropdown);
        pnl.add(residenceDropdown);
        pnl.add(usernameInfo);
        pnl.add(emailInfo);
        pnl.add(passwordInfo);
        pnl.add(repeatPasswordInfo);
        pnl.add(balanceInfo);
        userTypeDropdown.addActionListener(
                new ActionListener(){
                    // show balance box based on userType selected
                    public void actionPerformed(ActionEvent e){
                        if (userTypeDropdown.getSelectedItem().equals("Seller")){
                            balanceInfo.setVisible(true);
                            residenceLabel.setVisible(true);
                            residenceDropdown.setVisible(true);
                        } else {
                            balanceInfo.setVisible(false);
                            residenceLabel.setVisible(false);
                            residenceDropdown.setVisible(false);
                        }
                    }
                }
        );

        JButton signupButton = new JButton("Sign up");
        signupButton.addActionListener(actionEvent -> {
            try {
                signupClicked(actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pnl.add(signupButton);
        this.add(pnl);
        this.setTitle("Sign up");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);
    }
}