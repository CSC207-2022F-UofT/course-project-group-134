package order_history_use_case;

import entities.*;
import get_menus_use_case.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import order_use_case.*;
import user_access_use_case.*;
import get_menus_use_case.*;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sign_up_use_case.SignUpControllerTest;
import user_access_use_case.*;
import user_login_use_case.*;

import static org.junit.jupiter.api.Assertions.*;


public class OrderHistoryControllerTest {
    private OrderHistoryInputBoundary orderHistoryInteractor;
    private OrderHistoryController orderHistoryController;

    private OrderHistoryGateway OrderHistoryGateway;

    @BeforeEach
    public void setUp() throws Exception {

        String buyerUsername = "buyer1";
        String buyerEmail = "buyer1@mail.utoronto.ca";
        String filepath = ".src/test/resources/orders.csv";

        // Creating some users
        SignUpGateway signUpGateway = new SignUpGateway("./src/test/resources/users.csv");

        SignUpPresenter presenter = new SignUpPresenter();
        BuyerFactory buyerFactory = new BuyerFactory();
        SellerFactory sellerFactory = new SellerFactory();
        UserFactory userFactory = new UserFactory(buyerFactory, sellerFactory);

        SignUpInputBoundary signUpInputBoundary = new SignUpRegisterInteractor(
                signUpGateway, presenter, userFactory);
        SignUpRequestController signUpRequestController = new SignUpRequestController(signUpInputBoundary);

        // Creating a buyer account
        try {
            SignUpResponseModel responseModel = signUpRequestController.create(buyerUsername,
                    buyerEmail, "1234", UserType.BUYER.toString(),
                    ResidenceType.NEW_COLLEGE.toString(), "10.0");
            assertEquals(responseModel.getName(), buyerUsername);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SignUpFailed ex) {
            fail("Threw sign up error");
        }

        // Creating another buyer account
        try {
            SignUpResponseModel responseModel = signUpRequestController.create("buyer2",
                    "buyer2@mail.utoronto.ca", "1235", UserType.BUYER.toString(),
                    ResidenceType.NEW_COLLEGE.toString(), "10.0");
            assertEquals(responseModel.getName(), "buyer2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SignUpFailed ex) {
            fail("Threw sign up error");
        }


    // TODO: create the menu file and add food items
    GetMenusOutputBoundary getMenusPresenter = new GetMenusPresenter();
    MenuGatewayInterface menuGateway = new GetMenusGateway();
    GetMenusInputBoundary getMenusInteractor = new GetMenusInteractor(getMenusPresenter, menuGateway);
    GetMenusController getMenusController = new GetMenusController(getMenusInteractor);
    getMenusController.setUpInteractor("NEW_COLLEGE");

        try {
            OrderHistoryGateway = new OrderHistoryGateway(buyerUsername, buyerEmail);
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }

        OrderHistoryOutputBoundary orderHistoryOutputBoundary = new OrderHistoryPresenter();
        orderHistoryInteractor = new OrderHistoryInteractor(buyerUsername, buyerEmail, orderHistoryOutputBoundary);
        orderHistoryController = new OrderHistoryController(buyerUsername, buyerEmail, orderHistoryInteractor);

        // TODO: create a new order

    }

    /*
    To test:
    1. Check that each order's stuff in the correct order
    2. Check if exceptions are thrown at the correct places
    3. Check if the statuses of orders in FinishedOrders is finished as required
    4. Check that the intersection of FinishedOrders and CurrentOrders is empty

     */

    @AfterEach
    void tearDown() {
        new File("./src/test/resources/orders.csv").delete();
    }
}

