package review_use_case;

import entities.ReviewType;

public class ReviewRequestModel {
    private String reviewString;
    private int ratings;
    private String dininghall;
    private String username;
    private ReviewType reviewType;

    public ReviewRequestModel(String reviewString, int ratings, String dininghall, ReviewType reviewType, String username){
        this.reviewString = reviewString;
        this.ratings = ratings;
        this.dininghall = dininghall;
        this.reviewType = reviewType;
        this.username = username;
    }

    public String getReviewString(){
        return this.reviewString;
    }

    public int getRatings(){
        return this.ratings;
    }

    public String getDininghall(){
        return this.dininghall;
    }

    public ReviewType getReviewType(){
        return this.reviewType;
    }

    public String getUsername(){
        return this.username;
    }
}
