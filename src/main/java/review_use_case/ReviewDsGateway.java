package review_use_case;

import entities.Review;
import entities.ReviewFactory;

import java.io.IOException;

public interface ReviewDsGateway {

    void save(ReviewDsRequestModel requestModel) throws IOException;   // Save new review data

    void save() throws IOException;

    Review readReview(String review, int ratings, ReviewFactory reviewFactory);
}
