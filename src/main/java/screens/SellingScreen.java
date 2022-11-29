package screens;

import order_use_case.DoesNotExistException;
import order_use_case.OrderDsGateway;
import order_use_case.OrderDsModel;
import order_use_case.OrderFailed;
import selling_use_case.SellerMain;
import selling_use_case.SellingController;
import user_access_use_case.SignUpDsGateway;

import javax.swing.*;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

public class SellingScreen extends JFrame {

    private JComboBox<String> currentOrdersDropdown;
    private final SellingController sellingController;
    private final String sellerEmail;

    private final String sellerResidence;

    private final SignUpDsGateway signUpGateway;
    private void acceptClicked() throws IOException {

        try {
            String orderString = (String) currentOrdersDropdown.getSelectedItem();
            String[] orderInfoList = orderString.split(", ");
            String orderNumberString = orderInfoList[0];
            double price = Double.parseDouble(orderInfoList[1].substring(1));
            double balance = signUpGateway.getRequestModelFromEmail(sellerEmail).getMealPlanBalance();
            if (price > balance){
                JOptionPane.showMessageDialog(null,
                        "Please choose another order.", "Insufficient balance", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String buyerName = orderInfoList[2];

            sellingController.accept(sellerEmail, orderNumberString);
            this.dispose();
            try {
                SellerMain.create(sellerEmail, sellerResidence);
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

    public SellingScreen(SellingController sellingController, SignUpDsGateway signUpGateway, OrderDsGateway orderDsGateway, String sellerEmail, String
                         sellerResidence) {
        this.sellingController = sellingController;
        this.sellerEmail = sellerEmail;
        this.sellerResidence = sellerResidence;
        this.signUpGateway = signUpGateway;
        JPanel pnl = new JPanel(new GridLayout(5,1));
        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
        JButton acceptButton = new JButton("Accept Order");
        acceptButton.addActionListener(actionEvent -> {
            try {
                acceptClicked();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // 'Log-out' by going back to the welcome screen
        JButton logOutButton = new JButton("Log out");
        logOutButton.addActionListener(actionEvent -> {
            this.dispose();
            try {
                new WelcomeScreen();
                JOptionPane.showMessageDialog(null,
                        "Successfully logged out.",
                        "Logout succeeded",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        JButton pastOrderButton = new JButton("Past Orders");
        pastOrderButton.addActionListener(actionEvent -> {
            try {
                new SellerPastOrdersScreen(orderDsGateway,sellerEmail);
            } catch (DoesNotExistException e) {
                throw new RuntimeException(e);
            }
        });

        buttonsPanel.add(logOutButton);
        buttonsPanel.add(acceptButton);
        buttonsPanel.add(pastOrderButton);

        pnl.add(buttonsPanel);

        List<Integer> unfulfilledOrders = orderDsGateway.getUnfulfilledOrders(sellerResidence);
        String[] displayArray = new String[unfulfilledOrders.size()];
        for (int i = 0; i < unfulfilledOrders.size(); i++) {
            int orderNumber = unfulfilledOrders.get(i);
            OrderDsModel orderDsModel = orderDsGateway.getOrderInfo(orderNumber);
            String orderString = orderNumber + ", ";
            orderString += "$" + orderDsModel.getPrice() + ", ";
            orderString += orderDsModel.getBuyerName();

            for (int j = 0; j < orderDsModel.getFoodItems().length; j++){
                orderString += ", " + orderDsModel.getFoodItems()[j] + "(" + orderDsModel.getFoodQuantity()[j] + ")";
            }
            displayArray[i] = orderString;
        }

        if (unfulfilledOrders.size() > 0) {
            currentOrdersDropdown = new JComboBox<>(displayArray);
            LabelComboboxPanel ordersPanel = new LabelComboboxPanel(
                    new JLabel("Select an order:"), currentOrdersDropdown);
            pnl.add(ordersPanel);
        } else {
            JLabel noOrderLabel = new JLabel("There is currently no orders for this residence. ");
            noOrderLabel.setHorizontalAlignment(JLabel.CENTER);
            pnl.add(noOrderLabel);
        }


        this.add(pnl);
        this.setTitle("Selling");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(500, 100);
        this.setVisible(true);
    }
}
