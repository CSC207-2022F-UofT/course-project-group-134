package screens;

import javax.swing.*;
import java.awt.*;

public class OrderPreviewScreen extends JFrame {
    private JPanel pnl;
    private JButton orderButton = new JButton("Place Order");

    public OrderPreviewScreen(String userUsername, String userEmail, String residence, String[] foodItems, Integer[] foodItemQuantities, Double[] foodItemPrices, Double totalPrice){
        pnl = new JPanel(new GridLayout(foodItems.length + 2, 1));
        for (int i = 0; i < foodItems.length; i ++){
            double foodItemTotalPrice = foodItemPrices[i] * foodItemQuantities[i];
            JLabel temp = new JLabel(foodItems[i] + "(Qty: " + foodItemQuantities[i]+ ") : $" + foodItemTotalPrice);
            pnl.add(temp);
        }
        pnl.add(new JLabel("Total Price: $" + totalPrice));
        pnl.add(orderButton);
        this.add(pnl);
        this.setTitle("Order Preview");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(600, 0);
        this.setVisible(true);
    }
}
