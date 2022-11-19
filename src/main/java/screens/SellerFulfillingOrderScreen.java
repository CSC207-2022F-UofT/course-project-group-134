package screens;

import entities.OrderStatusType;
import order_use_case.DoesNotExistException;
import order_use_case.OrderDsGateway;
import order_use_case.OrderDsModel;
import selling_use_case.SellerMain;
import selling_use_case.SellingController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SellerFulfillingOrderScreen extends JFrame{

    public SellerFulfillingOrderScreen(SellingController sellingController, OrderDsGateway gateway, String sellerEmail,
                                       String sellerResidence) throws DoesNotExistException {
        JPanel pnl = new JPanel(new GridLayout(3,1));

        int orderNumber = gateway.getOrderNumberFromSellerEmail(sellerEmail);
        OrderDsModel orderDsModel = gateway.getOrderInfo(orderNumber);

        JPanel orderPanel = new JPanel(new GridLayout(0,1));
        JLabel orderNumberLabel = new JLabel("Order #: " + orderNumber);
        orderNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        orderPanel.add(orderNumberLabel);

        JLabel buyerNameLabel = (new JLabel("Buyer: " + orderDsModel.getBuyerName()));
        buyerNameLabel.setHorizontalAlignment(JLabel.CENTER);
        orderPanel.add(buyerNameLabel);

        JLabel foodItemsLabel = new JLabel("Food Items:");
        foodItemsLabel.setHorizontalAlignment(JLabel.CENTER);
        orderPanel.add(foodItemsLabel);

        for (String foodItem : orderDsModel.getFoodItems()) {
            JLabel foodItemLabel = new JLabel(foodItem);
            foodItemLabel.setHorizontalAlignment(JLabel.CENTER);
            orderPanel.add(foodItemLabel);
        }

        JButton chatButton = new JButton("Chat");
        JButton orderFulfilledButton = new JButton("ORDER FULFILLED!");
        pnl.add(orderPanel);
        pnl.add(chatButton);

        OrderStatusType orderStatus = gateway.getOrderStatus(orderNumber);
        if (orderStatus == OrderStatusType.SELLER_CONFIRMED) {
            JPanel confirmedPanel = new JPanel(new GridLayout(0,1));
            JLabel waitingForBuyerLabel1 = new JLabel("Waiting for " + orderDsModel.getBuyerName() +
                    " to confirm that order is fulfilled.");
            JLabel waitingForBuyerLabel2 = new JLabel("Please communicate in chat if needed.");
            waitingForBuyerLabel1.setHorizontalAlignment(JLabel.CENTER);
            waitingForBuyerLabel2.setHorizontalAlignment(JLabel.CENTER);
            confirmedPanel.add(waitingForBuyerLabel1);
            confirmedPanel.add(waitingForBuyerLabel2);
            pnl.add(confirmedPanel);
        } else {
            pnl.add(orderFulfilledButton);
            orderFulfilledButton.addActionListener(actionEvent -> {
                this.dispose();
                try {
                    if (orderStatus == OrderStatusType.BUYER_CONFIRMED) {
                        gateway.setOrderStatus(orderNumber, OrderStatusType.FINISHED);
                        SellerMain.create(sellerEmail, orderDsModel.getResidence());
                        JOptionPane.showMessageDialog(null,
                                "Successfully finished order.",
                                "Order Finished",
                                JOptionPane.PLAIN_MESSAGE);
                    } else { // order status is ACCEPTED
                        gateway.setOrderStatus(orderNumber, OrderStatusType.SELLER_CONFIRMED);
                        SellerMain.create(sellerEmail, orderDsModel.getResidence());
                    }
                } catch (DoesNotExistException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        this.add(pnl);
        this.setTitle("Fulfilling Order");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(500, 100);
        this.setVisible(true);
    }

}
