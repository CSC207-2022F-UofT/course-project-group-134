package screens;
import use_cases_mains.BuyerMain;
import use_cases_mains.ReviewMain;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class PreReviewView extends JFrame {

    private JComboBox<String> foodItems;
    private String foodItemsString;
    private String[] foodItemsArray;
    private String residence;
    private JButton reviewButton = new JButton("Place Review");
    private JButton cancelButton = new JButton("Cancel");
    private JPanel buttonPanel = new JPanel(new GridLayout(1,2));
    private JPanel pnl= new JPanel(new GridLayout(2,1));
    private String username;

    public PreReviewView(String foodItemsString, String residence, String username, String email) {
        this.residence = residence;
        this.foodItemsString = foodItemsString;
        this.foodItemsArray = foodItemsString.split(",");
        for (int i= 0; i < this.foodItemsArray.length; i++){
            this.foodItemsArray[i] = this.foodItemsArray[i].split("\\(")[0].strip();
        }
        this.username  = username;
        foodItems = new JComboBox<>(foodItemsArray);

        reviewButton.addActionListener(actionEvent -> {
            this.setVisible(false);
            try {
                ReviewMain.create(username, Objects.requireNonNull(foodItems.getSelectedItem()).toString(),residence, email, this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        cancelButton.addActionListener(actionEvent -> {
            this.dispose();
            try {
                BuyerMain.create(username, email);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        pnl.add(foodItems);
        buttonPanel.add(reviewButton);
        buttonPanel.add(cancelButton);
        pnl.add(buttonPanel);
        this.add(pnl);
        this.setTitle("Review an Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);
    }

}
