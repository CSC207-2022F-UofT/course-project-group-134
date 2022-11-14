package review_use_case;

public class ReviewResponseModel {
    private String review;

    public ReviewResponseModel(String review){
        this.review = review;
    }

    public String getReview() {
        return review;
    }
}
