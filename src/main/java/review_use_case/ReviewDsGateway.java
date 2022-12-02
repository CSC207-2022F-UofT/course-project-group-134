package review_use_case;

import entities.ResidenceType;
import entities.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReviewDsGateway {

    void save() throws IOException;

    List<Review> getReviewsFromName(String itemName, ResidenceType residence);

    void updateReview(ReviewDsRequestModel newReview) throws IOException;
}
