package entities;

import entities.entityInterfaces.Reviewable;

import java.util.ArrayList;

/**
 * This class represents a FoodItem that a dining hall may have on its menu.
 *
 * @author Catherine Lacis
 * @author Benjamin Liu
 */
public class FoodItem implements Reviewable {
    /**
     * Description of the food item/what it is.
     */
    private String description;
    /**
     * Potential allergens that this food item may have.
     */
    private String[] allergens;
    /**
     * Ingredients of the food item.
     */
    private String[] ingredients;
    /**
     * Approximate number of calories in one serving of the food item.
     */
    private int calories;
    /**
     * Price in Canadian Dollars of the food item.
     */
    private double price;
    /**
     * Popularity of the food item, represented by an integer number of times the item has been ordered.
     */
    private int popularity;

    /**
     * The average of all the numerical ratings of the food item.
     */
    private double starAverage;

    /**
     * The collection of reviews of this food item.
     */
    private ArrayList<Review> reviews;

    /**
     * The constructor for foodItem. This creates a food item with the given parameters. popularity and starAverage are
     * initialized as zero, and reviews is initialized as an empty arraylist.
     *
     * @param description The description of the food item.
     * @param allergens The potential allergens of the food item, represented by a list of strings.
     * @param ingredients The ingredients of the food item, represented by a list of strings.
     * @param calories The approximate number of (kilo)calories contained in one serving of the food item.
     * @param price The price of the food item in Canadian Dollars, represented by a double.
     */
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


    /**
     * Gets the description of the food item.
     * @return the description of the food item, as a String.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Gets the allergens of the food item.
     * @return The list of potential allergens (represented by a string) contained within the food item
     */
    public String[] getAllergens(){
        return this.allergens;
    }

    /**
     * Gets the ingredients of the food item.
     * @return The list of ingredients (represented by a string) contained within the food item
     */
    public String[] getIngredients(){
        return this.ingredients;
    }

    /**
     * Gets the number of calories in the food item (approximate)
     * @return the approximate integer number of (kilo)calories contained within the food item.
     */
    public int getCalories(){
        return this.calories;
    }

    /**
     * Gets the price of the food item.
     * @return The price of the food item in Canadian dollars, represented by a double to account for the number of
     * cents.
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Gets the popularity(number of times this item has been ordered) of the food item.
     * @return The popularity(number of times this item has been ordered) of the food item.
     */
    public int getPopularity(){
        return popularity;
    }

    /**
     * Gets the collection of reviews for this food item.
     * @return The arraylist containing the reviews with this item as the recipient.
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Increases the popularity of this food item by an integer n.
     * @param n The number of new orders that have ordered this food item.
     */
    public void increasePopularity(int n){
        this.popularity += n;
    }


    /**
     * Adds the given review to the collection of reviews of this item. Updates the new starAverage accordingly.
     * @param review The review to be added to the collection of reviews of this item.
     */
    public void addReview(Review review) {
        double totalRating = starAverage * this.reviews.size();
        totalRating += review.getRating();
        this.reviews.add(review);
        this.starAverage = totalRating / this.reviews.size();
    }

    /**
     * Gets the average review score of this item.
     * @return The average review score of this item.
     */
    public double getAverageReviewScore() {
        return starAverage;
    }

    /**
     * Removes the given item from the collection of reviews of this item. Updates the new starAverage accordingly.
     * @param review The review to be removed.
     */
    public void deleteReview(Review review) {
        double totalRating = starAverage * this.reviews.size();
        totalRating -= review.getRating();
        this.reviews.remove(review);
        this.starAverage = totalRating / this.reviews.size();
    }
}
