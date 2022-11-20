package review_use_case;

public class ReviewRequestModel {
    private String reviewString;
    private int ratings;
    private String dininghall;
    private String username;
    private String itemName;

    public ReviewRequestModel(String reviewString, int ratings, String dininghall, String itemName, String username){
        this.reviewString = reviewString;
        this.ratings = ratings;
        this.dininghall = dininghall;
        this.itemName = itemName;
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

    public String getItemName(){
        return this.itemName;
    }

    public String getUsername(){
        return this.username;
    }
}
