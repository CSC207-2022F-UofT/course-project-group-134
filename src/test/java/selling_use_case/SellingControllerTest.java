package selling_use_case;

import order_use_case.OrderDsGateway;
import order_use_case.OrderGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

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
    void testSellingGeneral(){
        try {
            controller.accept("v@mail.utoronto.ca", "3");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SellingFailed ex) {
            // threw selling exception
        }
    }

}
