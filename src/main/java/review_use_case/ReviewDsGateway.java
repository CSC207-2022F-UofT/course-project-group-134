package review_use_case;

import entities.Review;

import java.io.IOException;
import java.util.ArrayList;

public interface ReviewDsGateway {

    void save(ReviewDsRequestModel requestModel) throws IOException;   // Save new review data

    void save() throws IOException;

    ArrayList<Review> getReviewFromName(String itemName);
}
