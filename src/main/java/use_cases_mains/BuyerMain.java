package use_cases_mains;

import order_history_use_case.OrderHistoryInputBoundary;
import order_history_use_case.OrderHistoryInteractor;
import order_history_use_case.OrderHistoryOutputBoundary;
import order_history_use_case.OrderHistoryPresenter;
import screens.BuyerDefaultView;

import java.io.IOException;

public class BuyerMain {
    public static void create(String username, String email) throws IOException {
        OrderHistoryOutputBoundary orderHistoryOutputBoundary = new OrderHistoryPresenter();
        OrderHistoryInputBoundary orderHistoryInteractor = new OrderHistoryInteractor(username, email, orderHistoryOutputBoundary);
        BuyerDefaultView buyerDefaultView = new BuyerDefaultView(username, email, orderHistoryInteractor);
       // TODO: fill this later once we have all the order classes created.
    }
}
