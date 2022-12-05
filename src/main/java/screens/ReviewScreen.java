package screens;

import entities.ReviewType;
import use_cases_mains.BuyerMain;
import review_use_case.ReviewController;
import review_use_case.ReviewFailed;
import review_use_case.ReviewResponseModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

// Frameworks/Drivers layer

/**
 * The screen for creating reviews and ratings for specific food items that were previously purchased.
 * The reviewer can write a review in the provided text box.
 * The reviewer can choose a rating [1,5] from the dropbox.
 * The reviewer can go back to the buyer main screen by pressing the 'Back' button.
 */

public class ReviewScreen extends JFrame {
    private final JTextField reviewInput = new JTextField(15);
    private final JComboBox<String> ratingsInput;
    private final ReviewController reviewController;

    String username, itemName, diningHall, email;

    /**
     * This method is called when the post button is clicked.
     * Data gets sent to the controller.
     * This method tells you if review is successful.
     * @throws IOException
     */
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

    /**
     * Constructor for review screen.
     * JFrame is created and set up.
     * @param reviewController
     * @param username
     * @param itemName
     * @param diningHall
     */
    public ReviewScreen(ReviewController reviewController,
                        String username, String itemName, String diningHall, String email){
        this.reviewController = reviewController;
        this.email = email;
        this.username = username;
        this.itemName = itemName;
        this.diningHall = diningHall;

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
