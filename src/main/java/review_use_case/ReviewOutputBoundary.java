package review_use_case;

// Use case layer

/**
 * The purpose of the ReviewOutputBoundary is to invert the dependency
 * to satisfy the dependency rule in CLEAN architecture.
 */
public interface ReviewOutputBoundary {
    ReviewResponseModel prepareSuccessView(ReviewResponseModel info);
    ReviewResponseModel prepareFailView(String error);
}
