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
