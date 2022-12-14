package entities;
import java.util.ArrayList;
import java.util.List;

public class MealPlan {
    private final String residence;

    private final List<Residence> associatedDiningHalls;

    private double balance;
    private final List<FoodItem> masterListOfItemsBought;

    public MealPlan(String residence, double balance){
        this.residence = residence;
        this.balance = balance;
        this.masterListOfItemsBought = new ArrayList<>();
        this.associatedDiningHalls = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public List<Residence> getAssociatedDiningHalls() {
        // TODO: implement this by getting the set of dining halls from residence
        return associatedDiningHalls;
    }

    public void updateMealPlan(ArrayList<FoodItem> foodItems, Residence diningHall){
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
        return "Balance: " + String.format("%.2f", this.getBalance()) + " Residence: " + this.getResidence();
    }
}
