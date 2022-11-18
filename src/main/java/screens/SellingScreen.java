package screens;

import order_use_case.DoesNotExistException;
import order_use_case.OrderDsGateway;
import order_use_case.OrderDsModel;
import order_use_case.OrderFailed;
import selling_use_case.SellerMain;
import selling_use_case.SellingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class SellingScreen extends JFrame {

    private JComboBox<String> currentOrdersDropdown;
    private SellingController sellingController;
    private String sellerEmail;

    private void acceptClicked(ActionEvent actionEvent) throws IOException {

        try {
            String orderString = (String) currentOrdersDropdown.getSelectedItem();
            String[] orderInfoList = orderString.split(", ");
            String orderNumberString = orderInfoList[0];
            String buyerName = orderInfoList[1];
            sellingController.accept(sellerEmail, orderNumberString);
            this.dispose();
            try {
                SellerMain.create(sellerEmail);
            } catch (DoesNotExistException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(null,
                    "Please communicate with " + buyerName + " to give them their food.",
                    "Order accepted.",
                    JOptionPane.PLAIN_MESSAGE);

        } catch (OrderFailed ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "Order failed.", JOptionPane.WARNING_MESSAGE);
        }
    }

    public SellingScreen(SellingController sellingController, OrderDsGateway orderDsGateway, String sellerEmail) {
        this.sellingController = sellingController;
        this.sellerEmail = sellerEmail;
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

        ArrayList<Integer> unfulfilledOrders = orderDsGateway.getUnfulfilledOrders();
        String[] displayArray = new String[unfulfilledOrders.size()];
        for (int i = 0; i < unfulfilledOrders.size(); i++) {
            int orderNumber = unfulfilledOrders.get(i);
            OrderDsModel orderDsModel = orderDsGateway.getOrderInfo(orderNumber);
            String orderString = orderNumber + ", ";
            orderString += orderDsModel.getBuyerName();
            for (String foodItem : orderDsModel.getFoodItems()) {
                orderString += ", " + foodItem;
            }
            displayArray[i] = orderString;
        }

        currentOrdersDropdown = new JComboBox<>(displayArray);
        LabelComboboxPanel ordersPanel = new LabelComboboxPanel(
                new JLabel("Select an order:"), currentOrdersDropdown);

        pnl.add(buttonsPanel);
        pnl.add(ordersPanel);

        this.add(pnl);
        this.setTitle("Selling");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(500, 100);
        this.setVisible(true);
    }
}
