package get_menus_use_case;

import entities.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetMenusResponseModel {

    private final List<String> foodItemNames;
    private final List<Double> foodItemPrices;
    private final List<String[]> foodItemAllergens;
    private final List<String[]> foodItemIngredients;
    private final List<Integer> foodItemCalories;
    private final List<Integer> foodItemPopularities;
    private final List<Double> foodItemStarAverages;
    private final HashMap<String, List<Review>> foodItemReviews;


    /**
     *
     * @param foodItemNames an ArrayList of food items
     * @param foodItemPrices an ArrayList of food item prices
     * @param foodItemAllergens an ArrayList of food item allergens (the allergens for each food item is a String array)
     * @param foodItemIngredients an ArrayList of food item ingredients (the ingredients for each food item is a String array)
     * @param foodItemCalories an ArrayList of food item prices
     * @param foodItemPopularities an ArrayList of food item popularities
     * @param foodItemStarAverages an ArrayList of food item star averages
     * @param foodItemReviews an HashMap of food item reviews (for each food item, there is an ArrayList of String arrays.
     *                        Each string array has the review, the name of the user who gave the review,
     *                        and the star rating of the review)
     */

    public GetMenusResponseModel(List<String> foodItemNames, List<Double> foodItemPrices, List<String[]> foodItemAllergens, List<String[]> foodItemIngredients, List<Integer> foodItemCalories, List<Integer> foodItemPopularities, List<Double> foodItemStarAverages, HashMap<String, List<Review>> foodItemReviews) {
        this.foodItemNames = foodItemNames;
        this.foodItemPrices = foodItemPrices;
        this.foodItemAllergens = foodItemAllergens;
        this.foodItemIngredients = foodItemIngredients;
        this.foodItemCalories = foodItemCalories;
        this.foodItemPopularities = foodItemPopularities;
        this.foodItemStarAverages = foodItemStarAverages;
        this.foodItemReviews = foodItemReviews;
    }



    public List<String> getFoodItemNames() {
        return foodItemNames;
    }

    public List<Double> getFoodItemPrices() {
        return foodItemPrices;
    }

    public List<String[]> getFoodItemAllergens() {
        return foodItemAllergens;
    }

    public List<String[]> getFoodItemIngredients() {
        return foodItemIngredients;
    }

    public List<Integer> getFoodItemCalories() {
        return foodItemCalories;
    }

    public List<Integer> getFoodItemPopularities() {
        return foodItemPopularities;
    }

    public List<Double> getFoodItemStarAverages() {
        return foodItemStarAverages;
    }

    public HashMap<String, List<Review>> getFoodItemReviews() {
        return foodItemReviews;
    }
}
