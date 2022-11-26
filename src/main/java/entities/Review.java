package entities;

/**
 * This class represents a review that a buyer has made on an instance of a reviewable class, consisting of an integer
 * rating score and an accompanying string that represents the body of the review.
 *
 * @author Tejas Balaji
 * @author Benjamin Liu
 */
public class Review {

    /**
     * The text body of the review.
     */
    private String reviewString;

    /**
     * The numerical representation of the review, from 0 to 5 inclusive.
     */
    private int rating;

    /**
     * The item name of the review.
     */
    private final String itemName;

    /**
     * The buyer username who is making the review.
     */
    private final String buyerUsername;

    /**
     * Constructor for the review class. This creates a review instance with the specified parameters.
     *
     * @param reviewString The text body of the review.
     * @param rating The numerical score of the review. This is an integer from 0 to 5 inclusive.
     * @param itemName The item name for the review (the object being reviewed).
     * @param buyerUsername The buyer username who is issuing/reviewing the item.
     */
    public Review(String reviewString, int rating, String itemName, String buyerUsername){
        this.buyerUsername = buyerUsername;
        this.itemName = itemName;
        this.reviewString = reviewString;
        this.rating = rating;
    }

    /**
     * Gets the buyer username who issued/reviewed this review.
     * @return The buyer who issued/reviewed this review.
     */
    public String getBuyer() {
        return this.buyerUsername;
    }

    /**
     * Gets the numerical rating score of this review.
     * @return The numerical rating score of this review. This is an integer from 0 to 5 inclusive.
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * Gets the text body of the review.
     * @return The text body of the review, as a string.
     */
    public String getReviewString() {
        return this.reviewString;
    }

    /**
     * Gets the name of the item getting reviewed (in other words, the review recipient).
     * @return The name of the item getting reviewed
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * Changes this review to have new values for reviewString and newRating.
     * @param newReview The new review text body to change to.
     * @param newRating The new numerical rating score to change to.
     */
    public void editReview(String newReview, int newRating){
        this.reviewString = newReview;
        this.rating = newRating;
    }
}
