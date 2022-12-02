package review_use_case;

import entities.ReviewFactory;

import java.io.IOException;

// Use case layer

/**
 * The interactor for the review use case. Performs the logic for the review use case.
 */
public class ReviewInteractor implements ReviewInputBoundary{

    final ReviewDsGateway reviewDsGateway;
    final ReviewAccessPresenter reviewPresenter;
    final ReviewFactory reviewFactory;

    public ReviewInteractor(ReviewDsGateway reviewDsGateway, ReviewAccessPresenter reviewPresenter, ReviewFactory reviewFactory){
        this.reviewDsGateway = reviewDsGateway;
        this.reviewPresenter = reviewPresenter;
        this.reviewFactory = reviewFactory;
    }

    @Override
    public ReviewResponseModel create(ReviewRequestModel requestModel) throws IOException {
        ReviewDsRequestModel reviewDsModel;
        reviewDsModel = new ReviewDsRequestModel(requestModel.getReviewString(), requestModel.getRatings(), requestModel.getDininghall(),
                requestModel.getItemName(), requestModel.getUsername());

        reviewDsGateway.updateReview(reviewDsModel);

        ReviewResponseModel reviewsResponseModel = new ReviewResponseModel(requestModel.getReviewString(),
                (requestModel.getRatings()));
        return reviewPresenter.prepareSuccessView(reviewsResponseModel);
    }
}
