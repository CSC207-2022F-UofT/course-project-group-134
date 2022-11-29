package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a menu of foodItems that a dining hall serves.
 *
 * @author Tejas Balaji
 */
public class Menu{
    /**
     * The list of food items that the menu has
     */
    private final List<FoodItem> foodItems;


    /**
     * Constructor for Menu. Creates a new menu with the given arraylist of food items.
     * @param foodItems An arraylist of food items representing the food items that the Menu has on it.
     */
    public Menu(List<FoodItem> foodItems){
        this.foodItems = foodItems;
    }

    /**
     * Adds a food item to the menu.
     * @param foodItem The food item to add to the menu.
     */
    public void addFoodItem(FoodItem foodItem){
        this.foodItems.add(foodItem);
    }

    /**
     * Gets the food items that the Menu has.
     * @return The ArrayList of food items in the menu that represents the food items on the menu.
     */
    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public List<String> getFoodItemNames(){
        List<String> foodItemNames = new ArrayList<>();
        for (FoodItem foodItem : this.foodItems){
            foodItemNames.add(foodItem.getDescription());
        }
        return foodItemNames;
    }

    public List<Double> getFoodItemPrices(){
        List<Double> foodItemPrices = new ArrayList<>();
        for (FoodItem foodItem : this.foodItems){
            foodItemPrices.add(foodItem.getPrice());
        }
        return foodItemPrices;
    }

    public List<String[]> getFoodItemAllergens(){
        List<String[]> foodItemAllergens = new ArrayList<>();
        for (FoodItem foodItem : this.foodItems){
            foodItemAllergens.add(foodItem.getAllergens());
        }
        return foodItemAllergens;
    }

    public List<String[]> getFoodItemIngredients(){
        List<String[]> foodItemIngredients = new ArrayList<>();
        for (FoodItem foodItem : this.foodItems){
            foodItemIngredients.add(foodItem.getIngredients());
        }
        return foodItemIngredients;
    }

    public List<Integer> getFoodItemCalories(){
        List<Integer> foodItemCalories = new ArrayList<>();
        for (FoodItem foodItem : this.foodItems){
            foodItemCalories.add(foodItem.getCalories());
        }
        return foodItemCalories;
    }

    public ArrayList<Integer> getFoodItemPopularities(){
        ArrayList<Integer> foodItemPopularities = new ArrayList<>();
        for (FoodItem foodItem : this.foodItems){
            foodItemPopularities.add(foodItem.getPopularity());
        }
        return foodItemPopularities;
    }

    public ArrayList<Double> getFoodItemStarAverages(){
        ArrayList<Double> foodItemStarAverages = new ArrayList<>();
        for (FoodItem foodItem : this.foodItems){
            foodItemStarAverages.add(foodItem.getStarAverage());
        }
        return foodItemStarAverages;
    }

    public ArrayList<ArrayList<String>> getFoodItemReviews(){
        ArrayList<ArrayList<String>> foodItemReviews = new ArrayList<>();
        for (FoodItem foodItem : this.foodItems){
            ArrayList<String> foodItemReviewTemp = new ArrayList<>();
            for (Review review : foodItem.getReviews()){
                foodItemReviewTemp.add(review.getReviewString());
            }
            foodItemReviews.add(foodItemReviewTemp);
        }
        return foodItemReviews;
    }

}


