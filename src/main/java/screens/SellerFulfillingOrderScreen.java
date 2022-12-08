package screens;

import chat_use_case.ChatInteractor;
import entities.OrderStatusType;
import order_use_case.DoesNotExistException;
import order_use_case.OrderDsGateway;
import order_use_case.OrderDsModel;
import use_cases_mains.ChatMain;
import use_cases_mains.SellerMain;
import user_access_use_case.SignUpDsGateway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * The screen for fulfilling existing orders.
 * The seller can communicate with the buyer via the chat button.
 * The seller can confirm that the order is fulfilled.
 * The seller can go back to the main selling page by pressing the 'Back' button.
 */
public class SellerFulfillingOrderScreen extends JFrame{

    private boolean chatScreenOpen = false;
    private ChatScreen chatScreen;
    /**
     * Constructor for sellerFulfillingOrder screen. Creates and sets up the JFrame for the screen.
     * @param signUpGateway interface for the signUpGateway
     * @param orderGateway interface for the orderGateway
     * @param sellerEmail seller's email
     * @param price total price for the order
     * @param orderNumber the order number for the order
     * @throws DoesNotExistException
     */
    public SellerFulfillingOrderScreen(SignUpDsGateway signUpGateway, OrderDsGateway orderGateway, String sellerEmail, double price, int orderNumber) throws DoesNotExistException {
        JPanel pnl = new JPanel(new GridLayout(3,1));
        JPanel bottomPanel = new JPanel(new GridLayout(1,3));

        OrderDsModel orderDsModel = orderGateway.getOrderInfo(orderNumber);

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

        for (int j = 0; j < orderDsModel.getFoodItems().length; j ++){
            JLabel foodItemLabel = new JLabel(orderDsModel.getFoodItems()[j] + "(" + orderDsModel.getFoodQuantity()[j] + ")");
            foodItemLabel.setHorizontalAlignment(JLabel.CENTER);
            orderPanel.add(foodItemLabel);
        }

        JButton chatButton = new JButton("Chat");
        chatButton.addActionListener(actionEvent -> {
            try {
                if (!chatScreenOpen) {
                    this.chatScreen = ChatMain.create(sellerEmail, orderDsModel.getBuyerEmail());
                    this.chatScreenOpen = true;
                    chatScreen.addWindowListener(new WindowAdapter(){
                        public void windowClosing(WindowEvent e)
                        {
                            chatScreenOpen = false;
                        }
                    });

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Please close current chat before opening another one.",
                            "Chat Already Open",
                            JOptionPane.WARNING_MESSAGE);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        JButton orderFulfilledButton = new JButton("ORDER FULFILLED!");
        pnl.add(orderPanel);

        OrderStatusType orderStatus = orderGateway.getOrderStatus(orderNumber);
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
                this.setVisible(false);
                new ConfirmScreen(this, "Confirm order finished",
                        "Are you sure you want to confirm the order is finished?") {
                    @Override
                    void yesClicked() {
                        JOptionPane.showMessageDialog(null, "You have successfully confirmed the order is finished.", "Order finished confirmed", JOptionPane.PLAIN_MESSAGE);
                        this.dispose();
                        try {
                            signUpGateway.subtractPrice(sellerEmail, price);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        originalScreen.dispose();
                        try {

                            if (orderStatus == OrderStatusType.BUYER_CONFIRMED) {
                                orderGateway.setOrderStatus(orderNumber, OrderStatusType.FINISHED);
                                SellerMain.create(sellerEmail, orderDsModel.getResidence(), orderDsModel.getSellerName());
                                JOptionPane.showMessageDialog(null,
                                        "Successfully finished order.",
                                        "Order Finished",
                                        JOptionPane.PLAIN_MESSAGE);
                            } else { // order status is ACCEPTED
                                orderGateway.setOrderStatus(orderNumber, OrderStatusType.SELLER_CONFIRMED);
                                SellerMain.create(sellerEmail, orderDsModel.getResidence(), orderDsModel.getSellerName());
                            }
                        } catch (DoesNotExistException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                };
            });
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(actionEvent -> {
            this.dispose();
            this.chatScreen.dispose();
            try {
                SellerMain.create(sellerEmail, orderDsModel.getResidence(), orderDsModel.getSellerName());
            } catch (DoesNotExistException e) {
                throw new RuntimeException(e);
            }
        });

        pnl.add(bottomPanel);
        bottomPanel.add(chatButton);
        bottomPanel.add(backButton);

        this.add(pnl);
        this.setTitle("Fulfilling Order");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLocation(500, 100);
        this.setVisible(true);
    }

}
