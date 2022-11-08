package entities;

import java.util.ArrayList;

public class Seller extends User implements Reviewable{

    private MealPlan mealPlan;
    private ArrayList<Review> reviews;
    double starAverage;
    public Seller(String name, String password, MealPlan mealPlan){
        super(name, password);
        this.mealPlan = mealPlan;
    }

    public void fulfillOrder(Order order){
        order.setSeller(this);
    }

    public void getReview(Review review){
        this.reviews.add(review);
    }


}
