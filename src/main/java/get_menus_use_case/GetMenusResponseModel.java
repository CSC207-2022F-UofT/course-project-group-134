package get_menus_use_case;

import java.util.ArrayList;

public class GetMenusResponseModel {

    private ArrayList<String> foodItemNames;
    private ArrayList<Double> foodItemPrices;
    private ArrayList<String[]> foodItemAllergens;
    private ArrayList<String[]> foodItemIngredients;
    private ArrayList<Integer> foodItemCalories;
    private ArrayList<Integer> foodItemPopularities;
    private ArrayList<Double> foodItemStarAverages;
    private ArrayList<ArrayList<String>> foodItemReviews;


    public GetMenusResponseModel(ArrayList<String> foodItemNames, ArrayList<Double> foodItemPrices, ArrayList<String[]> foodItemAllergens, ArrayList<String[]> foodItemIngredients, ArrayList<Integer> foodItemCalories, ArrayList<Integer> foodItemPopularities, ArrayList<Double> foodItemStarAverages, ArrayList<ArrayList<String>> foodItemReviews) {
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

    public ArrayList<ArrayList<String>> getFoodItemReviews() {
        return foodItemReviews;
    }
}
