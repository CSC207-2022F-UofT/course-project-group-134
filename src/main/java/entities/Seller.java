package entities;

public class Seller extends User implements Reviewable{

    private MealPlan mealPlan;
    public Seller(String name, String password, MealPlan mealPlan){
        super(name, password);
        this.mealPlan = mealPlan;
    }

    public void fulfillOrder(Order order){
    }


}
