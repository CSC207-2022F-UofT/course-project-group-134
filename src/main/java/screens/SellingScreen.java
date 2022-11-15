package screens;

import order_use_case.OrderFailed;
import selling_use_case.SellingController;

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

    public SellingScreen(SellingController sellingController) {
        //this.orderController = orderController;
        JPanel pnl = new JPanel(new GridLayout(5,1));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
        JButton acceptButton = new JButton("Accept Order");
        acceptButton.addActionListener(actionEvent -> {
            try {
                acceptClicked(actionEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // 'Log-out' by going back to the welcome screen
        JButton logOutButton = new JButton("Log out");
        logOutButton.addActionListener(actionEvent -> {
            this.dispose();
            try {
                WelcomeScreen screen = new WelcomeScreen();
                JOptionPane.showMessageDialog(null,
                        "Successfully logged out.",
                        "Logout succeeded",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        buttonsPanel.add(logOutButton);
        buttonsPanel.add(acceptButton);

        pnl.add(buttonsPanel);

        this.add(pnl);
        this.setTitle("Selling");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(500, 100);
        this.setVisible(true);
    }
}
