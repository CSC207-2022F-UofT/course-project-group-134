package entities;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class SellerUnitTest {

    @Test
    void addReview_test(){
        MealPlan mealPlan= new MealPlan("New_College", 0.0);
        Seller seller = new Seller("name", "password", mealPlan, "poopoo123@mail.utoronto.ca");
        assertTrue(seller.getReviews().size() == 0);
    }
}
