package get_menus_use_case;

import entities.*;
import order_history_use_case.OrderHistoryInputBoundary;
import order_history_use_case.OrderHistoryInteractor;
import order_history_use_case.OrderHistoryOutputBoundary;
import order_history_use_case.OrderHistoryPresenter;
import order_use_case.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.OrderView;
import user_access_use_case.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetMenusInteractorTest {

    private SignUpDsGateway gateway;
    private GetMenusController getMenusController;

    @BeforeEach
    void setUp() throws Exception {

        /*try {
            gateway = new SignUpGateway("./src/test/resources/users.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }

        BuyerFactory buyerFactory = new BuyerFactory();
        SellerFactory sellerFactory = new SellerFactory();
        UserFactory userFactory = new UserFactory(buyerFactory, sellerFactory);

        SignUpPresenter signupPresenter = new SignUpPresenter();
        SignUpInputBoundary signupInteractor = new SignUpRegisterInteractor(
                gateway, signupPresenter, userFactory);
        SignUpRequestController signupController = new SignUpRequestController(signupInteractor);
        try {
            signupController.create("Tejas",
                    "tejas.balaji@mail.utoronto.ca", "1234", UserType.SELLER.toString(),
                    ResidenceType.ST_MICHAELS_COLLEGE.toString(), "10.0");
            signupController.create("eigenvector",
                    "eigenvector@mail.utoronto.ca", "password2", UserType.BUYER.toString(),
                    ResidenceType.CHESTNUT.toString(), "0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SignUpFailed ex) {
            fail("Threw sign up error");
        }*/


        GetMenusOutputBoundary getMenusPresenter = new GetMenusPresenter();
        MenuGatewayInterface menuGateway = new MenuGateway();
        GetMenusInputBoundary getMenusInteractor = new GetMenusInteractor(getMenusPresenter, menuGateway);
        getMenusController = new GetMenusController(getMenusInteractor);

        OrderDsGateway orders;
        try {
            orders = new OrderGateway("./src/main/java/data_storage/orders.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        // TODO: OrderFactory is ENTIRELY UNUSED because it is completely unnecessary right now
        // Maybe we should add a method that calculates the price for us?
        /*OrderFactory orderFactory = new OrderFactory();
        OrderOutputBoundary orderPresenter = new OrderPresenter();
        OrderInputBoundary orderInteractor = new OrderInteractor(orders, orderPresenter, orderFactory);
        OrderController orderController = new OrderController(orderInteractor);

        OrderHistoryOutputBoundary orderHistoryOutputBoundary = new OrderHistoryPresenter();
        OrderHistoryInputBoundary orderHistoryInteractor = new OrderHistoryInteractor("Tejas", "tejas.balaji@mail.utoronto.ca", orderHistoryOutputBoundary);

        OrderView orderView = new OrderView(orderController, getMenusController,"eigenvector", "eigenvector@mail.utoronto.ca", orderHistoryInteractor);
        */
    }

    @Test
    void testGettingAMenu() throws Exception {
        /*GetMenusResponseModel responseModel = getMenusController.getFoodItemNames("NEW_COLLEGE");
        assertEquals(responseModel.getFoodItemPrices(), new ArrayList<>(Arrays.asList(8.99, 9.99)));
        assertEquals(responseModel.getFoodItemNames(), new ArrayList<>(Arrays.asList("Pepperoni Pizza", "Veggie Burger")));
        assertEquals(responseModel.getFoodItemCalories(), new ArrayList<>(Arrays.asList(300, 250)));
        assertArrayEquals(responseModel.getFoodItemAllergens().get(0),new String[]{"Cheese", "Wheat"});
        assertArrayEquals(responseModel.getFoodItemAllergens().get(1), new String[]{"Cheese"});
        assertEquals(responseModel.getFoodItemIngredients().get(0)[2], "Wheat");*/
    }

    @AfterEach
    void tearDown() {

    }
}
