package order_use_case;

import entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OrderGatewayTest {

    private OrderDsGateway gateway;

    @BeforeEach
    void setUp() throws Exception {
        try {
            gateway = new OrderGateway("./src/test/resources/orders.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        OrderPresenter presenter = new OrderPresenter();
        OrderFactory orderFactory = new OrderFactory();
        OrderInputBoundary interactor = new OrderInteractor(
                gateway, presenter, orderFactory);
        OrderController controller = new OrderController(interactor);

        try {
            String[] foodItems = {"Burger", "Fries"};
            Integer[] foodQuantity = {1,1};
            String[] foodItems2 = {"Burger", "Burger", "Poutine", "Burger"};
            Integer[] foodQuantity3 = {1,1,1,1};

            controller.placeOrder("Vivian", "vivianyt.liu@mail.utoronto.ca",
                    ResidenceType.TRINITY_COLLEGE.toString(), foodItems, foodQuantity, 20.31);
            controller.placeOrder("Vivian", "vivianyt.liu@mail.utoronto.ca",
                    ResidenceType.TRINITY_COLLEGE.toString(), foodItems2, foodQuantity3, 20.03953590);

        } catch (OrderFailed ex) {
            fail("Threw place order error");
        }
    }

    @AfterEach
    void tearDown() {
        new File("./src/test/resources/orders.csv").delete();
    }

    @Test
    void testOrderExistsById() {
        assert(gateway.orderExistsById(0));
        assert(gateway.orderExistsById(1));
    }

    @Test
    void testIfOrderDoesNotExists() {
        assert(!gateway.orderExistsById(7));
    }


    @Test
    void testGetUnfulfilledOrders() {
        ArrayList<Integer> unfulfilledOrders = gateway.getUnfulfilledOrders(ResidenceType.TRINITY_COLLEGE.toString());
        assertEquals(2, unfulfilledOrders.size());
        assertEquals(0, unfulfilledOrders.get(0));
        assertEquals(1, unfulfilledOrders.get(1));
    }

    @Test
    void testGetFinishedOrders() throws IOException {
        ArrayList<Integer> finishedOrders = gateway.getFinishedOrders("vivianyt.liu@mail.utoronto.ca");
        assertEquals(0, finishedOrders.size());
    }

    @Test
    void testGetOrderInfoAndUpdateOrder() {
        OrderDsModel orderDsModel = gateway.getOrderInfo(1);
        assertEquals(orderDsModel.getOrderID(), 1);
        assertEquals(orderDsModel.getBuyerEmail(), "vivianyt.liu@mail.utoronto.ca");
        assertEquals(orderDsModel.getBuyerName(), "Vivian");
        assertEquals(orderDsModel.getPrice(), 20.03953590);
        assertEquals(orderDsModel.getResidence(), ResidenceType.TRINITY_COLLEGE.toString());
        assertEquals(orderDsModel.getFoodItems().length, 4);
        assertEquals(orderDsModel.getFoodQuantity().length, 4);
        assertEquals(orderDsModel.getStatus(), OrderStatusType.ORDERED.toString());

        gateway.updateOrder(1, OrderStatusType.ACCEPTED, "s@mail.utoronto.ca");
        assertEquals(gateway.getOrderStatus(1), OrderStatusType.ACCEPTED);
        assertEquals(gateway.getOrderInfo(1).getSellerEmail(), "s@mail.utoronto.ca");
    }

    @Test
    void testOrderStatus() {
        assertEquals(gateway.getOrderStatus(0), OrderStatusType.ORDERED);
        gateway.setOrderStatus(0, OrderStatusType.FINISHED);
        assertEquals(gateway.getOrderStatus(0), OrderStatusType.FINISHED);

    }

//    @Test
//    void testSellerHasOrder() {
//        assertTrue(gateway.sellerHasOrder("s@mail.utoronto.ca"));
//    }

//    @Test
//    void testGetOrderNumberFromSellerEmail() throws DoesNotExistException {
//        assertEquals(gateway.getOrderNumberFromSellerEmail("s@mail.utoronto.ca"), 1);
//    }
//
    @Test
    void testGetPriceFromOrderNumber() throws DoesNotExistException {
        assertEquals(gateway.getPriceFromOrderNumber(1), 20.03953590);
    }
}