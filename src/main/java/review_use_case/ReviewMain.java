package review_use_case;

import entities.ReviewFactory;
import screens.ReviewScreen;

import java.io.IOException;

public class ReviewMain {

    public static void create(String username, String itemName, String diningHall) throws IOException {
        ReviewDsGateway review;
        try {
            review = new ReviewGateway("./reviews.csv");
        } catch (IOException e){
            throw new RuntimeException("Could not create file.");
        }
        ReviewAccessPresenter presenter = new ReviewAccessPresenter();
        ReviewFactory reviewFactory = new ReviewFactory();
        ReviewInputBoundary interactor = new ReviewInteractor(review, presenter, reviewFactory);
        ReviewController reviewController = new ReviewController(interactor);
        ReviewScreen reviewScreen = new ReviewScreen(reviewController, username, itemName, diningHall);
    }
}
