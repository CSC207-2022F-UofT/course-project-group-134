package review_use_case;

import java.io.IOException;

// Use case layer

/**
 * The controller for the review use case. Takes in information from the
 * review screen and sends it to the interactor.
 */
public class ReviewController {
    ReviewInputBoundary boundary;

    public ReviewController(ReviewInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public ReviewResponseModel create(String reviewString, int ratings, String username,
                                      String itemName, String dininghall, String email) throws IOException {
        ReviewRequestModel requestModel;
        requestModel = new ReviewRequestModel(reviewString, ratings, dininghall, itemName, username, email);
        return boundary.create(requestModel);
    }
}
