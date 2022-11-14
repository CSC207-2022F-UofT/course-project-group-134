package review_use_case;

import java.io.IOException;

public interface ReviewInputBoundary {
    ReviewResponseModel create(ReviewRequestModel requestModel) throws IOException;

}
