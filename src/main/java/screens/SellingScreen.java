package screens;

import order_use_case.OrderFailed;
import user_login_use_case.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class SellingScreen extends JFrame {


    private void acceptClicked(ActionEvent actionEvent) throws IOException {

        try {
            JOptionPane.showMessageDialog(null,
                    "Please communicate with the buyer to give them their food.",
                    "Order accepted.",
                    JOptionPane.PLAIN_MESSAGE);

        } catch (OrderFailed ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "Order failed.", JOptionPane.WARNING_MESSAGE);
        }
    }

    public SellingScreen(LoginController loginController) {
        //this.orderController = orderController;
        JPanel pnl = new JPanel(new GridLayout(5,1));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
        JButton loginButton = new JButton("Accept Order");
        loginButton.addActionListener(actionEvent -> {
            try {
                acceptClicked(actionEvent);
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
