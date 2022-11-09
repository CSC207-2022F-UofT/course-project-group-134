package review_use_case;

import entities.Review;
import entities.entityInterfaces.Reviewable;

public class ReviewInteractor {

    public void placeReview(Reviewable reviewable, Review review) {
        reviewable.addReview(review);
    }
}