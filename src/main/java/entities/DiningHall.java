package entities;

import entities.entityInterfaces.Reviewable;

import java.util.List;
import java.util.ArrayList;

public class DiningHall implements Reviewable {

    private final String location;
    private final String name;
    private final Menu menu;
    private final ArrayList<Review> reviews;
    double starAverage;

    public DiningHall(String location, String name, Menu menu) {
        this.location = location;
        this.menu = menu;
        this.name = name;
        this.reviews = new ArrayList<>();
        this.starAverage = 0;
    }

    public void addReview(Review review) {
        double totalRating = starAverage * this.reviews.size();
        totalRating += review.getRating();
        this.reviews.add(review);
        this.starAverage = totalRating / this.reviews.size();
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public Menu getMenu() {
        return menu;
    }

    public double getAverageReviewScore() {
        return starAverage;
    }
}
