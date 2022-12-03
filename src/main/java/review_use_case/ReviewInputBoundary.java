package review_use_case;

import java.io.IOException;

// Use case layer

/**
 * The purpose of the ReviewInputBoundary is to invert the dependency
 * to satisfy the dependency rule in CLEAN architecture.
 */
public interface ReviewInputBoundary {
    ReviewResponseModel create(ReviewRequestModel requestModel) throws IOException;

}
