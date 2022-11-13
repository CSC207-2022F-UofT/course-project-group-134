package entities;
import entities.entityInterfaces.Reviewable;

import java.util.ArrayList;

/**
 * This class represents the subclass of User that is a seller. A seller sells or otherwise provides food to a buyer.
 * This class represents a seller as a user with additional parameters: mealPlan, reviews, and starAverage.
 *
 * @author Vivian Liu
 * @author Tejas Balaji
 * @author Benjamin Liu
 */
public class Seller extends User implements Reviewable{
    /**
     * Seller's meal plan
     */
    private MealPlan mealPlan;
    /**
     * List of reviews that the seller has received.
     */
    private ArrayList<Review> reviews;
    /**
     * Average 'star' value of the reviews that the seller has received.
     */
    double starAverage;

    /**
     * Constructor for a seller.
     * Initializes/creates a seller with the given parameters.
     * starAverage and reviews are initialized as 0 and an empty arraylist, respectively.
     *
     * @param name Username of the seller.
     * @param password Password of the seller.
     * @param mealPlan Meal plan of the seller.
     * @param email email of the seller.
     */
    public Seller(String name, String password, MealPlan mealPlan, String email){
        super(name, password, email);
        this.mealPlan = mealPlan;
        this.starAverage  = 0;
        this.reviews = new ArrayList<>();
    }

    /**
     * Gets the seller's meal plan.
     * @return The meal plan of this seller.
     */
    public MealPlan getMealPlan() {
        return this.mealPlan;
    }

    /**
     * Gets the average star value of all this seller's reviews.
     * @return The average star value of all this seller's reviews.
     */
    public double getAverageReviewScore(){
        return this.starAverage;
    }

    /**
     * This seller "fulfills" the given order (sets the seller of an order to this seller).
     * @param order Order to be fulfilled.
     */
    public void fulfillOrder(Order order){
        order.setSeller(this);
    }

    /**
     * Adds a review to the list of reviews of this seller and updates the average star value accordingly.
     * @param review Review to be added to this seller's list of reviews.
     */
    public void addReview(Review review){
        double totalRating = starAverage * this.reviews.size();
        totalRating += review.getRating();
        this.reviews.add(review);
        this.starAverage = totalRating/this.reviews.size();
    }

    /**
     * Gets the list of reviews of this seller.
     * @return The list of reviews of this seller.
     */
    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

    /**
     * Deletes a particular review from the list of reviews and updates the average star value accordingly.
     * @param review Review to be removed from this seller's list of reviews.
     */
    public void deleteReview(Review review) {
        double totalRating = starAverage * this.reviews.size();
        totalRating -= review.getRating();
        this.reviews.remove(review);
        this.starAverage = totalRating / this.reviews.size();
    }

    @Override
    public String toString() {
        return "Seller: " + this.getUsername() + " " + this.getMealPlan().toString();
    }
}
