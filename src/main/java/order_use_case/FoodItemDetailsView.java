package order_use_case;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class FoodItemDetailsView extends JFrame {

    public FoodItemDetailsView(String foodItemName,
                               Double foodItemPrice,
                               String[] foodItemAllergens,
                               String[] foodItemIngredients,
                               Integer foodItemCalories,
                               Integer foodItemPopularities,
                               Double foodItemStarAverages,
                               List<String[]> foodItemReviews){

        JPanel pnl = new JPanel(new GridLayout(8, 1));
        pnl.add(new JLabel("Name: " + foodItemName));
        pnl.add(new JLabel("Price: $" + foodItemPrice.toString()));
        pnl.add(new JLabel("Allergens: " + Arrays.toString(foodItemAllergens).replace("[", "").replace("]","")));
        pnl.add(new JLabel("Ingredients: " + Arrays.toString(foodItemIngredients).replace("[", "").replace("]","")));
        pnl.add(new JLabel("Calories: " + foodItemCalories));
        pnl.add(new JLabel("Popularity: " + foodItemPopularities.toString()));

        double starTotal = 0.0;
        for (String[] review: foodItemReviews){
            starTotal += Integer.parseInt(review[1]);
        }

        double starAverage = starTotal/foodItemReviews.size();

        pnl.add(new JLabel("Star Average: " + starAverage));

        JPanel reviewPanel = new JPanel(new GridLayout(foodItemReviews.size() + 1, 1));

        reviewPanel.add(new JLabel("Reviews:"));
        for (String[] review : foodItemReviews){
            reviewPanel.add(new JLabel(review[2]+": " +review[0] + " (" + review[1] + " stars)"));
        }

        pnl.add(reviewPanel);

        //pnl.add(new JLabel("Reviews: " + foodItemReviews.toString().replace("[", "").replace("]","")));

        this.add(pnl);
        this.setTitle("Food Item Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(600, 0);
        this.setVisible(true);
    }

}
