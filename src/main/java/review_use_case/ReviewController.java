package review_use_case;

import entities.ReviewType;

import java.io.IOException;

public class ReviewController {
    ReviewInputBoundary boundary;

    public ReviewController(ReviewInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public ReviewResponseModel create(String username, String reviewString, int ratings, String dininghall,
                                      String reviewTypeString) throws IOException {
        ReviewRequestModel requestModel;
        ReviewType reviewType;
        if (reviewTypeString.equals("FOOD_ITEM")) {
            reviewType = ReviewType.FOOD_ITEM;
        }
        else if(reviewTypeString.equals("SELLER")){
            reviewType = ReviewType.SELLER;
        }
        else {
            reviewType = ReviewType.DINING_HALL;
        }
        requestModel = new ReviewRequestModel(username, ratings, reviewString, reviewType, dininghall);
        System.out.println("here2");
        return boundary.create(requestModel);
    }
}
