package review_use_case;

import entities.Review;
import entities.entityInterfaces.Reviewable;

public class ReviewInteractor {

    public void placeReview(Reviewable reviewableItem, Review review) {
        reviewableItem.addReview(review);
    }

    public void deleteReview(Reviewable reviewableItem, Review review) {
        reviewableItem.deleteReview(review);
    }
}