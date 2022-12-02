package review_use_case;

// Use case layer

/**
 * The ReviewAccessPresenter either returns a success view if the review information
 * is valid, or a fail view if the information provided is invalid.
 */
public class ReviewAccessPresenter {
    public ReviewResponseModel prepareSuccessView(ReviewResponseModel info){
        return info;
    }
    public ReviewResponseModel prepareFailView(String error){
        throw new ReviewFailed(error);
    }
}
