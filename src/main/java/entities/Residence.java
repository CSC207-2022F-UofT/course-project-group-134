package entities;

import entities.entityInterfaces.Reviewable;

import java.util.ArrayList;
import java.util.List;

public class Residence implements Reviewable {

    private final String location;
    private final String name;
    private final Menu menu;
    private final List<Review> reviews;
    double starAverage;

    public Residence(String location, String name, Menu menu) {
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
    public String getName(){return name;}

    public List<Review> getReviews() {
        return reviews;
    }

    public Menu getMenu() {
        return menu;
    }

    public double getAverageReviewScore() {
        return starAverage;
    }

    public void deleteReview(Review review) {
        double totalRating = starAverage * this.reviews.size();
        totalRating -= review.getRating();
        this.reviews.remove(review);
        this.starAverage = totalRating / this.reviews.size();
    }

    public String getLocation() {
        return location;
    }
}


