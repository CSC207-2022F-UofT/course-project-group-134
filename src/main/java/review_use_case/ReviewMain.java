package review_use_case;

import entities.ReviewFactory;
import screens.ReviewScreen;

import java.io.IOException;

/**
 * ReviewMain is called when an instance of the review use case is called.
 * Creates and sets up instances of all the relevant classes for the review use case.
 */
public class ReviewMain {

    public static void create(String username, String itemName, String diningHall) throws IOException {
        ReviewDsGateway review;
        try {
            review = new ReviewGateway("./src/main/java/data_storage/reviews.csv");
        } catch (IOException e){
            throw new RuntimeException("Could not create file.");
        }
        ReviewAccessPresenter presenter = new ReviewAccessPresenter();
        ReviewFactory reviewFactory = new ReviewFactory();
        ReviewInputBoundary interactor = new ReviewInteractor(review, presenter, reviewFactory);
        ReviewController reviewController = new ReviewController(interactor);
        new ReviewScreen(reviewController, username, itemName, diningHall);
    }
}
