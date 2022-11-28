package get_menus_use_case;

import entities.Review;

import java.util.ArrayList;
import java.util.HashMap;

public class GetMenusResponseModel {

    private final ArrayList<String> foodItemNames;
    private final ArrayList<Double> foodItemPrices;
    private final ArrayList<String[]> foodItemAllergens;
    private final ArrayList<String[]> foodItemIngredients;
    private final ArrayList<Integer> foodItemCalories;
    private final ArrayList<Integer> foodItemPopularities;
    private final ArrayList<Double> foodItemStarAverages;
    private final HashMap<String, ArrayList<Review>> foodItemReviews;



    public GetMenusResponseModel(ArrayList<String> foodItemNames, ArrayList<Double> foodItemPrices, ArrayList<String[]> foodItemAllergens, ArrayList<String[]> foodItemIngredients, ArrayList<Integer> foodItemCalories, ArrayList<Integer> foodItemPopularities, ArrayList<Double> foodItemStarAverages, HashMap<String, ArrayList<Review>> foodItemReviews) {
        this.foodItemNames = foodItemNames;
        this.foodItemPrices = foodItemPrices;
        this.foodItemAllergens = foodItemAllergens;
        this.foodItemIngredients = foodItemIngredients;
        this.foodItemCalories = foodItemCalories;
        this.foodItemPopularities = foodItemPopularities;
        this.foodItemStarAverages = foodItemStarAverages;
        this.foodItemReviews = foodItemReviews;
    }



    public ArrayList<String> getFoodItemNames() {
        return foodItemNames;
    }

    public ArrayList<Double> getFoodItemPrices() {
        return foodItemPrices;
    }

    public ArrayList<String[]> getFoodItemAllergens() {
        return foodItemAllergens;
    }

    public ArrayList<String[]> getFoodItemIngredients() {
        return foodItemIngredients;
    }

    public ArrayList<Integer> getFoodItemCalories() {
        return foodItemCalories;
    }

    public ArrayList<Integer> getFoodItemPopularities() {
        return foodItemPopularities;
    }

    public ArrayList<Double> getFoodItemStarAverages() {
        return foodItemStarAverages;
    }

    public HashMap<String, ArrayList<Review>> getFoodItemReviews() {
        return foodItemReviews;
    }
}
