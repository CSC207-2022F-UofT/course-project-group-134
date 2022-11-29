package review_use_case;

public interface ReviewOutputBoundary {
    ReviewResponseModel prepareSuccessView(ReviewResponseModel info);
    ReviewResponseModel prepareFailView(String error);
}
