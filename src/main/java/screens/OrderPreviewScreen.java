package screens;

import entities.OrderFactory;
import order_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OrderPreviewScreen extends JFrame {
    private final OrderView orderView;

    public OrderPreviewScreen(OrderView orderView, String userUsername, String userEmail, String residence, String[] foodItems, Integer[] foodItemQuantities, Double[] foodItemPrices, Double totalPrice){
        this.orderView = orderView;
        JPanel pnl = new JPanel(new GridLayout(foodItems.length + 2, 1));
        for (int i = 0; i < foodItems.length; i ++){
            double foodItemTotalPrice = foodItemPrices[i] * foodItemQuantities[i];
            JLabel temp = new JLabel(foodItems[i] + "(Qty: " + foodItemQuantities[i]+ ") : $" + String.format("%.2f", foodItemTotalPrice));
            pnl.add(temp);
        }
        pnl.add(new JLabel("Total Price: $" + String.format("%.2f", totalPrice)));
        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener(actionEvent -> {
            placeOrder(userUsername, userEmail, residence, foodItems, foodItemQuantities, totalPrice);

        });
        pnl.add(orderButton);
        this.add(pnl);
        this.setTitle("Order Preview");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(600, 0);
        this.setVisible(true);
    }

    public void placeOrder(String userUsername, String userEmail, String residence, String[] foodItems, Integer[] foodItemQuantities, Double totalPrice) {
        OrderDsGateway orders;
        try {
            orders = new OrderGateway("./src/main/java/data_storage/orders.csv");
            //System.out.println("File Created!");
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

        JOptionPane.showMessageDialog(null, "Order has been sent", "Order Sucess", JOptionPane.PLAIN_MESSAGE);
        this.dispose();
        orderView.dispose();
        try {
            BuyerMain.create(userUsername, userEmail);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "Something inted.", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void dispose() {
        this.orderView.orderPreviewClosed = true;
        super.dispose();
    }
}
