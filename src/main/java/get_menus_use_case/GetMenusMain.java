package get_menus_use_case;

import entities.MenuFactory;
import entities.OrderFactory;
import order_use_case.*;
import screens.OrderView;

import java.io.IOException;

public class GetMenusMain {

    public static void main(String[] args) throws Exception {
        GetMenusOutputBoundary getMenusPresenter = new GetMenusPresenter();
        MenuFactory menuFactory = new MenuFactory();
        GetMenusInputBoundary getMenusInteractor = new GetMenusInteractor(getMenusPresenter, menuFactory);
        GetMenusController getMenusController = new GetMenusController(getMenusInteractor);

        OrderDsGateway orders;
        try {
            orders = new FileOrder("./orders.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        // TODO: OrderFactory is ENTIRELY UNUSED because it is completely unnecessary right now
        // Maybe we should add a method that calculates the price for us?
        OrderFactory orderFactory = new OrderFactory();
        OrderOutputBoundary orderPresenter = new OrderPresenter();
        OrderInputBoundary orderInteractor = new OrderInteractor(orders, orderPresenter, orderFactory);
        OrderController orderController = new OrderController(orderInteractor);

        OrderView orderView = new OrderView(orderController, getMenusController);
    }
}
