package review_use_case;

import entities.ResidenceType;
import entities.Review;

import java.io.IOException;
import java.util.List;

// Use case layer

/**
 * The purpose of the ReviewDsGateway is to invert the dependency
 * to satisfy the dependency rule in CLEAN architecture.
 */

public interface ReviewDsGateway {

    void save() throws IOException;

    List<Review> getReviewsFromName(String itemName, ResidenceType residence);

    void updateReview(ReviewDsRequestModel newReview) throws IOException;
}
