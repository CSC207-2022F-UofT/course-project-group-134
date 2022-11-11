package entities;

import entities.entityInterfaces.Reviewable;

import java.util.ArrayList;

public class FoodItem implements Reviewable {
    private String description;
    private String[] allergens;
    private String[] ingredients;
    private int calories;
    private double price;
    private int popularity;

    private double starAverage;

    private ArrayList<Review> reviews;

    public FoodItem(String description, String[] allergens, String[] ingredients, int calories, double price){
        this.description = description;
        this.allergens = allergens;
        this.ingredients = ingredients;
        this.calories = calories;
        this.price = price;
        this.popularity = 0;
        this.starAverage = 0;
        this.reviews = new ArrayList<>();
    }

    public String getDescription(){
        return this.description;
    }

    public String[] getAllergens(){
        return this.allergens;
    }

    public String[] getIngredients(){
        return this.ingredients;
    }

    public int getCalories(){
        return this.calories;
    }

    public double getPrice(){
        return this.price;
    }

    public int getPopularity(){
        return popularity;
    }


    public ArrayList<Review> getReviews() {
        return reviews;
    }


    public void addReview(Review review) {
        double totalRating = starAverage * this.reviews.size();
        totalRating += review.getRating();
        this.reviews.add(review);
        this.starAverage = totalRating / this.reviews.size();
    }


    public double getAverageReviewScore() {
        return starAverage;
    }

    public void deleteReview(Review review) {
        double totalRating = starAverage * this.reviews.size();
        totalRating -= review.getRating();
        this.reviews.remove(review);
        this.starAverage = totalRating / this.reviews.size();
    }
}
