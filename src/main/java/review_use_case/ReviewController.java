package review_use_case;

import java.io.IOException;

public class ReviewController {
    ReviewInputBoundary boundary;

    public ReviewController(ReviewInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public ReviewResponseModel create(String reviewString, int ratings, String username,
                                      String itemName, String dininghall) throws IOException {
        ReviewRequestModel requestModel;
        requestModel = new ReviewRequestModel(reviewString, ratings, dininghall, itemName, username);
        return boundary.create(requestModel);
    }
}
