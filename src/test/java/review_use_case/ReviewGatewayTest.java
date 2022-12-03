package review_use_case;

import entities.ReviewFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class ReviewGatewayTest {

    private ReviewInputBoundary interactor;
    private ReviewDsGateway gateway;
    private ReviewController controller;
    private ReviewFactory reviewFactory;

    @BeforeEach
    void setUp() throws Exception {
        try {
            gateway = new ReviewGateway("./src/test/resources/reviews.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        ReviewAccessPresenter presenter = new ReviewAccessPresenter();
        ReviewFactory reviewFactory = new ReviewFactory();
        interactor = new ReviewInteractor(gateway, presenter, reviewFactory);
        controller = new ReviewController(interactor);

        try {
            controller.create("excellent", 3, "laciscat", "pizza", "CAMPUS_ONE", "c@mail.utoronto.ca");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ReviewFailed ex) {
            fail("Threw review error");
        }
    }

    @AfterEach
    void tearDown(){
        new File("./src/test/resources/reviews.csv").delete();
    }



}
