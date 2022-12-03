package review_use_case;

// Use case layer

/**
 * ReviewResponseModel contains the reviews and ratings of a food item
 * when review is successful.
 */
public class ReviewResponseModel {
    private String review;
    private int rating;

    public ReviewResponseModel(String review, int rating){
        this.review = review;
        this.rating = rating;
    }

    public String getReview() {
        return this.review;
    }

    public int getRating() {
        return this.rating;
    }
}
