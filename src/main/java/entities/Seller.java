package entities;
import entities.entityInterfaces.Reviewable;

import java.util.ArrayList;

public class Seller extends User implements Reviewable{

    private MealPlan mealPlan;
    private ArrayList<Review> reviews;
    double starAverage;
    public Seller(String name, String password, MealPlan mealPlan, String email){
        super(name, password, email);
        this.mealPlan = mealPlan;
        this.starAverage  = 0;
        this.reviews = new ArrayList<>();
    }

    public MealPlan getMealPlan() {
        return this.mealPlan;
    }

    public double getAverageReviewScore(){
        return this.starAverage;
    }

    public void fulfillOrder(Order order){
        order.setSeller(this);
    }

    public void addReview(Review review){
        double totalRating = starAverage * this.reviews.size();
        totalRating += review.getRating();
        this.reviews.add(review);
        this.starAverage = totalRating/this.reviews.size();
    }

    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

    public void deleteReview(Review review) {
        double totalRating = starAverage * this.reviews.size();
        totalRating -= review.getRating();
        this.reviews.remove(review);
        this.starAverage = totalRating / this.reviews.size();
    }
}
