package review_use_case;

import entities.ReviewFactory;

import java.io.IOException;

public class ReviewMain {

    public static void create() throws IOException {
        ReviewDsGateway review;
        System.out.println("here 4");
        try {
            review = new ReviewGateway(" /reviews.csv");
        } catch (IOException e){
            throw new RuntimeException("Could not create file.");
        }
        System.out.println("here 3");
        ReviewAccessPresenter presenter = new ReviewAccessPresenter();
        ReviewFactory reviewFactory = new ReviewFactory();
        ReviewInputBoundary interactor = new ReviewInteractor(review, presenter, reviewFactory);
        ReviewController reviewController = new ReviewController(interactor);
        //ReviewScreen reviewScreen = new ReviewScreen(reviewController);
        reviewController.create("username", "reviewString", 0, "dininghall", "FOOD_ITEM");
    }
}
