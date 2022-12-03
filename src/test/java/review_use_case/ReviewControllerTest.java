package review_use_case;

import entities.ResidenceType;
import entities.Review;
import entities.ReviewFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewControllerTest {

    private ReviewInputBoundary interactor;
    private ReviewDsGateway gateway;
    private ReviewController controller;

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

    }

    @AfterEach
    void tearDown() {
        new File("./src/test/resources/reviews.csv").delete();
    }

    @Test
    public void testWrittenReviewDoesNotExist() {
        try {
            controller.create("ok", 3, "laciscat",
                    "pizza", "CAMPUS_ONE", "c@mail.utoronto.ca");
            List<Review> list = gateway.getReviewsFromName("pizza", ResidenceType.CAMPUS_ONE);
            assertFalse(list.isEmpty());
            Review review = list.get(0);
            assertEquals("ok", review.getReviewString());
            assertEquals("laciscat", review.getBuyer());
            assertEquals(3, review.getRating());
            assertEquals("pizza", review.getItemName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ReviewFailed ex) {
            fail("Threw review error");
        }
    }

    @Test
    public void testDuplicateReview() {
        try {
            controller.create("ok", 3, "laciscat",
                    "pizza", "CAMPUS_ONE", "c@mail.utoronto.ca");
            controller.create("bad", 1, "laciscat",
                    "pizza", "CAMPUS_ONE", "c@mail.utoronto.ca");
            List<Review> list = gateway.getReviewsFromName("pizza", ResidenceType.CAMPUS_ONE);
            assertFalse(list.isEmpty());
            Review review = list.get(0);
            assertEquals("bad", review.getReviewString());
            assertEquals("laciscat", review.getBuyer());
            assertEquals(1, review.getRating());
            assertEquals("pizza", review.getItemName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ReviewFailed ex) {
            fail("Threw review error.");
        }
    }
}
