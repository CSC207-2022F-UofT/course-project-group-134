package order_use_case;

import order_history_use_case.OrderHistoryInputBoundary;
import order_history_use_case.OrderHistoryInteractor;
import order_history_use_case.OrderHistoryOutputBoundary;
import order_history_use_case.OrderHistoryPresenter;
import screens.BuyerDefaultView;
import screens.SellingScreen;
import selling_use_case.*;

import java.io.IOException;

public class BuyerMain {
    public static void create() throws IOException {
        OrderHistoryOutputBoundary orderHistoryOutputBoundary = new OrderHistoryPresenter();
        OrderHistoryInputBoundary orderHistoryInteractor = new OrderHistoryInteractor("tb", "tb", orderHistoryOutputBoundary);
        BuyerDefaultView buyerDefaultView = new BuyerDefaultView("tb", "tb", orderHistoryInteractor);
       // TODO: fill this later once we have all the order classe created.
    }
}
