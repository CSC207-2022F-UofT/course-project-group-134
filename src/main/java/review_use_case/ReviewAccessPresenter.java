package review_use_case;

public class ReviewAccessPresenter {
    public ReviewResponseModel prepareSuccessView(ReviewResponseModel info){
        return info;
    }
    public ReviewResponseModel prepareFailView(String error){
        throw new ReviewFailed(error);
    }
}
