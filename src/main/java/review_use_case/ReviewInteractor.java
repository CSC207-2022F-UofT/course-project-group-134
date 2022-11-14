package review_use_case;

import entities.ReviewFactory;
import entities.ReviewType;

import java.io.IOException;

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
        System.out.println("here");
        if(requestModel.getReviewType() == ReviewType.FOOD_ITEM){
            reviewDsModel = new ReviewDsRequestModel(requestModel.getReviewString(), requestModel.getRatings(), requestModel.getDininghall(),
                    "Food Item", requestModel.getUsername());
        } else if(requestModel.getReviewType() == ReviewType.SELLER){
            reviewDsModel = new ReviewDsRequestModel(requestModel.getReviewString(), requestModel.getRatings(), requestModel.getDininghall(),
                    "Seller", requestModel.getUsername());
        } else{
            reviewDsModel = new ReviewDsRequestModel(requestModel.getReviewString(), requestModel.getRatings(), requestModel.getDininghall(),
                    "Dining Hall", requestModel.getUsername());
        }

        reviewDsGateway.save(reviewDsModel);

        ReviewResponseModel reviewsResponseModel = new ReviewResponseModel(requestModel.getReviewString());
        return reviewPresenter.prepareSuccessView(reviewsResponseModel);
    }
}
