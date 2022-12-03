package selling_use_case;

import entities.OrderStatusType;
import entities.ResidenceType;
import order_use_case.OrderDsGateway;
import order_use_case.OrderDsRequestModel;
import order_use_case.OrderGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SellingControllerTest {
    private SellingController controller;
    private OrderDsGateway orderDsGateway;

    @BeforeEach
    void setUp() throws Exception {
        try {
            orderDsGateway = new OrderGateway("./src/test/resources/orders.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        SellingPresenter presenter = new SellingPresenter();
        SellingInputBoundary interactor = new SellingInteractor(presenter, orderDsGateway);
        controller = new SellingController(interactor);
    }

    @AfterEach
    void tearDown() {
        new File("./src/test/resources/orders.csv").delete();
    }

    @Test
    void testSellingFailOrderDoesNotExist(){
        try {
            controller.accept("v@mail.utoronto.ca", "3", "Mouse");
            fail("Order 3 doesn't exist, exception SellingFailed should be raised");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SellingFailed ex) {
            // threw selling exception
        }
    }

    @Test
    void testSellingSuccessOrderExists(){
        try {
            orderDsGateway.saveOrder(new OrderDsRequestModel("buyerName", "buyerEmail@mail.utoronto.ca",
                    "null", "null", ResidenceType.TRINITY_COLLEGE.toString(),
                    OrderStatusType.ORDERED.toString(), new String[]{"food item 1"}, new Integer[]{1}, 50.0));
            SellingResponseModel responseModel = controller.accept("v@mail.utoronto.ca", "0");
            assertEquals(0, responseModel.getOrderNumber());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SellingFailed ex) {
            fail("Order 0 exists, exception SellingFailed shouldn't be raised");
        }
    }

    @Test
    void testFailOrderAlreadyTaken(){
        try {
            orderDsGateway.saveOrder(new OrderDsRequestModel("buyerName", "buyerEmail@mail.utoronto.ca",
                    "null", "null", ResidenceType.TRINITY_COLLEGE.toString(),
                    OrderStatusType.ORDERED.toString(), new String[]{"food item 1"}, new Integer[]{1}, 50.0));
            controller.accept("v@mail.utoronto.ca", "0");
            controller.accept("v2@mail.utoronto.ca", "0");
            fail("Order 0 is taken by v, exception SellingFailed should be raised");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SellingFailed ex) {
            // threw SellingFailed exception
        }
    }
}
