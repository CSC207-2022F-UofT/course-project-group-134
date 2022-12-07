package get_menus_use_case;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user_access_use_case.SignUpDsGateway;

import java.util.List;
import java.util.Map;

public class GetMenusInteractorTest {

    private SignUpDsGateway gateway;
    private GetMenusController getMenusController;

    @BeforeEach
    void setUp() {
        GetMenusOutputBoundary getMenusPresenter = new GetMenusPresenter();
        MenuGatewayInterface menuGateway = new GetMenusGateway();
        GetMenusInputBoundary getMenusInteractor = new GetMenusInteractor(getMenusPresenter, menuGateway);
        getMenusController = new GetMenusController(getMenusInteractor);
    }

    @Test
    void testGettingAMenu() throws Exception {
        getMenusController.setUpInteractor("NEW_COLLEGE");
        List<String[]> foodDetails = getMenusController.getFoodDetails();
        Assertions.assertEquals(foodDetails.get(0)[0], "Pepperoni Pizza");
        Assertions.assertEquals(foodDetails.get(0)[1], "8.99");
        Assertions.assertEquals(foodDetails.get(0)[2], "Cheese,Wheat");
        Assertions.assertEquals(foodDetails.get(0)[3], "Cheese,Pepperoni,Wheat,Tomato Sauce");
        Assertions.assertEquals(foodDetails.get(0)[4], "300");

        Assertions.assertEquals(foodDetails.get(1)[0], "Veggie Burger");
        Assertions.assertEquals(foodDetails.get(1)[1], "9.99");
        Assertions.assertEquals(foodDetails.get(1)[2], "Cheese");
        Assertions.assertEquals(foodDetails.get(1)[3], "Cheese,Tomatoes,Mayonnaise,Onions,Potatoes");
        Assertions.assertEquals(foodDetails.get(1)[4], "250");
    }

    @AfterEach
    void tearDown() {

    }
}
