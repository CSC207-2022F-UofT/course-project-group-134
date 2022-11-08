package entities;

public class Review {

    private String reviewString;
    private int rating;
    private DiningHall diningHall;
    private Buyer buyer;

    public Review(String reviewString, int rating, DiningHall diningHall, Buyer buyer){
        this.buyer = buyer;
        this.diningHall = diningHall;
        this.reviewString = reviewString;
        this.rating = rating;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewString() {
        return reviewString;
    }

    public DiningHall getDiningHall() {
        return diningHall;
    }

    public void editReview(String newReview, int newRating){
        this.reviewString = newReview;
        this.rating = newRating;
    }
}
