package entities;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a menu of foodItems that a dining hall serves.
 *
 * @author Tejas Balaji
 */
public class Menu{
    /**
     * The list of food items that the menu has
     */
    private ArrayList<FoodItem> foodItems;


    /**
     * Constructor for Menu. Creates a new menu with the given arraylist of food items.
     * @param foodItems An arraylist of food items representing the food items that the Menu has on it.
     */
    public Menu(ArrayList<FoodItem> foodItems){
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
    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }
}
