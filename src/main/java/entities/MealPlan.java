package entities;
import java.util.List;
import java.util.ArrayList;

public class MealPlan {
    private String residence;

    private ArrayList<DiningHall> associatedDiningHalls;

    private double balance;
    private ArrayList<FoodItem> masterListOfItemsBought;

    public MealPlan(String residence, double balance){
        this.residence = residence;
        this.balance = balance;
        this.masterListOfItemsBought = new ArrayList<>();
        this.associatedDiningHalls = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<DiningHall> getAssociatedDiningHalls() {
        // TODO: implement this by getting the set of dining halls from residence
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

    public String getResidence() {
        return residence;
    }

    @Override
    public String toString() {
        return "Balance: " + this.getBalance() + " Residence: " + this.getResidence();
    }
}
