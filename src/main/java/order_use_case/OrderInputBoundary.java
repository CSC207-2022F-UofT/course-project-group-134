package order_use_case;

import order_history_use_case.OrderHistoryResponseModel;

public interface OrderInputBoundary {
    OrderResponseModel placeOrder(OrderRequestModel request);
}
