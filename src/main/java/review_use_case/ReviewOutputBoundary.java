package review_use_case;

public interface ReviewOutputBoundary {
    public ReviewResponseModel prepareSuccessView(ReviewResponseModel info);
    public ReviewResponseModel prepareFailView(String error);
}
