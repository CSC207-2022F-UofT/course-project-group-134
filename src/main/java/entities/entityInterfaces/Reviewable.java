package entities.entityInterfaces;

import entities.Review;

import java.util.List;

public interface Reviewable {
    List<Review> getReviews();

    void addReview(Review review);

    double getAverageReviewScore();

    void deleteReview(Review review);
}
