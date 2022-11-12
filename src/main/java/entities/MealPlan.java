package entities;
import java.util.List;
import java.util.ArrayList;

public class MealPlan {
    private ArrayList<DiningHall> associatedDiningHalls;
    private Seller mealPlanUser;
    private double balance;
    private ArrayList<FoodItem> masterListOfItemsBought;


    public MealPlan() {
        // TODO shouldnt be blank.
    }

    public MealPlan(Seller mealPlanUser, ArrayList<DiningHall> associatedDiningHalls, double balance){
        this.associatedDiningHalls = associatedDiningHalls;
        this.mealPlanUser = mealPlanUser;
        this.balance = balance;
        this.masterListOfItemsBought = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<DiningHall> getAssociatedDiningHalls() {
        return associatedDiningHalls;
    }

    public void updateMealPlan(ArrayList<FoodItem> foodItems, DiningHall diningHall){
        if (!this.associatedDiningHalls.contains(diningHall)){
            return;
        }
        for (FoodItem foodItem: foodItems){
            if (!diningHall.getMenu().getFoodItems().contains(foodItem)){
                return;
            }
        }
        this.masterListOfItemsBought.addAll(foodItems);
        for (FoodItem foodItem: foodItems){
            this.balance -= foodItem.getPrice();
        }
    }

}
