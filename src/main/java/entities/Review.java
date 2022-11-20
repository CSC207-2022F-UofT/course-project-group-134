package entities;

import entities.entityInterfaces.Reviewable;

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
     * The reviewable object being reviewed. This is either a food item, a dining hall, or a seller.
     */
    private final Reviewable reviewRecipient;

    /**
     * The buyer who is making the review.
     */
    private final Buyer buyer;

    /**
     * Constructor for the review class. This creates a review instance with the specified parameters.
     *
     * @param reviewString The text body of the review.
     * @param rating The numerical score of the review. This is an integer from 0 to 5 inclusive.
     * @param reviewRecipient The recipient of the review (the object being reviewed).
     * @param buyer The buyer who is issuing/reviewing the reviewRecipient.
     */
    public Review(String reviewString, int rating, Reviewable reviewRecipient, Buyer buyer){
        this.buyer = buyer;
        this.reviewRecipient = reviewRecipient;
        this.reviewString = reviewString;
        this.rating = rating;
    }

    /**
     * Gets the buyer who issued/reviewed this review.
     * @return The buyer who issued/reviewed this review.
     */
    public Buyer getBuyer() {
        return this.buyer;
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
     * Gets the object getting reviewed (in other words, the review recipient).
     * @return The object getting reviewed
     */
    public Reviewable getReviewRecipient() {
        return this.reviewRecipient;
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
