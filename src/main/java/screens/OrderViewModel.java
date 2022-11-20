package screens;

import java.util.ArrayList;

public interface OrderViewModel {
    public void showMenus(ArrayList<String> foodItemNames, ArrayList<Double> foodItemPrices, ArrayList<String[]> foodItemAllergens, ArrayList<String[]> foodItemIngredients, ArrayList<Integer> foodItemCalories, ArrayList<Integer> foodItemPopularities, ArrayList<Double> foodItemStarAverages, ArrayList<ArrayList<String>> foodItemReviews);
}
