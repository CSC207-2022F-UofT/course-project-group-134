package selling_use_case;

import entities.ResidenceType;
import order_use_case.OrderDsGateway;
import order_use_case.OrderDsRequestModel;
import order_use_case.OrderGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class SellingControllerTest {
    private SellingController controller;

    @BeforeEach
    void setUp() throws Exception {
        OrderDsGateway gateway;
        try {
            gateway = new OrderGateway("./src/test/resources/orders.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        SellingPresenter presenter = new SellingPresenter();
        SellingInputBoundary interactor = new SellingInteractor(presenter, gateway);
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

}
