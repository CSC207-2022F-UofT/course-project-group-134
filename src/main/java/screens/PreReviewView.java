package screens;
import review_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class PreReviewView extends JFrame {

    private JComboBox<String> foodItems;
    private String foodItemsString;
    private String[] foodItemsArray;
    private String residence;
    private JButton reviewButton = new JButton("Review");
    private JPanel pnl= new JPanel(new GridLayout(2,1));
    private String username;

    public PreReviewView(String foodItemsString, String residence, String username) {
        this.residence = residence;
        this.foodItemsString = foodItemsString;
        this.foodItemsArray = foodItemsString.split(",");
        for (int i= 0; i < this.foodItemsArray.length; i++){
            this.foodItemsArray[i] = this.foodItemsArray[i].split("\\(")[0].strip();
        }
        this.username  = username;
        foodItems = new JComboBox<>(foodItemsArray);

        reviewButton.addActionListener(actionEvent -> {
            try {
                ReviewMain.create(username, Objects.requireNonNull(foodItems.getSelectedItem()).toString(),residence);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        pnl.add(foodItems);
        pnl.add(reviewButton);
        this.add(pnl);
        this.setTitle("Review an Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(600, 0);
        this.setVisible(true);
    }

}
