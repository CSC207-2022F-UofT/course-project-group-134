package screens;

import entities.ReviewType;
import order_use_case.BuyerMain;
import review_use_case.ReviewController;
import review_use_case.ReviewFailed;
import review_use_case.ReviewResponseModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ReviewScreen extends JFrame {
    private final JTextField reviewInput = new JTextField(15);
    private final JComboBox<String> ratingsInput;
    private final ReviewController reviewController;

    String username, itemName, diningHall, email;

    private void reviewClicked() throws IOException {
        try {
            Object inputDropdown = ratingsInput.getSelectedItem();
            assert inputDropdown != null;
            ReviewResponseModel response = reviewController.create(reviewInput.getText(), Integer.parseInt(Objects.requireNonNull(ratingsInput.getSelectedItem()).toString()),
                    username, itemName, diningHall, email);

            this.dispose();
            BuyerMain.create(username, email);
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
        this.reviewController = reviewController;
        JPanel pnl = new JPanel(new GridLayout(8,1));

        LabelTextPanel reviewInfo = new LabelTextPanel(
                new JLabel("Enter review"),reviewInput);

        ReviewType[] reviewStates = ReviewType.values();
        String[] reviewTypeList = new String[reviewStates.length];

        for (int i = 0; i < reviewStates.length; i++){
            reviewTypeList[i] = reviewStates[i].toString();
        }

        // Ratings dropdown
        String[] ratingsPossible = {"1","2","3","4","5"};
        ratingsInput = new JComboBox<>(ratingsPossible);
        LabelComboboxPanel ratingsInfo = new LabelComboboxPanel(
                new JLabel("Enter rating"), ratingsInput);

        pnl.add(reviewInfo);
        pnl.add(ratingsInfo);



        JPanel buttonsPanel = new JPanel(new GridLayout(1,2));

        JButton reviewButton = new JButton("Post");
        reviewButton.addActionListener(actionEvent ->{
            try {
                reviewClicked();
            } catch (IOException e ){
                throw new RuntimeException(e);
            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(actionEvent -> {
            this.dispose();
            try {
                BuyerMain.create(username, email);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            });
        buttonsPanel.add(backButton);
        buttonsPanel.add(reviewButton);

        pnl.add(buttonsPanel);
        this.add(pnl);
        this.setTitle("Review");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(700, 100);
        this.setVisible(true);


    }

}
