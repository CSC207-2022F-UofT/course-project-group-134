package screens;

import order_use_case.OrderFailed;
import user_login_use_case.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class BuyerScreen extends JFrame {
    private JComboBox<String> residenceDropdown;
    private JComboBox<String> diningHallDropdown;

    private JComboBox<String> foodItemDropdown;

    private void orderClicked(ActionEvent actionEvent) throws IOException {

        try {
            Object inputUserType = residenceDropdown.getSelectedItem();
            Object inputResidence = diningHallDropdown.getSelectedItem();
            /*UserResponseModel response = orderController.create(usernameInput.getText(),
                    emailInput.getText(), passwordString, inputUserType.toString(), inputResidence.toString(),
                    mealPlanInput.getText());*/
            //TODO: edit this later after confirming with Deon

//            this.dispose();
//            WelcomeScreen screen = new WelcomeScreen();
            JOptionPane.showMessageDialog(null,
                    "Please wait for someone to pick up your order",
                    "Order created.",
                    JOptionPane.PLAIN_MESSAGE);

        } catch (OrderFailed ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "Order failed.", JOptionPane.WARNING_MESSAGE);
        }
    }

    public BuyerScreen(LoginController loginController) {
        //this.orderController = orderController;
        JPanel pnl = new JPanel(new GridLayout(5,1));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
        JButton loginButton = new JButton("Create order");
        loginButton.addActionListener(actionEvent -> {
            try {
                orderClicked(actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        ///Hmm TODO is this logout?
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
        this.setTitle("Buying");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(200, 200);
        this.setVisible(true);
    }
}
