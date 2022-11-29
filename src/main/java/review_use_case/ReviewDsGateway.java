package review_use_case;

import entities.ResidenceType;
import entities.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReviewDsGateway {

    void save(ReviewDsRequestModel requestModel) throws IOException;   // Save new review data

    void save() throws IOException;

    List<Review> getReviewsFromName(String itemName, ResidenceType residence);
}
