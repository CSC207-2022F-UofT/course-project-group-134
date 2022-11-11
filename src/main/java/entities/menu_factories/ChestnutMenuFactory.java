package entities.menu_factories;

import entities.FoodItem;
import entities.Menu;

import java.util.ArrayList;

public class ChestnutMenuFactory {
    public Menu createMenu(){
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("Cheese Omlette",
                new String[]{"Egg", "Cheese"},
                new String[]{"Egg", "Cheese", "Onions, Tomatoes"},
                100,
                7.99));
        foodItems.add(new FoodItem("Chicken Kabob",
                new String[]{},
                new String[]{"Chicken", "Potatoes"},
                100,
                9.00));
        foodItems.add(new FoodItem("Veggie Quesadilla",
                new String[]{"Cheese", "Wheat"},
                new String[]{"Tomatoes", "Onions", "Cheese, Wheat base"},
                100,
                6.00));
        foodItems.add(new FoodItem("Beef Burrito",
                new String[]{"Cheese"},
                new String[]{"Beef", "Black Beans", "Cheese"},
                100,
                8.99));
        foodItems.add(new FoodItem("Pepperoni Pizza",
                new String[]{"Cheese", "Wheat"},
                new String[]{"Pepperoni", "Cheese", "Wheat Pizza Base", "Tomato sauce"},
                100,
                4.99));
        return new Menu(foodItems);
    }
}
