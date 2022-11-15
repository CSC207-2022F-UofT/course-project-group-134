package get_menus_use_case;

import entities.MenuFactory;
import order_use_case.OrderController;
import screens.OrderView;

public class GetMenusmMain {

    public static void main(String[] args) throws Exception {
        GetMenusPresenter getMenusPresenter = new GetMenusPresenter();
        MenuFactory menuFactory = new MenuFactory();
        OrderController orderController = new OrderController();
        GetMenusInteractor getMenusInteractor = new GetMenusInteractor(getMenusPresenter, menuFactory);
        GetMenusController getMenusController = new GetMenusController(getMenusInteractor);
        OrderView orderView = new OrderView(orderController, getMenusController);
    }
}
