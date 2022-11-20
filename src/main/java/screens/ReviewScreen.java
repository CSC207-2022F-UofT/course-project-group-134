package screens;

import entities.ReviewType;
import review_use_case.ReviewController;
import review_use_case.ReviewFailed;
import review_use_case.ReviewResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class ReviewScreen extends JFrame {
    private JTextField reviewInput = new JTextField(15);
    private JComboBox<String> ratingsInput;
    private JTextField sellerInput = new JTextField(15);
    private ReviewController reviewController;

    String username, itemName, diningHall;

    private void reviewClicked(ActionEvent actionEvent) throws IOException {
        try {
            ReviewResponseModel response = reviewController.create(reviewInput.getText(), Integer.parseInt(Objects.requireNonNull(ratingsInput.getSelectedItem()).toString()),
                    username, itemName, diningHall);

            this.dispose();
            // TODO send them back to buyer screen.
            JOptionPane.showMessageDialog(null,
                    "Enter review: " + response.getReview() + ".",
                    "Review posted successfully.",
                    JOptionPane.PLAIN_MESSAGE);
        }
        catch (ReviewFailed ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "Review failed to post.", JOptionPane.WARNING_MESSAGE);
        }

    }

    public ReviewScreen(ReviewController reviewController,
                        String username, String itemName, String diningHall){
        this.username = username;
        this.itemName = itemName;
        this.diningHall = diningHall;
        this.reviewController = reviewController;
        JPanel pnl = new JPanel(new GridLayout(8,1));
        LabelTextPanel reviewInfo = new LabelTextPanel(
                new JLabel("Enter review"),reviewInput);
        String[] ratingsPossible = {"1","2","3","4","5"};
        ratingsInput = new JComboBox<>(ratingsPossible);
        LabelComboboxPanel ratingsInfo = new LabelComboboxPanel(
                new JLabel("Enter rating"), ratingsInput);

        // ReviewType dropdown

        ReviewType[] reviewStates = ReviewType.values();
        String[] reviewTypeList = new String[reviewStates.length];

        for (int i = 0; i < reviewStates.length; i++){
            reviewTypeList[i] = reviewStates[i].toString();
        }

        pnl.add(reviewInfo);
        pnl.add(ratingsInfo);

        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));

        JButton reviewButton = new JButton("Post");
        reviewButton.addActionListener(actionEvent ->{
            try {
                reviewClicked(actionEvent);
            } catch (IOException e ){
                throw new RuntimeException(e);
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(actionEvent -> {
            this.dispose();
            // TODO
        });
        buttonsPanel.add(backButton);
        buttonsPanel.add(reviewButton);

        pnl.add(buttonsPanel);
        this.add(pnl);
        this.setTitle("Review");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);


    }

}
