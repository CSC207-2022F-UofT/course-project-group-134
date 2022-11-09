package entities.entityInterfaces;

import entities.Review;

import java.util.ArrayList;

public interface Reviewable {
    ArrayList<Review> getReviews();

    void addReview(Review review);

    double getAverageReviewScore();
}
