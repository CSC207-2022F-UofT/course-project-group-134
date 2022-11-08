package entities;
import java.util.List;
import java.util.ArrayList;

public class Menu{
    private ArrayList<FoodItem> foodItems;


    public Menu(ArrayList<FoodItem> foodItems){
        this.foodItems = foodItems;
    }

    public void addFoodItem(FoodItem foodItem){
        this.foodItems.add(foodItem);
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }
}
