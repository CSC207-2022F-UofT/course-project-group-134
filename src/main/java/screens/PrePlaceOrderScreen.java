package screens;

import javax.swing.*;
import java.awt.*;

public class PrePlaceOrderScreen extends JFrame {

    private String userUsername;
    private String userEmail;
    private String residence;
    private String[] foodItems;
    private Integer[] foodItemQuantities;
    Double totalPrice;
    private OrderPreviewScreen orderPreviewScreen;


    public PrePlaceOrderScreen(String userUsername, String userEmail, String residence, String[] foodItems, Integer[] foodItemQuantities, Double totalPrice, OrderPreviewScreen orderPreviewScreen) {
        this.userUsername = userUsername;
        this.userEmail = userEmail;
        this.residence = residence;
        this.foodItems = foodItems;
        this.foodItemQuantities = foodItemQuantities;
        this.totalPrice = totalPrice;
        this.orderPreviewScreen = orderPreviewScreen;

        JPanel pnl = new JPanel(new GridLayout(2, 1));
        pnl.add(new JLabel("Are you sure you want to place this order?"));

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);
        pnl.add(buttonsPanel);

        yesButton.addActionListener(actionEvent ->{
            yesClicked(userUsername, userEmail, residence, foodItems, foodItemQuantities, totalPrice);
        });

        noButton.addActionListener(actionEvent ->{
            noClicked();
        });

        this.add(pnl);
        this.setTitle("Confirm Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(400, 0);
        this.setVisible(true);
    }

    private void yesClicked(String userUsername, String userEmail, String residence, String[] foodItems, Integer[] foodItemQuantities, Double totalPrice){
        this.dispose();
        this.orderPreviewScreen.prePlaceOrderScreenOn = false;
        orderPreviewScreen.placeOrder(userUsername, userEmail, residence, foodItems, foodItemQuantities, totalPrice);
    }

    private void noClicked(){
        this.orderPreviewScreen.prePlaceOrderScreenOn = false;
        this.dispose();
    }

}
