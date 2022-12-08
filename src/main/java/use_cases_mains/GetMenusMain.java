package use_cases_mains;

import get_menus_use_case.*;
import order_history_use_case.OrderHistoryInputBoundary;
import order_history_use_case.OrderHistoryInteractor;
import order_history_use_case.OrderHistoryOutputBoundary;
import order_history_use_case.OrderHistoryPresenter;
import order_use_case.*;
import screens.OrderView;

import java.io.IOException;

public class GetMenusMain {

    public static void create(String username, String email) throws Exception {
        GetMenusOutputBoundary getMenusPresenter = new GetMenusPresenter();
        MenuGatewayInterface menuGateway = new GetMenusGateway();
        GetMenusInputBoundary getMenusInteractor = new GetMenusInteractor(getMenusPresenter, menuGateway);
        GetMenusController getMenusController = new GetMenusController(getMenusInteractor);

        OrderDsGateway orders;
        try {
            orders = new OrderGateway("./src/main/java/data_storage/orders.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        // TODO: OrderFactory is ENTIRELY UNUSED because it is completely unnecessary right now
        // Maybe we should add a method that calculates the price for us?
        OrderOutputBoundary orderPresenter = new OrderPresenter();
        OrderInputBoundary orderInteractor = new OrderInteractor(orders, orderPresenter);
        OrderController orderController = new OrderController(orderInteractor);

        OrderHistoryOutputBoundary orderHistoryOutputBoundary = new OrderHistoryPresenter();
        OrderHistoryInputBoundary orderHistoryInteractor = new OrderHistoryInteractor(username, email, orderHistoryOutputBoundary);

        new OrderView(orderController, getMenusController,username, email, orderHistoryInteractor);

    }
}
