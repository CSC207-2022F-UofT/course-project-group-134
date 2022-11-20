package screens;

import entities.OrderFactory;
import order_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OrderPreviewScreen extends JFrame {

    public OrderPreviewScreen(String userUsername, String userEmail, String residence, String[] foodItems, Integer[] foodItemQuantities, Double[] foodItemPrices, Double totalPrice){
        JPanel pnl = new JPanel(new GridLayout(foodItems.length + 2, 1));
        for (int i = 0; i < foodItems.length; i ++){
            double foodItemTotalPrice = foodItemPrices[i] * foodItemQuantities[i];
            JLabel temp = new JLabel(foodItems[i] + "(Qty: " + foodItemQuantities[i]+ ") : $" + foodItemTotalPrice);
            pnl.add(temp);
        }
        pnl.add(new JLabel("Total Price: $" + totalPrice));
        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener(actionEvent -> {
            orderClicked(userUsername, userEmail, residence, foodItems, foodItemQuantities, totalPrice);
        });
        pnl.add(orderButton);
        this.add(pnl);
        this.setTitle("Order Preview");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(600, 0);
        this.setVisible(true);
    }

    public void orderClicked(String userUsername, String userEmail, String residence, String[] foodItems, Integer[] foodItemQuantities, Double totalPrice) {
        OrderDsGateway orders;
        try {
            orders = new OrderGateway("./src/main/java/data_storage/orders.csv");
            System.out.println("File Created!");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        // TODO: OrderFactory is ENTIRELY UNUSED because it is completely unnecessary right now
        // Maybe we should add a method that calculates the price for us?
        OrderFactory orderFactory = new OrderFactory();
        OrderOutputBoundary orderPresenter = new OrderPresenter();
        OrderInputBoundary orderInteractor = new OrderInteractor(orders, orderPresenter, orderFactory);
        OrderController orderController = new OrderController(orderInteractor);
        orderController.placeOrder(userUsername, userEmail, residence, foodItems, foodItemQuantities, totalPrice);
    }
}
