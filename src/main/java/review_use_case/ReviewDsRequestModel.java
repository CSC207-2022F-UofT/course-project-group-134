package review_use_case;

public class ReviewDsRequestModel {
    private String reviewString;
    private int ratings;
    private String dininghall;
    private String username;
    private String reviewType;

    public ReviewDsRequestModel(String reviewString, int ratings, String dininghall, String reviewType, String username){
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

    public String getReviewType(){
        return this.reviewType;
    }

    public String getUsername(){
        return this.username;
    }
}
