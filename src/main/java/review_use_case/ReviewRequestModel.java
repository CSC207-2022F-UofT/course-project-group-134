package review_use_case;

// Use case layer

/**
 * ReviewRequestModel has the review information which the user inputs
 * into the review screen.
 */
public class ReviewRequestModel {
    private String reviewString;
    private int ratings;
    private String dininghall;
    private String username;
    private String itemName;
    private String email;

    public ReviewRequestModel(String reviewString, int ratings, String dininghall, String itemName, String username, String email){
        this.reviewString = reviewString;
        this.ratings = ratings;
        this.dininghall = dininghall;
        this.itemName = itemName;
        this.username = username;
        this.email = email;
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

    public String getEmail(){
        return this.email;
    }
}
