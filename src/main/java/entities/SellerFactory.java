package entities;

public class SellerFactory {
    public Seller create(String name, String password, MealPlan mealPlan) {
        return new Seller(name, password, mealPlan);
    }
}
